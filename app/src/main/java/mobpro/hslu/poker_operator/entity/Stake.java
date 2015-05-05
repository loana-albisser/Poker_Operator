package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by manager on 04.05.2015.
 */
public class Stake implements DbObject {
    private long id;
    private float smallBlind;
    private float bigBlind;

    public Stake (){};

    public Stake (float smallBlind, float bigBlind) {
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(float bigBlind) {
        this.bigBlind = bigBlind;
    }

    public float getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(float smallBlind) {
        this.smallBlind = smallBlind;
    }

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ID, getId());
        values.put(DbHelper.COLUMN_SMALL_BLIND, getSmallBlind());
        values.put(DbHelper.COLUMN_BIG_BLIND, getBigBlind());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_BANKROLL;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_ID;
    }

    @Override
    public String getPrimaryFieldValue() {
        return String.valueOf(getId());
    }

    public static Stake getGamesByID(String id, DbAdapter dbAdapter) {
        Stake stake = new Stake();
        stake.setId(Long.parseLong(id));
        ContentValues contentValues = dbAdapter.getByObject(stake);
        if(contentValues!= null) {
            stake = copyContentValuesToObject(contentValues, stake);
        }else{stake=null;}

        return stake;
    }

    public static Collection<Stake> getAllStakes(DbAdapter dbAdapter) {
        Collection<Stake> allStakes = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Stake().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allStakes.add(copyContentValuesToObject(contentValues, new Stake()));
            }
        }else{allStakes=null;}

        return allStakes;
    }

    private static Stake copyContentValuesToObject(ContentValues contentValues, Stake stake) {
        stake.setId(Long.parseLong(contentValues.getAsString(DbHelper.COLUMN_ID)));
        stake.setSmallBlind(Float.parseFloat(contentValues.getAsString(DbHelper.COLUMN_SMALL_BLIND)));
        stake.setBigBlind(Float.parseFloat(contentValues.getAsString(DbHelper.COLUMN_BIG_BLIND)));
        return  stake;
    }
}
