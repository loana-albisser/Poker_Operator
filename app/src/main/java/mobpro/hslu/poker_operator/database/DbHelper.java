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
    public static String TABLE_SESSION = "Session";

    public static String COLUMN_ID = "id";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_CURRENCY = "Currency";
    public static String COLUMN_SMALL_BLIND = "smallBlind";
    public static String COLUMN_BIG_BLIND = "bigBlind";
    public static String COLUMN_LIMITTYPE = "limittype";
    public static String COLUMN_GAMES = "game";
    public static String COLUMN_STAKE = "stake";
    public static String COLUMN_BUYIN = "buyin";
    public static String COLUMN_BANKROLL = "bankroll";
    public static String COLUMN_LOCATION = "location";
    public static String COLUMN_START_DATE_TIME = "startDateTime";
    public static String COLUMN_END_DATE_TIME = "endDateTime";
    public static String COLUMN_CASHOUT = "cashout";
    public static String COLUMN_CRRATE = "crrate";

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
            createSession(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createSession(SQLiteDatabase db) {
        String CREATE_SESSION_TABLE = "CREATE TABLE "+this.TABLE_SESSION + " (" +
                this.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_LIMITTYPE + " INTEGER, " +
                this.COLUMN_GAMES + " INTEGER, " +
                this.COLUMN_STAKE + " INTEGER, " +
                this.COLUMN_BUYIN + " REAL, " +
                this.COLUMN_BANKROLL + " INTEGER, " +
                this.COLUMN_LOCATION + " INTEGER, " +
                this.COLUMN_START_DATE_TIME + " TEXT, " +
                this.COLUMN_END_DATE_TIME + " TEXT, " +
                this.COLUMN_CASHOUT + " REAL, " +
                this.COLUMN_CURRENCY + " TEXT, " +
                this.COLUMN_CRRATE + " REAL)";
        db.execSQL(CREATE_SESSION_TABLE);
    }

    private void createGameTable(SQLiteDatabase db) throws SQLException{
        String CREATE_CURRENCY_TABLE = "CREATE TABLE "+this.TABLE_GAMES +" (" +
                this.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_DESCRIPTION+ " TEXT) ";
        db.execSQL(CREATE_CURRENCY_TABLE);
    }

    private void createCurrencyTable(SQLiteDatabase db) throws SQLException{
        String CREATE_CURRENCY_TABLE = "CREATE TABLE "+this.TABLE_CURRENCY +" (" +
                this.COLUMN_DESCRIPTION + " TEXT PRIMARY KEY) ";
        db.execSQL(CREATE_CURRENCY_TABLE);
    }

    private void createLimittype(SQLiteDatabase db) throws SQLException{
        String CREATE_LIMIT_TABLE = "CREATE TABLE "+this.TABLE_LIMITTYPE +" (" +
                this.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_DESCRIPTION + " TEXT) ";
        db.execSQL(CREATE_LIMIT_TABLE);
        String CREATE_INIT_VALUES = "INSERT INTO "+this.TABLE_LIMITTYPE +" VALUES ( 0, 'No Limit');";
        db.execSQL(CREATE_INIT_VALUES);

    }

    private void createLocation(SQLiteDatabase db) throws SQLException{
        String CREATE_LOCATION_TABLE = "CREATE TABLE "+this.TABLE_LOCATION +" (" +
                this.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_DESCRIPTION + " TEXT, " +
                this.COLUMN_CURRENCY + " TEXT) ";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    private void createStake(SQLiteDatabase db) throws SQLException{
        String CREATE_STAKE_TABLE = "CREATE TABLE "+this.TABLE_STAKE +" (" +
                this.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_SMALL_BLIND + " REAL, " +
                this.COLUMN_BIG_BLIND + " REAL)";
        db.execSQL(CREATE_STAKE_TABLE);
    }

    private void createBankroll(SQLiteDatabase db) throws SQLException{
        String CREATE_BANKROLL_TABLE = "CREATE TABLE "+this.TABLE_BANKROLL +" (" +
                this.COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                this.COLUMN_DESCRIPTION + " TEXT, " +
                this.COLUMN_CURRENCY + " TEXT) ";
        db.execSQL(CREATE_BANKROLL_TABLE);
    }


}
