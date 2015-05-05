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
public class Limittype implements DbObject {
    private  long id;
    private String description;

    public Limittype(){};

    public Limittype(String description) {
        this.description = description;
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

    @Override
    public ContentValues getContentValues() {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ID, getId());
        values.put(DbHelper.COLUMN_DESCRIPTION, getDescription());
        return values;
    }

    @Override
    public String getTableName() {
        return DbHelper.TABLE_LIMITTYPE;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_ID;
    }

    @Override
    public String getPrimaryFieldValue() {
        return String.valueOf(getId());
    }

    public static Limittype getLimittypeByID(String id, DbAdapter dbAdapter) {
        Limittype limittype = new Limittype(id);
        ContentValues contentValues = dbAdapter.getByObject(limittype);
        if(contentValues!= null) {
            limittype = copyContentValuesToObject(contentValues, limittype);
        }else{limittype=null;}

        return limittype;
    }

    public static Collection<Limittype> getAllLimittypes(DbAdapter dbAdapter) {
        Collection<Limittype> allLimittype = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Limittype().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allLimittype.add(copyContentValuesToObject(contentValues, new Limittype()));
            }
        }else{allLimittype=null;}

        return allLimittype;
    }

    private static Limittype copyContentValuesToObject(ContentValues contentValues, Limittype limittype) {
        limittype.setId(Long.parseLong(contentValues.getAsString(DbHelper.COLUMN_ID)));
        limittype.setDescription(contentValues.getAsString(DbHelper.COLUMN_DESCRIPTION));
        return  limittype;
    }
}
