package mobpro.hslu.poker_operator.entity;

/**
 * Created by manager on 04.05.2015.
 */
public class Limittype {
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
}
