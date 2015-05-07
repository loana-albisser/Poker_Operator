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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Location;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsLocation extends Activity{
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Location> allLocations;
    private ArrayAdapter<Location> locationArrayAdapter;

    public SettingsLocation(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
            setContentView(R.layout.settings_location);
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

                final Location location = locationArrayAdapter.getItem(position);

                final EditText locationInput = new EditText(context);
                locationInput.setText(location.getDescription());
                final Spinner currencyInput = new Spinner(context);
                currencyInput.setSelection(getSelectedId(location.getCurrency(), currencyInput));
                currencyInput.setAdapter(currencyArrayAdapter);

                layout.addView(locationInput);
                layout.addView(currencyInput);
                locationInput.setInputType(InputType.TYPE_CLASS_TEXT);
                locationInput.setHint("Location");


                builder.setTitle("Edit Location");
                builder.setView(layout);


                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        location.setDescription(locationInput.getText().toString());
                        location.setCurrency(currencyArrayAdapter.getItem(currencyInput.getSelectedItemPosition()));
                        dbAdapter.updateDbObject(location);
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
                        dbAdapter.deleteDbObject(location);
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
        listView = (ListView)findViewById(R.id.listView_location);

        allLocations = new ArrayList<>(Location.getAllLocation(dbAdapter));

        locationArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allLocations);
        listView.setAdapter(locationArrayAdapter);
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

    public void addLocation (View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        final LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        ArrayList<Currency> allCurrencys = new ArrayList<>(Currency.getAllCurrency(dbAdapter));
        ArrayAdapter<Currency> currencyArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allCurrencys);

        final EditText LocationInput = new EditText(this);
        final Spinner currencyInput = new Spinner(this);
        currencyInput.setAdapter(currencyArrayAdapter);
        layout.addView(LocationInput);
        layout.addView(currencyInput);
        LocationInput.setInputType(InputType.TYPE_CLASS_TEXT);
        LocationInput.setHint("Location");


        builder.setTitle("Add Location");
        builder.setView(layout);

        locationArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allLocations);
        listView.setAdapter(locationArrayAdapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Currency currency = (Currency) currencyInput.getSelectedItem();
                Location location = new Location(LocationInput.getText().toString(), currency);
                location.setId(dbAdapter.CreateDbObject(location));
                locationArrayAdapter.add(location);
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

