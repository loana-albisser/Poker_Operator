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
public class Games implements DbObject {
    private long id;
    private String description;

    public Games(){};

    public Games(String description) {
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
        return DbHelper.TABLE_GAMES;
    }

    @Override
    public String getPrimaryFieldName() {
        return DbHelper.COLUMN_ID;
    }

    @Override
    public String getPrimaryFieldValue() {
        return String.valueOf(getId());
    }

    public static Games getGamesByID(String id, DbAdapter dbAdapter) {
        Games games= new Games(id);
        ContentValues contentValues = dbAdapter.getByObject(games);
        if(contentValues!= null) {
            games = copyContentValuesToObject(contentValues, games);
        }else{games=null;}

        return games;
    }

    public static Collection<Games> getAllGames(DbAdapter dbAdapter) {
        Collection<Games> allGames = new ArrayList<>();
        Collection<ContentValues> allContentValues = dbAdapter.getAllByTable(new Games().getTableName());
        if(allContentValues!= null) {
            for(ContentValues contentValues: allContentValues) {
                allGames.add(copyContentValuesToObject(contentValues, new Games()));
            }
        }else{allGames=null;}

        return allGames;
    }
    
    private static Games copyContentValuesToObject(ContentValues contentValues, Games games) {
        games.setId(Long.parseLong(contentValues.getAsString(DbHelper.COLUMN_ID)));
        games.setDescription(contentValues.getAsString(DbHelper.COLUMN_DESCRIPTION));
        return  games;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
