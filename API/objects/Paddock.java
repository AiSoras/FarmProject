package objects;

import java.sql.Time;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="List_of_paddocks")
public class Paddock {
    @Id
    private String ID;
    @Embedded
    private Ration ration;
    @Column
    private SpeciesOfAnimal species; //Вид один для загона
    @Column
    private Time time; //Время последнего кормления (одинаково для всего загона)
    @ManyToOne(cascade = CascadeType.ALL)
    private Hangar hangar;

    public Paddock() {
    }

    public Paddock(Ration ration, SpeciesOfAnimal species, Time time, Hangar hangar) {
        this.ration = ration;
        this.species = species;
        this.time = time;
        this.hangar = hangar;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Ration getRation() {
        return ration;
    }

    public void setRation(Ration ration) {
        this.ration = ration;
    }

    public SpeciesOfAnimal getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesOfAnimal species) {
        this.species = species;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }
    
    
    
}
