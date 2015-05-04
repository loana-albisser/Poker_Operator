package mobpro.hslu.poker_operator.entity;

/**
 * Created by tbeugste on 04.05.2015.
 */
public class Currency {

    private String description;

    public Currency(){};

    public Currency(String description) {
        this.description = description;
    };

    public String description() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
