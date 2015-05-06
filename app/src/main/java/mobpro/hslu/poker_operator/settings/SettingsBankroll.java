package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsBankroll extends Activity{
    //private String dialogText = "";
    private ListView listView;
    private ArrayList<ContentValues> array;
    private ArrayAdapter<ContentValues>adapter;
    private Button addButton;
    private DbAdapter dbAdapter;

    public SettingsBankroll(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_bankroll);


    }

    public void addContentToList(){
        array = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView_bankroll);
        dbAdapter = new DbAdapter(this);
        final Collection<ContentValues> values = dbAdapter.getAllByTable("bankroll");
        dbAdapter.open();



        for (ContentValues value: values){
            array.add(value);
        }
        dbAdapter.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        //listView.setAdapter(new SimpleCursorAdapter(this, R.layout.settings_bankroll, cursor, ));
    }

    public void addBankroll (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Add Bankroll");
        builder.setView(input);

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //adapter.add(input.getText().toString());
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

