package mobpro.hslu.poker_operator;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Games;
import mobpro.hslu.poker_operator.entity.Limittype;
import mobpro.hslu.poker_operator.entity.Location;
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

    private ArrayList<String> allRateOptions;
    private ArrayAdapter<String> rateArrayAdapter;

    private String webURI = "http://download.finance.yahoo.com/d/quotes.csv?s=XXXYYY=X+YYYXXX=X&f=nl1d1t1";

    public FragmentSession(){

    }

    @Override
    public void onPause(){
        dbAdapter.close();
        super.onPause();
    }

    @Override
    public void onResume(){
        dbAdapter.open();
        super.onResume();
        setPreferences();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_session, container, false);
        try {
            dbAdapter = new DbAdapter(getActivity().getApplicationContext());
            dbAdapter.open();
            loadData();

        }catch (Exception e) {
            e.printStackTrace();
        }
        setPreferences();

        return rootView;
    }

    public void loadData(){
        final Spinner bankrollSpinner = (Spinner)rootView.findViewById(R.id.spinner_bankroll);
        allBankrolls = new ArrayList<>(Bankroll.getAllBankroll(dbAdapter));
        bankrollArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allBankrolls);
        bankrollSpinner.setAdapter(bankrollArrayAdapter);

        final Spinner currencySpinner = (Spinner)rootView.findViewById(R.id.spinner_currency);
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

        final Spinner locationSpinner = (Spinner)rootView.findViewById(R.id.spinner_location);
        allLocations = new ArrayList<>(Location.getAllLocation(dbAdapter));
        locationArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allLocations);
        locationSpinner.setAdapter(locationArrayAdapter);

        Spinner stakeSpinner = (Spinner)rootView.findViewById(R.id.spinner_stake);
        allStakes = new ArrayList<>(Stake.getAllStakes(dbAdapter));
        stakeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allStakes);
        stakeSpinner.setAdapter(stakeArrayAdapter);

        final Spinner rateSpinner = (Spinner)rootView.findViewById(R.id.spinner_currencyRate);
        allRateOptions = new ArrayList<String>(Arrays.asList("Manual", "yahooService"));
        rateArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, allRateOptions);
        rateSpinner.setAdapter(rateArrayAdapter);
        rateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rateArrayAdapter.getItem(position) == "yahooService")
                {
                    String webServiceURI = "";
                    String baseCurrency = bankrollArrayAdapter.getItem(bankrollSpinner.getSelectedItemPosition()).getCurrency().getDescription();
                    String targetCurrency = locationArrayAdapter.getItem(locationSpinner.getSelectedItemPosition()).getCurrency().getDescription();
                    webServiceURI = webURI.replace("XXX",baseCurrency).replace("YYY", targetCurrency);
                    ExchangeRateService exchangeRateService = new ExchangeRateService();
                    exchangeRateService.execute(webServiceURI);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

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

    private Object openHttpConnection(String urlString) {
        Object content =null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            if(httpConnection.getResponseCode()== HttpURLConnection.HTTP_OK){
                content = httpConnection.getContent();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            content=null;
        }
        return content;
    }

    private class ExchangeRateService extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls){

            String text = "";
            try {
                for (String url : urls) {
                    text = downloadText(url);
                }
            }catch (IllegalStateException ex){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    Toast.makeText(getActivity().getApplicationContext(), "Keine Internetverbindung", Toast.LENGTH_SHORT).show();
                 }
                });
            }catch (Exception e) {
                e.printStackTrace();

            }
            return text;
        }



        private String downloadText(String stringURI) throws IllegalStateException
        {
            String text = "";
            String fullText = "";
            InputStream inputStream = null;
            try{
                inputStream = (InputStream)openHttpConnection(stringURI);
                if(inputStream!=null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    while ((text = reader.readLine()) != null) {
                        String[] rowData = text.split(",");
                        fullText = rowData[1];
                        break;
                    }
                    inputStream.close();
                }else{
                    throw new IllegalStateException("Keine Internetverbindung");
                }
            }
            catch (IOException io){
                io.printStackTrace();
            }
            return fullText;
        }

        protected void onPostExecute(String result) {
            EditText rateEdit = (EditText)rootView.findViewById(R.id.edit_currencyRate);
            rateEdit.setText(result);
        }
    }





}
