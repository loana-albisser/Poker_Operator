package mobpro.hslu.poker_operator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
    private ArrayList<Session> sessionItem;
    private LayoutInflater inflater;



    public OverviewAdapter (Context c, ArrayList<Session> sessionItem){
        this.sessionItem = sessionItem;
        inflater = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return sessionItem.size();
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
        TextView date = (TextView)itemLayout.findViewById(R.id.entry_date);
        TextView location = (TextView)itemLayout.findViewById(R.id.entry_place);
        TextView duration = (TextView)itemLayout.findViewById(R.id.entry_duration);
        TextView stake = (TextView)itemLayout.findViewById(R.id.entry_stake);
        TextView gameType = (TextView)itemLayout.findViewById(R.id.entry_gameType);
        TextView cashout = (TextView)itemLayout.findViewById(R.id.entry_cashout);

        Session current  = sessionItem.get(position);
        date.setText(current.getStartDateTime().toString());
        location.setText(current.getLocation().toString());
        //ToDo Dauer berechnen
        duration.setText(current.getEndDateTime().toString());
        stake.setText(current.getStake().toString());
        gameType.setText(current.getGames().toString());
        cashout.setText((int) current.getCashout());





        return itemLayout;


    }
}
