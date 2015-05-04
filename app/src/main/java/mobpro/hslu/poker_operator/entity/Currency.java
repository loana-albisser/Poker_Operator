package mobpro.hslu.poker_operator.entity;

/**
 * Created by tbeugste on 04.05.2015.
 */
public class Currency {
    private String id;

    public Currency(){};

    public Currency(String id) {
        this.id = id;
    };

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
