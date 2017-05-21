package objects;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "List_of_paddocks")
public class Paddock implements Serializable {

    @Id
    private String ID;
    @Column
    private String name;
    @Embedded
    private Ration ration;
    @Column
    private SpeciesOfAnimal species; //Вид один для загона
    @Column
    private Time time; //Время последнего кормления (одинаково для всего загона)
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn (name = "Paddock_ID")
    private List<Passport> animals;
    @ManyToOne
    @JoinColumn (name = "Hangar_ID", nullable = false)
    private Hangar hangar;

    public Paddock() {
    }

    public Paddock(String name, Ration ration, SpeciesOfAnimal species, Time time, Hangar hangar) {
        this.name = name;
        this.ration = ration;
        this.species = species;
        this.time = time;
        this.hangar = hangar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Passport> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Passport> animals) {
        this.animals = animals;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }
    
    
    
}
