package mobpro.hslu.poker_operator.entity;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Collection;

import mobpro.hslu.poker_operator.Contract.DbObject;
import mobpro.hslu.poker_operator.database.DbAdapter;
import mobpro.hslu.poker_operator.database.DbHelper;

/**
 * Created by tbeugste on 04.05.2015.
 */
public class Currency implements DbObject {

    private String description;
    public Currency(){};

    public Currency(String description) {
        this.description = description;
    };

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_DESCRIPTION, getDescription());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_CURRENCY;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_DESCRIPTION;
    }

    @Override
    public String getPrimaryFieldValue() {
        return getDescription();
    }

    public static Currency getCurrencyByID(String id, DbAdapter dbAdapter) {
        Currency currency= new Currency(id);
        ContentValues contentValues = dbAdapter.getByObject(currency);
        if(contentValues!= null) {
            currency = copyContentValuesToObject(contentValues, currency);
        }else{currency=null;}

        return currency;
    }

    public static Collection<Currency> getAllCurrency(DbAdapter dbAdapter) {
        Collection<Currency> allCurrency = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Currency().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allCurrency.add(copyContentValuesToObject(contentValues, new Currency()));
            }
        }else{allCurrency=null;}

        return allCurrency;
    }

    private static Currency copyContentValuesToObject(ContentValues contentValues, Currency currency) {
        currency.setDescription(contentValues.getAsString(DbHelper.COLUMN_DESCRIPTION));
        return  currency;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
