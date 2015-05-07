package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsBankroll extends Activity{
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Bankroll> allBankroll;
    private ArrayAdapter<Bankroll> bankrolleArrayAdapter;


    public SettingsBankroll(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
            setContentView(R.layout.settings_bankroll);
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
                        final LinearLayout layout = new LinearLayout(getApplicationContext());
                        layout.setOrientation(LinearLayout.VERTICAL);
                        ArrayList<Currency> allCurrencys = new ArrayList<>(Currency.getAllCurrency(dbAdapter));
                        final ArrayAdapter<Currency> currencyArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allCurrencys);

                        final Bankroll bankroll = bankrolleArrayAdapter.getItem(position);

                        final EditText bankrollInput = new EditText(context);
                        bankrollInput.setText(bankroll.getDescription());
                        final Spinner currencyInput = new Spinner(context);
                        currencyInput.setSelection(getSelectedId(bankroll.getCurrency(), currencyInput));
                        currencyInput.setAdapter(currencyArrayAdapter);

                        layout.addView(bankrollInput);
                        layout.addView(currencyInput);
                        bankrollInput.setInputType(InputType.TYPE_CLASS_TEXT);
                        bankrollInput.setHint("Bankroll");


                        builder.setTitle("Edit Bankroll");
                        builder.setView(layout);


                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bankroll.setDescription(bankrollInput.getText().toString());
                        bankroll.setCurrency(currencyArrayAdapter.getItem(currencyInput.getSelectedItemPosition()));
                        dbAdapter.updateDbObject(bankroll);
                        updateList();
                    }
                });

                builder.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbAdapter.deleteDbObject(bankroll);
                        updateList();
                    }
                });
                builder.show();
            }
        });
    }

    private int getSelectedId(Currency currency, Spinner spinner) {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(currency)){
                index = i;
                break;
            }
        }
        return index;
    }

    private void updateList() {
        listView = (ListView)findViewById(R.id.listView_bankroll);

        allBankroll = new ArrayList<>(Bankroll.getAllBankroll(dbAdapter));

        bankrolleArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allBankroll);
        listView.setAdapter(bankrolleArrayAdapter);
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


    public void addBankroll (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        final LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        ArrayList<Currency> allCurrencys = new ArrayList<>(Currency.getAllCurrency(dbAdapter));
        ArrayAdapter<Currency> currencyArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,allCurrencys);

        final EditText bankrollInput = new EditText(this);
        final Spinner currencyInput = new Spinner(this);
        currencyInput.setAdapter(currencyArrayAdapter);
        layout.addView(bankrollInput);
        layout.addView(currencyInput);
        bankrollInput.setInputType(InputType.TYPE_CLASS_TEXT);
        bankrollInput.setHint("Bankroll");


        builder.setTitle("Add Bankroll");
        builder.setView(layout);

        bankrolleArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allBankroll);
        listView.setAdapter(bankrolleArrayAdapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ToDo Currency
                Currency currency = (Currency)currencyInput.getSelectedItem();
                Bankroll bankroll = new Bankroll(bankrollInput.getText().toString(), currency);
                bankroll.setId(dbAdapter.CreateDbObject(bankroll));
                bankrolleArrayAdapter.add(bankroll);
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

