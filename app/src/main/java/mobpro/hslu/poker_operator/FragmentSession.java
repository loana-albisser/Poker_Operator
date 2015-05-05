package mobpro.hslu.poker_operator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentSession extends android.support.v4.app.Fragment {

    public FragmentSession(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_session, container, false);

        return rootView;

    }
}
