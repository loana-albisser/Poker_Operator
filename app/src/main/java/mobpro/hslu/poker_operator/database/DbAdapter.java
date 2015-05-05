package mobpro.hslu.poker_operator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import mobpro.hslu.poker_operator.entity.Currency;

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

}
