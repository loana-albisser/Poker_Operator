package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by manager on 04.05.2015.
 */
public class Bankroll implements DbObject {

    private long id;
    private String description;
    private Currency currency = new Currency();

    public Bankroll(){};

    public Bankroll(String description, Currency currency) {
        this.description = description;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ID, getId());
        values.put(DbHelper.COLUMN_DESCRIPTION, getDescription());
        values.put(DbHelper.COLUMN_CURRENCY, getCurrency().getDescription());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_BANKROLL;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_ID;
    }

    @Override
    public String getPrimaryFieldValue() {
        return String.valueOf(getId());
    }

    public static Bankroll getBankrollByID(String id, DbAdapter dbAdapter) {
        Bankroll bankroll = new Bankroll();
        bankroll.setId(Long.parseLong(id));
        ContentValues contentValues = dbAdapter.getByObject(bankroll);
        if(contentValues!= null) {
            bankroll = copyContentValuesToObject(contentValues, bankroll, dbAdapter);
        }else{bankroll=null;}

        return bankroll;
    }

    public static Collection<Bankroll> getAllBankroll(DbAdapter dbAdapter) {
        Collection<Bankroll> allBankroll = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Bankroll().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allBankroll.add(copyContentValuesToObject(contentValues, new Bankroll(), dbAdapter));
            }
        }else{allBankroll=null;}

        return allBankroll;
    }

    private static Bankroll copyContentValuesToObject(ContentValues contentValues, Bankroll bankroll,
                                                      DbAdapter dbAdapter) {
        bankroll.setId(Long.parseLong(contentValues.getAsString(DbHelper.COLUMN_ID)));
        bankroll.setDescription(contentValues.getAsString(DbHelper.COLUMN_DESCRIPTION));
        bankroll.setCurrency(Currency.getCurrencyByID(
                contentValues.getAsString(DbHelper.COLUMN_CURRENCY), dbAdapter));
        return  bankroll;
    }

    @Override
    public String toString(){
        return getDescription() + " ("+ getCurrency().getDescription()+") ";
    }
}
