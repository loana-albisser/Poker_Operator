package mobpro.hslu.poker_operator.entity;

/**
 * Created by manager on 04.05.2015.
 */
public class Stake {
    private long id;
    private float smallBlind;
    private float bigBlind;

    public Stake (){};

    public Stake (float smallBlind, float bigBlind) {
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(float bigBlind) {
        this.bigBlind = bigBlind;
    }

    public float getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(float smallBlind) {
        this.smallBlind = smallBlind;
    }
}
