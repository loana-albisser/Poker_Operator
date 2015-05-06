package mobpro.hslu.poker_operator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.database.DbHelper;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Games;
import mobpro.hslu.poker_operator.entity.Limittype;
import mobpro.hslu.poker_operator.entity.Location;
import mobpro.hslu.poker_operator.entity.Session;
import mobpro.hslu.poker_operator.entity.Stake;
import mobpro.hslu.poker_operator.settings.SettingsBankroll;
import mobpro.hslu.poker_operator.settings.SettingsCurrency;
import mobpro.hslu.poker_operator.settings.SettingsGames;
import mobpro.hslu.poker_operator.settings.SettingsLimit;
import mobpro.hslu.poker_operator.settings.SettingsLocation;
import mobpro.hslu.poker_operator.settings.SettingsStake;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Button btnstartDate;
    private Button btnendDate;
    private Button btnstartTime;
    private Button btnendTime;

    private EditText buyIn;
    private EditText cashout;

    private Spinner gameTpyeSpinner;
    private Spinner limitTypeSpinner;
    private Spinner stakeSpinner;
    private Spinner bankrollSpinner;
    private Spinner rateSpinner;
    private Spinner currencySpinner;
    private Spinner locationSpinner;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
        getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        PlaceholderFragment fragment = new PlaceholderFragment();




        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        //loadSpinnerData();


    }




    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();// For AppCompat use getSupportFragmentManager
        switch(position) {
            default:
            case 0:
                fragment = new FragmentSession();
                break;
            case 1:
                fragment = new FragmentOverview();
                break;
            case 2:
                fragment = new FragmentSettings();
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void loadSpinnerData(){
        DbAdapter adapter = new DbAdapter(getApplicationContext());
        //Game
        Collection<ContentValues> list = adapter.getAllByTable("Games");
        Spinner gameSpinner = (Spinner)findViewById(R.id.spinner_gameType);
        ArrayAdapter<ContentValues>arrayAdapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, (List<ContentValues>) list);
        gameSpinner.setAdapter(arrayAdapter);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void startBankroll(View v) {
        Intent intent = new Intent(this, SettingsBankroll.class);
        startActivity(intent);
    }

    public void startCurrency(View v) {
        Intent intent = new Intent(this, SettingsCurrency.class);
        startActivity(intent);
    }

    public void startGames(View v) {
        Intent intent = new Intent(this, SettingsGames.class);
        startActivity(intent);
    }

    public void startLimit(View v) {
        Intent intent = new Intent(this, SettingsLimit.class);
        startActivity(intent);
    }

    public void startLocation(View v) {
        Intent intent = new Intent(this, SettingsLocation.class);
        startActivity(intent);
    }

    public void startStake(View v) {
        Intent intent = new Intent(this, SettingsStake.class);
        startActivity(intent);
    }

    public void save (final View v){
        buyIn = (EditText)findViewById(R.id.edit_buyIn);
        cashout = (EditText)findViewById(R.id.edit_cashout);

        gameTpyeSpinner = (Spinner)findViewById(R.id.spinner_gameType);
        limitTypeSpinner = (Spinner)findViewById(R.id.spinner_limitType);
        stakeSpinner = (Spinner)findViewById(R.id.spinner_stake);
        bankrollSpinner = (Spinner)findViewById(R.id.spinner_bankroll);
        currencySpinner = (Spinner)findViewById(R.id.spinner_currency);
        rateSpinner = (Spinner)findViewById(R.id.spinner_currencyRate);
        locationSpinner = (Spinner)findViewById(R.id.spinner_location);

        DbAdapter dbAdapter = new DbAdapter(this);
        if (buyIn.getText().toString().trim().isEmpty() ||cashout.getText().toString().trim().isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please fill out at least Buy-In and Cashout");
            builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(buyIn.getText().toString().trim().isEmpty()){
                        buyIn.setFocusable(true);
                    }
                    else {
                        cashout.setFocusable(true);
                    }
                }
            });
            builder.show();
        }
        else {
            Session sessionObject = new Session();
            sessionObject.setBuyIn(Float.parseFloat(buyIn.getText().toString()));
            sessionObject.setCashout(Float.parseFloat(cashout.getText().toString()));
            sessionObject.setGames((Games) gameTpyeSpinner.getSelectedItem());
            sessionObject.setLimittype((Limittype) limitTypeSpinner.getSelectedItem());
            sessionObject.setStake((Stake) stakeSpinner.getSelectedItem());
            sessionObject.setBankroll((Bankroll) bankrollSpinner.getSelectedItem());
            sessionObject.setCurrency((Currency) currencySpinner.getSelectedItem());
            //sessionObject.setCurrencyrate((Rate) gameTpyeSpinner.getSelectedItem());
            dbAdapter.CreateDbObject(sessionObject);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Session saved");
            builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    buyIn.setFocusable(true);
                }

            });
            builder.show();
        }
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        };
       menu.add(Menu.NONE, 1, Menu.NONE, "Prefrences");
        return super.onCreateOptionsMenu(menu);*/
       getMenuInflater().inflate(R.menu.main, menu);
       menu.add(Menu.NONE, 0, 0, "Preferences");
       return true;
    }

    @Override
    public void onResume(){
        super.onResume();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        //Intent intent = new Intent(this, SessionPrefrences.class);
        //startActivity(intent);

        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
             case 0:
                 Intent intent = new Intent(this, SessionPrefrences.class);
                 startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onClick(View v) {
        btnstartDate = (Button) findViewById(R.id.btn_startDate);
        btnstartDate.setOnClickListener(this);
        btnendDate = (Button) findViewById(R.id.btn_endDate);
        btnendDate.setOnClickListener(this);
        btnstartTime = (Button) findViewById(R.id.btn_start);
        btnstartTime.setOnClickListener(this);
        btnendTime = (Button) findViewById(R.id.btn_endTime);
        btnendTime.setOnClickListener(this);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        if(v == btnstartDate){
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            btnstartDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if(v == btnendDate){
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            btnendDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            //btnstartDate.setText("hello");
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if(v == btnstartTime){
            TimePickerDialog tpd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    btnstartTime.setText(hourOfDay + ":" + minute);
                }}, mHour, mMinute, false);
            tpd.show();
        }

        if(v == btnendTime){
            TimePickerDialog tpd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    btnendTime.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            tpd.show();
        }


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        /*@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;

        }*/

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);



            return rootView;
        }
    }


}
