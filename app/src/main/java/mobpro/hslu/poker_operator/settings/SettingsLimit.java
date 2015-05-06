package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Limittype;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsLimit extends Activity{
    private ListView listView;
    private ArrayList<String> array;
    private ArrayAdapter<String> adapter;
    DbAdapter dbAdapter;

    private ArrayList<Limittype> allLimittypes;
    private ArrayAdapter<Limittype> limittypeArrayAdapter;

    public SettingsLimit(){

    }
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        dbAdapter = new DbAdapter(this);
        setContentView(R.layout.settings_limit);

        array = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView_limit);
        for (int i=0; i<2;i++){
            array.add("blabla"+i);
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        super.onCreate(savedInstanceState);
    }*/

    public void onCreate(Bundle savedInstanceState) {
        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();
        setContentView(R.layout.settings_limit);

        listView = (ListView)findViewById(R.id.listView_limit);
        allLimittypes = new ArrayList<>(Limittype.getAllLimittypes(dbAdapter));

        limittypeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allLimittypes);
        listView.setAdapter(limittypeArrayAdapter);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        dbAdapter.open();
        super.onResume();
    }

    @Override
    public void onPause(){
        dbAdapter.close();
        super.onPause();
    }

    public void addLimit (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setTitle("Add Limit");
        builder.setView(input);

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.add(input.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}

