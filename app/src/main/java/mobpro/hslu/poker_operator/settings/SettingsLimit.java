package mobpro.hslu.poker_operator.settings;

import android.app.Activity;
import android.os.Bundle;

import mobpro.hslu.poker_operator.R;

/**
 * Created by User on 04.05.2015.
 */
public class SettingsLimit extends Activity{
    public SettingsLimit(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_limit);
    }
}

