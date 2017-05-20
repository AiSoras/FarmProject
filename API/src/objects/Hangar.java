package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "List_of_hangars")
public class Hangar implements Serializable {

    @Id
    private String ID;
    @Column
    private String name;
    @Column
    private Positions minimalLevelOfAccess;
    @Column
    private TypeOfHangar type;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "Hangar_ID")
    private List<Paddock> paddocks;

    public Hangar() {
    }

    public Hangar(String name, Positions minimalLevelOfAccess, TypeOfHangar type) {
        this.name = name;
        this.minimalLevelOfAccess = minimalLevelOfAccess;
        this.type = type;
        this.paddocks = new ArrayList<Paddock>();
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

    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> paddocks) {
        this.paddocks = paddocks;
    }
    
    public void addPaddock(Paddock paddock){
        this.paddocks.add(paddock);
    }

}
