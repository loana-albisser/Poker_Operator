package mobpro.hslu.poker_operator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.entity.Session;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentOverview extends android.support.v4.app.Fragment {
    private ArrayList<Session> sessionList;
    private ListView overviewView;
    public FragmentOverview(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        overviewView = (ListView) rootView.findViewById(R.id.listView_overview);
        sessionList = new ArrayList<>();

        OverviewAdapter overviewAdapter = new OverviewAdapter(getActivity(), sessionList);
        overviewView.setAdapter(overviewAdapter);

        return rootView;
    }


}
