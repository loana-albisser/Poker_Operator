package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Limittype;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsLimit extends Activity{
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Limittype> allLimittypes;
    private ArrayAdapter<Limittype> limittypeArrayAdapter;


    public SettingsLimit(){

    }

    public void onCreate(Bundle savedInstanceState) {
        try {
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
            setContentView(R.layout.settings_limit);
            updateList();
            setOnClickListener(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    private void setOnClickListener(final Context context) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                final Limittype limittype = limittypeArrayAdapter.getItem(position);
                input.setText(limittype.getDescription());

                builder.setTitle("Edit Limit");
                builder.setView(input);

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        limittype.setDescription(input.getText().toString());
                        dbAdapter.updateDbObject(limittype);
                        updateList();
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
        });
    }

    private void updateList() {
        listView = (ListView)findViewById(R.id.listView_limit);
        allLimittypes = new ArrayList<>(Limittype.getAllLimittypes(dbAdapter));

        limittypeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allLimittypes);
        listView.setAdapter(limittypeArrayAdapter);
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
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Add Limit");
        builder.setView(input);

        limittypeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allLimittypes);
        listView.setAdapter(limittypeArrayAdapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Limittype limittype = new Limittype(input.getText().toString());
                limittype.setId(dbAdapter.CreateDbObject(limittype));
                limittypeArrayAdapter.add(limittype);
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

