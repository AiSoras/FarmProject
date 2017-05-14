package objects;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "List_of_animals")
public class Passport {
    @Id
    private String ID;
    @Column
    private Date dateOfBirth;
    @Column
    private float weight; //В кг
    @Column
    private String breed;
    @ManyToOne(cascade = CascadeType.ALL)
    private Paddock paddock;

    public Passport() {
    }

    public Passport(Date dateOfBirth, float weight, String breed, Paddock paddock) {
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.breed = breed;
        this.paddock = paddock;
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

    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

}
