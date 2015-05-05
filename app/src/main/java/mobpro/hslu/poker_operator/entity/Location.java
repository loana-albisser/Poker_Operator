package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by manager on 04.05.2015.
 */
public class Location implements DbObject {
    private long id;
    private String description;
    private Currency currency = new Currency();

    public Location (){};

    public Location(String description, Currency currency) {
        this.description = description;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ID, getId());
        values.put(DbHelper.COLUMN_DESCRIPTION, getDescription());
        values.put(DbHelper.COLUMN_CURRENCY, getCurrency().getDescription());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_LOCATION;
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
