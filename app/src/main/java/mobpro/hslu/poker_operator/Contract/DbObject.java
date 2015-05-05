package mobpro.hslu.poker_operator.Contract;

import android.content.ContentValues;

/**
 * Created by Andy on 05.05.2015.
 */
public interface DbObject {
    public ContentValues getContentValues();
    public String getTableName();
}
