package mobpro.hslu.poker_operator.entity;

/**
 * Created by manager on 04.05.2015.
 */
public class Bankroll {

    private String description;
    private Currency currency = new Currency();

    public Bankroll(){};


    public Bankroll(String description, Currency currency) {
        this.description = description;
        this.currency = currency;
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
}
