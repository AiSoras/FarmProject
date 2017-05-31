package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Contains information about animal and methods of working with it (set and get)
 * 
 * @author OlesiaPC
 * @author BG
 */

@Entity
@Table(name = "List_of_animals")
public class Animal implements Serializable {

    @Id
    private String ID;
    @Column
    private String name;
    @Column
    private Date dateOfBirth;
    @Column
    private float weight; //В кг
    @Column
    private String breed;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Animal_ID", nullable = false)
    private List<Vaccination> vaccinations;

    public Animal() {
    }

    public Animal(String name, Date dateOfBirth, float weight, String breed) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.weight = weight;
    this.breed = breed;
    this.vaccinations = new ArrayList<Vaccination>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }
    
    public void addVaccination(Vaccination vaccination) {
        this.vaccinations.add(vaccination);
    }
}