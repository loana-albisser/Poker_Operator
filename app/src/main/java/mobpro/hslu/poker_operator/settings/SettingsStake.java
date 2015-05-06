package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Stake;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsStake extends Activity{
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Stake> allStakes;
    private ArrayAdapter<Stake> stakeArrayAdapter;

    public SettingsStake(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
            setContentView(R.layout.settings_stake);
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

                final Stake stake = stakeArrayAdapter.getItem(position);


                final LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText bigblind = new EditText(context);
                final EditText smallblind = new EditText(context);

                bigblind.setText(String.valueOf(stake.getBigBlind()));
                smallblind.setText(String.valueOf(stake.getSmallBlind()));

                layout.addView(bigblind);
                layout.addView(smallblind);
                bigblind.setInputType(InputType.TYPE_CLASS_NUMBER);
                bigblind.setHint("big blind");

                smallblind.setInputType(InputType.TYPE_CLASS_NUMBER);
                smallblind.setHint("small blind");

                builder.setTitle("Add Stake");
                builder.setView(layout);


                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stake.setBigBlind(Float.parseFloat(bigblind.getText().toString()));
                        stake.setSmallBlind(Float.parseFloat(smallblind.getText().toString()));
                        dbAdapter.updateDbObject(stake);
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
                        dbAdapter.deleteDbObject(stake);
                        updateList();
                    }
                });
                builder.show();
            }
        });
    }

    private void updateList() {
        listView = (ListView)findViewById(R.id.listView_stake);

        allStakes = new ArrayList<>(Stake.getAllStakes(dbAdapter));

        stakeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allStakes);
        listView.setAdapter(stakeArrayAdapter);
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

    public void addStake (View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText bigblind = new EditText(this);
        final EditText smallblind = new EditText(this);

        layout.addView(bigblind);
        layout.addView(smallblind);
        bigblind.setInputType(InputType.TYPE_CLASS_NUMBER);
        bigblind.setHint("big blind");

        smallblind.setInputType(InputType.TYPE_CLASS_NUMBER);
        smallblind.setHint("small blind");

        builder.setTitle("Add Stake");
        builder.setView(layout);

        stakeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allStakes);
        listView.setAdapter(stakeArrayAdapter);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Stake stake = new Stake(Float.parseFloat(smallblind.getText().toString()), Float.parseFloat(bigblind.getText().toString()) );
                stake.setId(dbAdapter.CreateDbObject(stake));
                stakeArrayAdapter.add(stake);
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

