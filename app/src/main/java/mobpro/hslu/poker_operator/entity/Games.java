package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by manager on 04.05.2015.
 */
public class Games implements DbObject {
    private long id;
    private String description;

    public Games(){};

    public Games(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_GAMES;
    }
}
