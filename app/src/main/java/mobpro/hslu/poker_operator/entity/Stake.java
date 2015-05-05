package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import mobpro.hslu.poker_operator.Contract.DbObject;
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
}
