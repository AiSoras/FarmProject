package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "List_of_animals")
public class Passport implements Serializable {

    @Id
    private String ID;
    @Column
    private Date dateOfBirth;
    @Column
    private float weight; //В кг
    @Column
    private String breed;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Animal_ID")
    private List<Vaccination> vaccinations;

    public Passport() {
    }

    public Passport(Date dateOfBirth, float weight, String breed) {
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

}
