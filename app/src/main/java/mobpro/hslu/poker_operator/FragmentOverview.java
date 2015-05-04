package mobpro.hslu.poker_operator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentOverview extends android.support.v4.app.Fragment {
    public FragmentOverview(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        return rootView;
    }
}
