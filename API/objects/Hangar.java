package objects;

import javax.persistence.*;

@Entity
@Table(name = "List_of_hangars")
public class Hangar {

    @Id
    private String ID;
    @Column
    private Positions minimalLevelOfAccess;
    @Column
    private TypeOfHangar type;

    public Hangar() {
    }

    public Hangar(Positions minimalLevelOfAccess, TypeOfHangar type) {
        this.minimalLevelOfAccess = minimalLevelOfAccess;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Positions getMinimalLevelOfAccess() {
        return minimalLevelOfAccess;
    }

    public void setMinimalLevelOfAccess(Positions minimalLevelOfAccess) {
        this.minimalLevelOfAccess = minimalLevelOfAccess;
    }

    public TypeOfHangar getType() {
        return type;
    }

    public void setType(TypeOfHangar type) {
        this.type = type;
    }

}
