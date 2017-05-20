package objects;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Ration implements Serializable{
    @Column
    private TypeOfFood food;
    @Column
    private int dose;
    @Column
    private long period; //Периодичность кормления (в милисекундах?)

    public Ration() {
    }

    public Ration(TypeOfFood food, int dose, long period) {
        this.food = food;
        this.dose = dose;
        this.period = period;
    }
    
    public TypeOfFood getFood() {
        return food;
    }

    public void setFood(TypeOfFood food) {
        this.food = food;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
       
    
}
