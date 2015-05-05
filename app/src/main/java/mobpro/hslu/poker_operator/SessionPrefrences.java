package mobpro.hslu.poker_operator;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

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
