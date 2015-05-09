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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private LayoutInflater inflater;
    DbAdapter dbAdapter;
    private ArrayList<Session>sessions;

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
        LinearLayout itemLayout = (LinearLayout)inflater.inflate(R.layout.overview_item, parent, false);
        TextView startDateEntry = (TextView)itemLayout.findViewById(R.id.entry_startDate);
        TextView endDateEntry = (TextView)itemLayout.findViewById(R.id.entry_endDate);
        TextView locationEntry = (TextView)itemLayout.findViewById(R.id.entry_place);
        TextView stakeEntry = (TextView)itemLayout.findViewById(R.id.entry_stake);
        TextView gameEntry = (TextView)itemLayout.findViewById(R.id.entry_gameType);
        TextView cashoutEntry = (TextView)itemLayout.findViewById(R.id.entry_cashout);
        TextView sessionEntry = (TextView)itemLayout.findViewById(R.id.entry_session);

        Session current  = sessions.get(position);

        itemLayout.setTag(position);

        cashoutEntry.setText(String.valueOf(current.getCashout()));
        startDateEntry.setText(current.getStartDateTime().toString());
        endDateEntry.setText(current.getEndDateTime().toString());
        gameEntry.setText(current.getGames().toString());
        locationEntry.setText(current.getLocation().toString());
        stakeEntry.setText(current.getStake().toString());
        sessionEntry.setText("Session: " + current.getId());

        return itemLayout;
    }

}
