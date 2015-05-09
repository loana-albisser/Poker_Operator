package mobpro.hslu.poker_operator;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mobpro.hslu.poker_operator.R;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Session;

/**
 * Created by User on 04.05.2015.
 */
public class FragmentOverview extends android.support.v4.app.Fragment {
    private ArrayList<Session> sessionList;
    private ListView overviewView;
    private View rootView;
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Session> allSessions;
    private ArrayAdapter<Session> sessionArrayAdapter;

    public FragmentOverview() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        dbAdapter = new DbAdapter(getActivity());
        dbAdapter.open();
        setDataToOverview();
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        setDataToOverview();
    }

    public void setDataToOverview(){
        overviewView = (ListView)rootView.findViewById(R.id.listView_overview);
        ArrayList<Session>sessionList = new ArrayList<>(Session.getAllSessions(dbAdapter));

        Collections.sort(sessionList, new Comparator<Session>() {
            @Override
            public int compare(Session lhs, Session rhs) {
                return lhs.getStartDateTime().compareTo(rhs.getStartDateTime());
            }
        });
        OverviewAdapter overviewAdapter = new OverviewAdapter(getActivity(), sessionList);
        overviewView.setAdapter(overviewAdapter);

    }

}


