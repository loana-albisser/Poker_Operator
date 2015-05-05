package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import java.sql.Time;

import java.util.Date;

import mobpro.hslu.poker_operator.Contract.DbObject;
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
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private float cashout;
    private Currency currency = new Currency();
    private float currencyrate;

    public Session(){};

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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getEndTime() {
        return endTime;
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
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_SESSION;
    }
}
