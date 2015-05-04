package mobpro.hslu.poker_operator.entity;

/**
 * Created by manager on 04.05.2015.
 */
public class Limittype {
    private String description;

    public Limittype(){};

    public Limittype(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
