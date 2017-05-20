/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author OlesiaPC
 */
@StaticMetamodel(Vaccination.class)
public class Vaccination_ {

    public static volatile SingularAttribute<Vaccination, String> ID;
    public static volatile SingularAttribute<Vaccination, String> name;
    public static volatile SingularAttribute<Vaccination, Date> dateOfVaccination;
    public static volatile SingularAttribute<Vaccination, TypeOfVaccination> type;
    public static volatile SingularAttribute<Vaccination, Date> date;
}
