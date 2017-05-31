package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Contains information about paddock and methods of working with it (set and get)
 * 
 * @author OlesiaPC
 * @author BG
 */

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
    private SpeciesOfAnimal species;
    @Column
    private Date date;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Paddock_ID", nullable = false)
    private List<Animal> animals;

    public Paddock() {
    }

    public Paddock(String name, Ration ration, SpeciesOfAnimal species, Date date) {
        this.name = name;
        this.ration = ration;
        this.species = species;
        this.date = date;
        this.animals = new ArrayList<Animal>();
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

    public Date getTime() {
        return date;
    }

    public void setTime(Date time) {
        this.date = time;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
}
