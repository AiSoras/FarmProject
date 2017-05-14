/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author OlesiaPC
 */
@StaticMetamodel(Hangar.class)
public class Hangar_ {

    public static volatile SingularAttribute<Hangar, String> ID;
    public static volatile SingularAttribute<Hangar, Positions> minimalLevelOfAccess;
    public static volatile SingularAttribute<Hangar, TypeOfHangar> type;
}
