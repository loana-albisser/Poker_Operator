package mobpro.hslu.poker_operator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manager on 04.05.2015.
 */
public class DbHelper extends SQLiteOpenHelper{

    public DbHelper(final Context context) {
        super(context, DbAdapter.DB_NAME , null, DbAdapter.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
