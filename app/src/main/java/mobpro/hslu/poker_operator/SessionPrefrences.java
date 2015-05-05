package mobpro.hslu.poker_operator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.widget.TextView;

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
        @Override
        public void onCreate (final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefrences);
        }
    }
}
