package mobpro.hslu.poker_operator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.entity.Bankroll;
import mobpro.hslu.poker_operator.entity.Currency;
import mobpro.hslu.poker_operator.entity.Limittype;
import mobpro.hslu.poker_operator.entity.Location;
import mobpro.hslu.poker_operator.entity.Session;

/**
 * Created by User on 06.05.2015.
 */
public class OverviewAdapter extends BaseAdapter {
    //private ArrayList<Session> sessionItem;
    private LayoutInflater inflater;
    private ListView listView;
    DbAdapter dbAdapter;
    private ArrayList<Session>sessions;
    private ArrayAdapter<Session> sessionArrayAdapter;



    public OverviewAdapter (Context c, ArrayList<Session> sessions){
        this.sessions = sessions;
        inflater = LayoutInflater.from(c);
        dbAdapter = new DbAdapter(c);
        dbAdapter.open();

    }

    @Override
    public int getCount() {
        return sessions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemLayout = (LinearLayout)inflater.inflate(R.layout.test_item, parent, false);
        //TextView dateEntry = (TextView)itemLayout.findViewById(R.id.entry_date);
        //TextView locationEntry = (TextView)itemLayout.findViewById(R.id.entry_place);
        //TextView durationEntry = (TextView)itemLayout.findViewById(R.id.entry_duration);
        //TextView stakeEntry = (TextView)itemLayout.findViewById(R.id.entry_stake);
        //TextView gameEntry = (TextView)itemLayout.findViewById(R.id.entry_gameType);
        //TextView cashoutEntry = (TextView)itemLayout.findViewById(R.id.entry_cashout);

        TextView gameTest = (TextView)itemLayout.findViewById(R.id.entry_gameTest);
        TextView currencyTest = (TextView)itemLayout.findViewById(R.id.entry_currencyTest);

        //sessions = new ArrayList<>(Session.getAllSessions(dbAdapter));

        Session current  = sessions.get(position);
        gameTest.setText(current.getGames().toString());
        currencyTest.setText(current.getCurrency().toString());

        itemLayout.setTag(position);
        /*long end = Long.parseLong(current.getEndDateTime().toString());
        long start = Long.parseLong(current.getStartDateTime().toString());
        cashoutEntry.setText(String.valueOf(current.getCashout()));
        dateEntry.setText(current.getStartDateTime().toString());
        durationEntry.setText((int) (end-start));
        gameEntry.setText(current.getGames().toString());
        locationEntry.setText(current.getLocation().toString());
        stakeEntry.setText(current.getStake().toString());*/


        return itemLayout;


    }
}
