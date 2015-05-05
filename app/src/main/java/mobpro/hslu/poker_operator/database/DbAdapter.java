package mobpro.hslu.poker_operator.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mobpro.hslu.poker_operator.Contract.DbObject;

/**
 * Created by manager on 04.05.2015.
 */
public class DbAdapter {
    public static String DB_NAME = "Poker_Operator";
    public static int DB_Version = 1;

    private final DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbAdapter(final Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        if(db==null || !db.isOpen()) {
            db = dbHelper.getWritableDatabase();
        }
    }

    public void close() {
        dbHelper.close();
    }


    public long CreateDbObject(DbObject dbObject) {
        return db.insert(dbObject.getTableName(), null, dbObject.getContentValues());
    }

    public Cursor execQuery(String query,String[] args) {
        return db.rawQuery(query, args);
    }

    public boolean updateDbObject(DbObject dbObject) {
        return db.update(dbObject.getTableName(), dbObject.getContentValues(),
                dbObject.getPrimaryFieldName() + "=" + dbObject.getPrimaryFieldValue(), null)>0;

    }
}
