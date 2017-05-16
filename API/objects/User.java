package objects;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "List_of_users")
public class User implements Serializable {

    @Id
    private String ID;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String secondName;
    @Column
    private String eMail;
    @Column
    private Positions levelOfAccess;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String token;

    public User() {
    }

    public User(String firstName, String middleName, String secondName, Positions levelOfAccess) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.levelOfAccess = levelOfAccess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Positions getLevelOfAccess() {
        return levelOfAccess;
    }

    public void setLevelOfAccess(Positions levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
