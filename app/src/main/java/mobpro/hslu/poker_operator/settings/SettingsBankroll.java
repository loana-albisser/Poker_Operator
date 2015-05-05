package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsBankroll extends Activity{
    //private String dialogText = "";
    private ListView listView;
    private ArrayList<String> array;
    private ArrayAdapter<String>adapter;
    private Button addButton;

    public SettingsBankroll(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_bankroll);
    }

    public void addBankroll (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setTitle("Add Bankroll");
        builder.setView(input);

        listView = (ListView)findViewById(R.id.listView_bankroll);
        array = new ArrayList<>();
        addButton = (Button)findViewById(R.id.btn_add);
        /*for (int i=0; i<2;i++){
            array.add("blabla"+i);
        }*/
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                array.add(input.getText().toString());
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

    public void changeBankroll (View v){
        ListView list = (ListView)findViewById(R.id.listView_bankroll);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}

