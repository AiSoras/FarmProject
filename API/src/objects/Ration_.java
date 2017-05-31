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
@StaticMetamodel(Ration.class)
public class Ration_ {
    public static volatile SingularAttribute<Ration, TypeOfFood> food;
    public static volatile SingularAttribute<Ration, Integer> dose;
    public static volatile SingularAttribute<Ration, Long> period;
}
