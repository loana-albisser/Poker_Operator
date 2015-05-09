package mobpro.hslu.poker_operator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import mobpro.hslu.poker_operator.database.DbAdapter;
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

    private final Calendar c = Calendar.getInstance();
    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);
    private int mHour = c.get(Calendar.HOUR_OF_DAY);
    private int mMinute = c.get(Calendar.MINUTE);

    private Date startDate;
    private Date endDate;

    private EditText buyIn;
    private EditText cashout;

    private Spinner listGameType;
    private Spinner listtLimitType;
    private Spinner listStake;
    private Spinner listCashout;
    private Spinner listCurrency;
    private EditText listRate;
    private Spinner listBankroll;
    private Spinner listLocation;

    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
        getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        this.deleteDatabase(DbAdapter.DB_NAME);
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

    public void saveSession (final View v){
        buyIn = (EditText)findViewById(R.id.edit_buyIn);
        cashout = (EditText)findViewById(R.id.edit_cashout);

        btnstartDate = (Button)findViewById(R.id.btn_startDate);
        btnstartTime = (Button)findViewById(R.id.btn_start);
        btnendDate = (Button)findViewById(R.id.btn_endDate);
        btnendTime = (Button)findViewById(R.id.btn_endTime);

        listGameType = (Spinner)findViewById(R.id.spinner_gameType);
        listtLimitType = (Spinner)findViewById(R.id.spinner_limitType);
        listStake = (Spinner)findViewById(R.id.spinner_stake);
        listBankroll = (Spinner)findViewById(R.id.spinner_bankroll);
        listCurrency = (Spinner)findViewById(R.id.spinner_currency);
        listRate = (EditText)findViewById(R.id.edit_currencyRate);
        listLocation = (Spinner)findViewById(R.id.spinner_location);

        Bankroll bankrollValue = (Bankroll)listBankroll.getSelectedItem();
        Currency currencyValue = (Currency)listCurrency.getSelectedItem();
        Games gameValue = (Games)listGameType.getSelectedItem();
        Limittype limitValue = (Limittype)listtLimitType.getSelectedItem();
        Location locationValue = (Location)listLocation.getSelectedItem();
        Stake stakeValue = (Stake)listStake.getSelectedItem();
        Float currencyRate = Float.parseFloat(listRate.getText().toString());


        String startDateText = btnstartDate.getText().toString();
        String startTimeText = btnstartTime.getText().toString();
        String endDateText = btnendDate.getText().toString();
        String endTimeText = btnendTime.getText().toString();

        String startDateString = startDateText+" "+startTimeText;
        String endDateString= endDateText+" " +endTimeText;

        boolean emptyStartDate = "Start Date".equals(startDateText);
        boolean emptyStartTime = "Start Time".equals(startTimeText);
        boolean emptyEndDate = "End Date".equals(endDateText);
        boolean emptyEndTime = "End Time".equals(endTimeText);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {

            if (buyIn.getText().toString().trim().isEmpty() ||cashout.getText().toString().trim().isEmpty()||emptyStartDate||emptyStartTime||emptyEndDate||emptyEndTime){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Please fill in all Values!");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                startDate = format.parse(startDateString);
                endDate = format.parse(endDateString);
                final Session session = new Session();
                session.setBankroll(bankrollValue);
                session.setCurrency(currencyValue);
                session.setGames(gameValue);
                session.setLimittype(limitValue);
                session.setLocation(locationValue);
                session.setStake(stakeValue);
                session.setBuyIn(Float.parseFloat(buyIn.getText().toString()));
                session.setCashout(Float.parseFloat(cashout.getText().toString()));
                session.setStartDateTime(startDate);
                session.setEndDateTime(endDate);
                session.setCurrencyrate(currencyRate);

                dbAdapter.CreateDbObject(session);

                builder.setMessage("Session saved");
                builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buyIn.setText("");
                        cashout.setText("");
                        buyIn.setFocusable(true);
                    }
                });
                builder.show();
            }

        }
        catch (ParseException e){
            e.getMessage();
        }
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);
       menu.add(Menu.NONE, 0, 0, "Preferences");
       return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement

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

        if(v == btnstartDate){
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            btnstartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if(v == btnendDate){
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            btnendDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }


                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if(v == btnstartTime){
            TimePickerDialog tpd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    if (minute <10){
                        btnstartTime.setText(hourOfDay + ":0" + minute);
                    }
                    else
                    {
                        btnstartTime.setText(hourOfDay + ":" + minute);
                    }

                }}, mHour, mMinute, false);
            tpd.show();
        }

        if(v == btnendTime){
            TimePickerDialog tpd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                    if (minute <10){
                        btnendTime.setText(hourOfDay + ":0" + minute);
                    }
                    else {
                        btnendTime.setText(hourOfDay + ":" + minute);
                    }
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


            //view.setText("hallo");
            return rootView;
        }
    }


}
