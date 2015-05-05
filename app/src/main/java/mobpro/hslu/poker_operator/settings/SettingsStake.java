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

import mobpro.hslu.poker_operator.R;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsStake extends Activity{
    private ListView listView;
    private ArrayList<String> array;
    private ArrayAdapter<String> adapter;
    public SettingsStake(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_stake);

        array = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView_stake);
        for (int i=0; i<2;i++){
            array.add("blabla"+i);
        }
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
    }

    public void addStake (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Add Stake");
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

