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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Currency;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsCurrency extends Activity  {
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Currency> allCurrencies;
    private ArrayAdapter<Currency> currencyArrayAdapter;

    public SettingsCurrency(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
            setContentView(R.layout.settings_currency);
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
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                final Currency currency = currencyArrayAdapter.getItem(position);
                input.setText(currency.getDescription());

                builder.setTitle("Edit Currency");
                builder.setView(input);

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currency.setDescription(input.getText().toString());
                        dbAdapter.updateDbObject(currency);
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
                        dbAdapter.deleteDbObject(currency);
                        updateList();
                    }
                });
                builder.show();
            }
        });
    }

    private void updateList() {
        listView = (ListView)findViewById(R.id.listView_currney);

        allCurrencies = new ArrayList<>(Currency.getAllCurrency(dbAdapter));

        currencyArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allCurrencies);
        listView.setAdapter(currencyArrayAdapter);
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

    public void addCurrency (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Add Currency");
        builder.setView(input);

        currencyArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allCurrencies);
        listView.setAdapter(currencyArrayAdapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Currency currency = new Currency(input.getText().toString());
                //ToDO Currency setID?
                //currency.setId(dbAdapter.CreateDbObject(currency));
                currency.setDescription(String.valueOf(dbAdapter.CreateDbObject(currency)));
                currencyArrayAdapter.add(currency);
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

