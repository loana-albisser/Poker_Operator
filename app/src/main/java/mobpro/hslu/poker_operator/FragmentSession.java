package mobpro.hslu.poker_operator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentSession extends android.support.v4.app.Fragment {

    public FragmentSession(){

    }

    @Override
    public void onResume(){
        super.onResume();
        //ToDo implement onResume
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_session, container, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //Game Prefrences
        TextView gameView = (TextView)rootView.findViewById(R.id.txt_gameType);
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

        return rootView;
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
