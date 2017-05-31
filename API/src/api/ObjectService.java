/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package api;

import java.util.List;
import objects.Paddock;
import objects.User;

/**
 * Handles requests related to objects.
 *
 * @author OlesiaPC
 */
public interface ObjectService {

    /**
     * Saves the object to the database
     *
     * @param object Saved object (user, hangar, paddock, animal, ration or
     * vaccination)
     */
    public void saveObject(Object object);

    /**
     * Returns the object ID
     *
     * @param object Selected object (user, hangar, paddock, animal, ration or
     * vaccination)
     * @return (String) object ID, if it exists, or null, does not exists
     */
    public String getObjectID(Object object);

    /**
     * Returns list of objects
     *
     * @param objectClass Class of required object
     * @return List of objects
     */
    public List<Object> getListOfObjects(Class objectClass);

    /**
     * Removes an object from the database
     *
     * @param object Selected object (user, hangar, paddock, animal, ration or
     * vaccination)
     */
    public void deleteObject(Object object);

    /**
     * Returns list of users that contain information like query in the fields
     * to be searched for
     *
     * @param query String, containing the search fragment
     * @return List of users
     */
    public List<User> getListOfUsersLike(String query);

    /**
     * Returns list of paddocks that contain information like query in the
     * fields to be searched for
     *
     * @param query String, containing the search fragment
     * @return List of paddocks
     */
    public List<Paddock> getListOfPaddocksLike(String query);

    /**
     * Returns the value of the generated ID
     *
     * @param symbol The letter that is added at the beginning of ID
     * @return The value of the generated ID
     */
    public String getGeneratedObjectID(char symbol);

    /**
     * Updates the object in the database
     *
     * @param object Required object
     * @return Updated object
     */
    public Object updateObject(Object object);
}
