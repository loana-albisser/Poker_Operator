package mobpro.hslu.poker_operator.entity;

/**
 * Created by manager on 04.05.2015.
 */
public class Location {

    private String description;
    private Currency currency = new Currency();

    public Location (){};

    public Location(String description, Currency currency) {
        this.description = description;
        this.currency = currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
