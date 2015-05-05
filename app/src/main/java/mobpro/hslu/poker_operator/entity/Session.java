package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by Andy on 05.05.2015.
 */
public class Session implements DbObject {
    private long id;
    private Limittype limittype = new Limittype();
    private Games games = new Games();
    private Stake stake = new Stake();
    private float buyIn;
    private Bankroll bankroll = new Bankroll();
    private Location location = new Location();
    private Date startDateTime;
    private Date endDateTime;
    private float cashout;
    private Currency currency = new Currency();
    private float currencyrate;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm");

    public Session() {
        simpleDateFormat.setCalendar(new GregorianCalendar());
    };

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLimittype(Limittype limittype) {
        this.limittype = limittype;
    }

    public Limittype getLimittype() {
        return limittype;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public Games getGames() {
        return games;
    }

    public void setStake(Stake stake) {
        this.stake = stake;
    }

    public Stake getStake() {
        return stake;
    }

    public void setBuyIn(float buyIn) {
        this.buyIn = buyIn;
    }

    public float getBuyIn() {
        return buyIn;
    }

    public void setBankroll(Bankroll bankroll) {
        this.bankroll = bankroll;
    }

    public Bankroll getBankroll() {
        return bankroll;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setCashout(float cashout) {
        this.cashout = cashout;
    }

    public float getCashout() {
        return cashout;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrencyrate(float currencyrate) {
        this.currencyrate = currencyrate;
    }

    public float getCurrencyrate() {
        return currencyrate;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ID, getId());
        values.put(DbHelper.COLUMN_LIMITTYPE, getLimittype().getId());
        values.put(DbHelper.COLUMN_GAMES, getGames().getId());
        values.put(DbHelper.COLUMN_STAKE, getStake().getId());
        values.put(DbHelper.COLUMN_BUYIN, getBuyIn());
        values.put(DbHelper.COLUMN_BANKROLL, getBankroll().getId());
        values.put(DbHelper.COLUMN_LOCATION, getLocation().getId());
        values.put(DbHelper.COLUMN_START_DATE_TIME, simpleDateFormat.format(getStartDateTime()));
        values.put(DbHelper.COLUMN_END_DATE_TIME, simpleDateFormat.format(getEndDateTime()));
        values.put(DbHelper.COLUMN_CASHOUT, getCashout());
        values.put(DbHelper.COLUMN_CURRENCY, getCurrency().getDescription());
        values.put(DbHelper.COLUMN_CRRATE, getCurrencyrate());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_SESSION;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_ID;
    }

    @Override
    public String getPrimaryFieldValue() {
        return String.valueOf(getId());
    }

    public static Session getSessionByID(String id, DbAdapter dbAdapter) {
        Session session = new Session();
        session.setId(Long.parseLong(id));
        ContentValues contentValues = dbAdapter.getByObject(session);
        if(contentValues!= null) {
            session = copyContentValuesToObject(contentValues, session, dbAdapter);
        }else{session=null;}

        return session;
    }

    public static Collection<Session> getAllSessions(DbAdapter dbAdapter) {
        Collection<Session> allSessions = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Session().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allSessions.add(copyContentValuesToObject(contentValues, new Session(), dbAdapter));
            }
        }else{allSessions=null;}

        return allSessions;
    }

    private static Session copyContentValuesToObject(ContentValues contentValues, Session session,
                                                   DbAdapter dbAdapter) {
        session.setId(Long.parseLong(contentValues.getAsString(DbHelper.COLUMN_ID)));
        session.setLimittype(Limittype.getLimittypeByID(
                contentValues.getAsString(DbHelper.COLUMN_LIMITTYPE), dbAdapter));
        session.setGames(Games.getGamesByID(
                contentValues.getAsString(DbHelper.COLUMN_GAMES), dbAdapter));
        session.setStake(Stake.getGamesByID(
                contentValues.getAsString(DbHelper.COLUMN_STAKE), dbAdapter));
        session.setBuyIn(Float.parseFloat(
                contentValues.getAsString(DbHelper.COLUMN_BUYIN)));
        session.setBankroll(Bankroll.getBankrollByID(
                contentValues.getAsString(DbHelper.COLUMN_BANKROLL), dbAdapter));
        session.setLocation(Location.getLocationsByID(
                contentValues.getAsString(DbHelper.COLUMN_LOCATION), dbAdapter));
        Date date = null;
        try {
            date = simpleDateFormat.parse(
                    contentValues.getAsString(DbHelper.COLUMN_START_DATE_TIME));
        }catch (ParseException pe) {
            date = null;
        }

        try {
            date = simpleDateFormat.parse(
                    contentValues.getAsString(DbHelper.COLUMN_END_DATE_TIME));
        }catch (ParseException pe) {
            date = null;
        }
        session.setEndDateTime(date);
        session.setCashout(Float.parseFloat(
                contentValues.getAsString(DbHelper.COLUMN_CASHOUT)));
        session.setCurrency(Currency.getCurrencyByID(
                contentValues.getAsString(DbHelper.COLUMN_CURRENCY),dbAdapter));
        session.setCurrencyrate(Float.parseFloat(
                contentValues.getAsString(DbHelper.COLUMN_CRRATE)));
        return  session;
    }
}
