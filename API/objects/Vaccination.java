package objects;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "List_of_vaccinations")
public class Vaccination {

    @Id
    private String ID;
    @Column
    private String name;
    @Column
    private Date dateOfVaccination;
    @Column
    private TypeOfVaccination type;
    @Column
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Passport animal;

    public Vaccination() {
    }

    public Vaccination(String name, Date dateOfVaccination, TypeOfVaccination type, Date date, Passport animal) {
        this.name = name;
        this.dateOfVaccination = dateOfVaccination;
        this.type = type;
        this.date = date;
        this.animal = animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(Date dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }

    public TypeOfVaccination getType() {
        return type;
    }

    public void setType(TypeOfVaccination type) {
        this.type = type;
    }

    public Passport getAnimal() {
        return animal;
    }

    public void setAnimal(Passport animal) {
        this.animal = animal;
    }

}
