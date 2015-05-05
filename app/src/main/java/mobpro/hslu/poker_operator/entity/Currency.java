package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by tbeugste on 04.05.2015.
 */
public class Currency implements DbObject {

    private String description;

    public Currency(){};

    public Currency(String description) {
        this.description = description;
    };

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_DESCRIPTION, getDescription());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_CURRENCY;
    }
}
