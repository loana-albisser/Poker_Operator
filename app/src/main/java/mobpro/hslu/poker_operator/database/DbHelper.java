package mobpro.hslu.poker_operator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by manager on 04.05.2015.
 */
public class DbHelper extends SQLiteOpenHelper{

    public static String TABLE_CURRENCY = "CURRENCY";
    public static String TABLE_GAMES = "GAMES";
    public static String TABLE_LIMITTYPE = "LIMITTYPE";
    public static String TABLE_LOCATION = "LOCATION";
    public static String TABLE_STAKE = "STAKE";
    public static String TABLE_BANKROLL = "BANKROLL";


    public DbHelper(final Context context) {
        super(context, DbAdapter.DB_NAME , null, DbAdapter.DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            createCurrencyTable(db);
            createGameTable(db);
            createLimittype(db);
            createLocation(db);
            createStake(db);
            createBankroll(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createGameTable(SQLiteDatabase db) throws SQLException{
        String CREATE_CURRENCY_TABLE = "CREATE TABLE "+this.TABLE_CURRENCY +" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT) ";
        db.execSQL(CREATE_CURRENCY_TABLE);
    }

    private void createCurrencyTable(SQLiteDatabase db) throws SQLException{
        String CREATE_CURRENCY_TABLE = "CREATE TABLE "+this.TABLE_CURRENCY +" (" +
                "description TEXT PRIMARY KEY) ";
        db.execSQL(CREATE_CURRENCY_TABLE);
    }

    private void createLimittype(SQLiteDatabase db) throws SQLException{
        String CREATE_LIMIT_TABLE = "CREATE TABLE "+this.TABLE_LIMITTYPE +" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT) ";
        db.execSQL(CREATE_LIMIT_TABLE);
    }

    private void createLocation(SQLiteDatabase db) throws SQLException{
        String CREATE_LOCATION_TABLE = "CREATE TABLE "+this.TABLE_LOCATION +" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT, " +
                "fk_Currency TEXT) ";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    private void createStake(SQLiteDatabase db) throws SQLException{
        String CREATE_STAKE_TABLE = "CREATE TABLE "+this.TABLE_STAKE +" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "smallBlind REAL, " +
                "bigBlind REAL)";
        db.execSQL(CREATE_STAKE_TABLE);
    }

    private void createBankroll(SQLiteDatabase db) throws SQLException{
        String CREATE_BANKROLL_TABLE = "CREATE TABLE "+this.TABLE_BANKROLL +" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT, " +
                "fk_Currency TEXT) ";
        db.execSQL(CREATE_BANKROLL_TABLE);
    }

}
