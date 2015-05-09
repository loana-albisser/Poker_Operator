package mobpro.hslu.poker_operator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Games;
import mobpro.hslu.poker_operator.entity.Limittype;
import mobpro.hslu.poker_operator.entity.Location;
import mobpro.hslu.poker_operator.entity.Session;
import mobpro.hslu.poker_operator.entity.Stake;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentSession extends android.support.v4.app.Fragment {

    private View rootView;
    private ListView listView;
    DbAdapter dbAdapter;

    private ArrayList<Bankroll> allBankrolls;
    private ArrayAdapter<Bankroll> bankrollArrayAdapter;

    private ArrayList<Currency> allCurrencies;
    private ArrayAdapter<Currency> currencyArrayAdapter;

    private ArrayList<Games> allGames;
    private ArrayAdapter<Games> gameArrayAdapter;

    private ArrayList<Limittype> allLimittypes;
    private ArrayAdapter<Limittype> limittypeArrayAdapter;

    private ArrayList<Location> allLocations;
    private ArrayAdapter<Location> locationArrayAdapter;

    private ArrayList<Stake> allStakes;
    private ArrayAdapter<Stake> stakeArrayAdapter;

    public FragmentSession(){

    }

    @Override
    public void onResume(){
        super.onResume();
        setPreferences();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_session, container, false);
        try {
            dbAdapter = new DbAdapter(getActivity());
            dbAdapter.open();
            loadData();

        }catch (Exception e) {
            e.printStackTrace();
        }
        setPreferences();

        return rootView;
    }

    public void loadData(){
        Spinner bankrollSpinner = (Spinner)rootView.findViewById(R.id.spinner_bankroll);
        allBankrolls = new ArrayList<>(Bankroll.getAllBankroll(dbAdapter));
        bankrollArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allBankrolls);
        bankrollSpinner.setAdapter(bankrollArrayAdapter);

        Spinner currencySpinner = (Spinner)rootView.findViewById(R.id.spinner_currency);
        allCurrencies = new ArrayList<>(Currency.getAllCurrency(dbAdapter));
        currencyArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allCurrencies);
        currencySpinner.setAdapter(currencyArrayAdapter);

        Spinner gameSpinner = (Spinner)rootView.findViewById(R.id.spinner_gameType);
        allGames = new ArrayList<>(Games.getAllGames(dbAdapter));
        gameArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allGames);
        gameSpinner.setAdapter(gameArrayAdapter);

        Spinner limitSpinner = (Spinner)rootView.findViewById(R.id.spinner_limitType);
        allLimittypes = new ArrayList<>(Limittype.getAllLimittypes(dbAdapter));
        limittypeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allLimittypes);
        limitSpinner.setAdapter(limittypeArrayAdapter);

        Spinner locationSpinner = (Spinner)rootView.findViewById(R.id.spinner_location);
        allLocations = new ArrayList<>(Location.getAllLocation(dbAdapter));
        locationArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allLocations);
        locationSpinner.setAdapter(locationArrayAdapter);

        Spinner stakeSpinner = (Spinner)rootView.findViewById(R.id.spinner_stake);
        allStakes = new ArrayList<>(Stake.getAllStakes(dbAdapter));
        stakeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allStakes);
        stakeSpinner.setAdapter(stakeArrayAdapter);
    }

    public void setPreferences(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //Game Prefrences
        Spinner gameSpinner = (Spinner)rootView.findViewById(R.id.spinner_gameType);
        String gameType = prefs.getString("gameType", "");
        gameSpinner.setSelection(getIndex(gameSpinner, gameType));

        //Limit Prefrences
        Spinner limitSpinner = (Spinner)rootView.findViewById(R.id.spinner_limitType);
        String limitType = prefs.getString("limit", "");
        limitSpinner.setSelection(getIndex(limitSpinner, limitType));

        //Stake
        Spinner stakeSpinner = (Spinner)rootView.findViewById(R.id.spinner_stake);
        String stakeType = prefs.getString("stake", "");
        stakeSpinner.setSelection(getIndex(stakeSpinner, stakeType));

        //BuyIn
        EditText buyInText = (EditText)rootView.findViewById(R.id.edit_buyIn);
        String buyInType = prefs.getString("buyIn","");
        buyInText.setText(buyInType);

        //Bankroll
        Spinner bankrollSpinner = (Spinner)rootView.findViewById(R.id.spinner_bankroll);
        String bankrollType = prefs.getString("bankroll", "");
        bankrollSpinner.setSelection(getIndex(bankrollSpinner, stakeType));

        //Cashout
        EditText cashoutText = (EditText)rootView.findViewById(R.id.edit_cashout);
        String cashoutType = prefs.getString("cashout","");
        cashoutText.setText(cashoutType);
        //Currency
        Spinner currencySpinner = (Spinner)rootView.findViewById(R.id.spinner_currency);
        String currencyType = prefs.getString("currency", "");
        currencySpinner.setSelection(getIndex(currencySpinner, currencyType));


        //CurrencyRate
        Spinner rateSpinner = (Spinner)rootView.findViewById(R.id.spinner_currencyRate);
        String rateType = prefs.getString("rate", "");
        rateSpinner.setSelection(getIndex(rateSpinner, rateType));

        //Location
        Spinner locationSpinner = (Spinner)rootView.findViewById(R.id.spinner_currency);
        String locationType = prefs.getString("location", "");
        locationSpinner.setSelection(getIndex(locationSpinner, locationType));
    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }






}
