package mobpro.hslu.poker_operator;

import android.app.Activity;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Games;
import mobpro.hslu.poker_operator.entity.Limittype;
import mobpro.hslu.poker_operator.entity.Location;
import mobpro.hslu.poker_operator.entity.Session;
import mobpro.hslu.poker_operator.entity.Stake;

/**
 * Created by User on 05.05.2015.
 */
public class SessionPrefrences extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SessionPreferenceInitializer()).commit();
    }

    private class SessionPreferenceInitializer extends PreferenceFragment {
        DbAdapter dbAdapter;
        private ArrayList<Bankroll> allBankrolls;
        private ArrayList<Currency> allCurrencies;
        private ArrayList<Games> allGames;
        private ArrayList<Limittype> allLimittypes;
        private ArrayList<Location> allLocations;
        private ArrayList<Session> allSessions;
        private ArrayList<Stake>allStakes;




        @Override
        public void onCreate (final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            dbAdapter = new DbAdapter(getActivity().getApplicationContext());
            dbAdapter.open();

            addPreferencesFromResource(R.xml.preferences);

            //Bankroll
            ListPreference bankrollList = (ListPreference)findPreference("bankroll");
            allBankrolls = new ArrayList<>(Bankroll.getAllBankroll(dbAdapter));
            List<String>bankrollEntries = new ArrayList<>();
            List<String>bankrollEntryValues = new ArrayList<>();
            for (Bankroll bankroll : allBankrolls){
                bankrollEntries.add(bankroll.toString());
                bankrollEntryValues.add(bankroll.toString());
            }
            final CharSequence[]bankrollEntryChar = bankrollEntries.toArray(new CharSequence[bankrollEntries.size()]);
            final CharSequence[]bankrollEntryValChar = bankrollEntryValues.toArray(new CharSequence[bankrollEntryValues.size()]);

            bankrollList.setEntries(bankrollEntryChar);
            bankrollList.setEntryValues(bankrollEntryValChar);

            //Currency
            ListPreference currencyList = (ListPreference)findPreference("currency");
            allCurrencies = new ArrayList<>(Currency.getAllCurrency(dbAdapter));
            List<String>currencyEntries = new ArrayList<>();
            List<String>currencyEntryValues = new ArrayList<>();
            for (Currency currency : allCurrencies){
                currencyEntries.add(currency.toString());
                currencyEntryValues.add(currency.toString());
            }
            final CharSequence[]currencyEntryChar = currencyEntries.toArray(new CharSequence[currencyEntries.size()]);
            final CharSequence[]currencyEntryValChar = currencyEntryValues.toArray(new CharSequence[currencyEntryValues.size()]);

            currencyList.setEntries(currencyEntryChar);
            currencyList.setEntryValues(currencyEntryValChar);

            //Games
            ListPreference gameList = (ListPreference)findPreference("gameType");
            allGames = new ArrayList<>(Games.getAllGames(dbAdapter));
            List<String>gameEntries = new ArrayList<>();
            List<String>gameEntryValues = new ArrayList<>();
            for (Games games : allGames){
                gameEntries.add(games.toString());
                gameEntryValues.add(games.toString());
            }
            final CharSequence[]gameEntryChar = gameEntries.toArray(new CharSequence[gameEntries.size()]);
            final CharSequence[]gameEntryValChar = gameEntryValues.toArray(new CharSequence[gameEntryValues.size()]);

            gameList.setEntries(gameEntryChar);
            gameList.setEntryValues(gameEntryValChar);

            //Limittype
            ListPreference limitList = (ListPreference)findPreference("limit");
            allLimittypes = new ArrayList<>(Limittype.getAllLimittypes(dbAdapter));
            List<String>limitEntries = new ArrayList<>();
            List<String>limitEntryValues = new ArrayList<>();
            for (Limittype limit : allLimittypes){
                limitEntries.add(limit.toString());
                limitEntryValues.add(limit.toString());
            }
            final CharSequence[]limitEntryChar = limitEntries.toArray(new CharSequence[limitEntries.size()]);
            final CharSequence[]limitEntryValChar = limitEntryValues.toArray(new CharSequence[limitEntryValues.size()]);

            limitList.setEntries(limitEntryChar);
            limitList.setEntryValues(limitEntryValChar);

            //Location
            ListPreference locationList = (ListPreference)findPreference("location");
            allLocations = new ArrayList<>(Location.getAllLocation(dbAdapter));
            List<String>locationEntries = new ArrayList<>();
            List<String>locationEntryValues = new ArrayList<>();
            for (Location location : allLocations){
                locationEntries.add(location.toString());
                locationEntryValues.add(location.toString());
            }
            final CharSequence[]locationEntryChar = locationEntries.toArray(new CharSequence[locationEntries.size()]);
            final CharSequence[]locationEntryValChar = locationEntryValues.toArray(new CharSequence[locationEntryValues.size()]);

            locationList.setEntries(locationEntryChar);
            locationList.setEntryValues(locationEntryValChar);

            //Stake
            ListPreference stakeList = (ListPreference)findPreference("stake");
            allStakes = new ArrayList<>(Stake.getAllStakes(dbAdapter));
            List<String>stakeEntries = new ArrayList<>();
            List<String>stakeEntryValues = new ArrayList<>();
            for (Stake stake : allStakes){
                stakeEntries.add(stake.toString());
                stakeEntryValues.add(stake.toString());
            }
            final CharSequence[]stakeEntryChar = stakeEntries.toArray(new CharSequence[stakeEntries.size()]);
            final CharSequence[]stakeEntryValChar = stakeEntryValues.toArray(new CharSequence[stakeEntryValues.size()]);

            stakeList.setEntries(stakeEntryChar);
            stakeList.setEntryValues(stakeEntryValChar);
        }
    }
}
