/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.Time;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author OlesiaPC
 */
@StaticMetamodel(Paddock.class)
public class Paddock_ {

    public static volatile SingularAttribute<Paddock, String> ID;
    public static volatile SingularAttribute<Paddock, Ration> ration;
    public static volatile SingularAttribute<Paddock, SpeciesOfAnimal> species;
    public static volatile SingularAttribute<Paddock, Time> time;
    public static volatile SingularAttribute<Paddock, Hangar> hangar;
}
