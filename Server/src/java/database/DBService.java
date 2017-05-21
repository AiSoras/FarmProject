/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objects.User;
import objects.User_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Handles database queries from the server.
 *
 * @author OlesiaPC
 */
public class DBService {    

    private static final Logger logger = LogManager.getLogger(DBService.class.getName());

    /**
     * Creates or updates object in the database.
     *
     * @param object
     */
    public static synchronized void saveObject(Object object) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            logger.info("The object [" + object.getClass().toString() + "] has been saved or updated successfully");
            session.flush();
        }
    }

    /**
     * Returns the list of objects of which class is <code>classObject</code>.
     *
     * @param classObject
     *
     * @return The list of objects of which class is <code>classObject</code> or
     * <code>null</code> if nothing found.
     *
     */
    public static synchronized List<Object> loadObjects(Class classObject) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<Object> criteriaQuery = builder.createQuery(classObject);
            Root<Object> root = criteriaQuery.from(classObject);
            criteriaQuery.select(root);
            try {
                TypedQuery<Object> typed = session.createQuery(criteriaQuery);
                return typed.getResultList();
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return null;
            }
        }
    }

    /**
     * Deletes object with <code>ID</code> in the database.
     *
     * @param object
     * @param ID
     */
    public static synchronized void deleteObject(Object object, String ID) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            try {
                Object persistentInstance = session.load(object.getClass(), ID);
                session.remove(persistentInstance);
                session.flush();
                logger.info("The object [" + object.getClass().toString() + "] has been removed successfully");
            } catch (HibernateException e) {
                logger.error("Exception: ", e);
            }
        }
    }

    /**
     * Gets the user from the database whose {@link User#token} equal to
     * <code>token</code>.
     *
     * @param token
     * @return The user whose {@link User#token} equal to <code>token</code> or
     * <code>null</code> if nothing found.
     */
    public static synchronized User getByToken(String token) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.equal(root.get(User_.token), token));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult();
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return null;
            }
        }
    }

    /**
     * Returns the object of which ID in the database is equal to
     * <code>ID</code>.
     *
     * @param classObject Class of the object.
     * @param ID
     * @return The object of which ID in the database is equal to
     * <code>ID</code> or <code>null</code> if nothing found.
     */
    public static synchronized Object getObjectByID(Class classObject, String ID) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            Object object = session.get(classObject, ID);
            if (object == null) {
                logger.error("No result was found");
            }
            return object;
        }
    }

    /**
     * Returns the user whose {@link User.eMail} is equal to <code>eMail</code>.
     *
     * @param eMail
     * @return The user whose {@link User.eMail} is equal to <code>eMail</code>
     * or <code>null</code> if nothing found.
     */
    public static synchronized User getByEMail(String eMail) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.equal(root.get(User_.eMail), eMail));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult();
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return null;
            }
        }
    }

    /**
     * Returns the user whose {@link User#login} and {@link User#password} are
     * equal to <code>login</code> and <code>password</code>.
     *
     * @param login
     * @param password
     * @return The user whose {@link User#login} and {@link User#password} are
     * equal to <code>login</code> and <code>password</code> or
     * <code>null</code> if nothing found.
     */
    public static synchronized User getByLoginAndPassword(String login, String password) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.or(
                    builder.equal(root.get(User_.login), login),
                    builder.equal(root.get(User_.password), password)));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult();
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return null;
            }
        }
    }

    /**
     * Checks for existence in the database of the user with <code>login</code>
     * or <code>eMail</code>.
     *
     * @param login
     * @param eMail
     * @return <code>true</code> if the user with <code>login</code> or
     * <code>eMail</code> is already in the database, otherwise
     * <code>false</code>.
     */
    public static synchronized boolean isLoginOrEMailExists(String login, String eMail) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.or(
                    builder.equal(root.get(User_.login), login),
                    builder.equal(root.get(User_.eMail), eMail)));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult() != null;
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return false;
            }
        }
    }

    /**
     * Returns the list of users whose {@link User#firstName} or
     * {@link User#middleName} or {@link User#secondName} contains
     * <code>query</code>.
     *
     * @param query
     * @return The list of users whose {@link User#firstName} or
     * {@link User#middleName} or {@link User#secondName} contains
     * <code>query</code> or <code>null</code> if nothing found.
     */
    public static synchronized List<User> getUserLikeList(String query) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.or(
                    builder.like(root.<String>get(User_.firstName), "%" + query + "%"),
                    builder.like(root.<String>get(User_.secondName), "%" + query + "%"),
                    builder.like(root.<String>get(User_.middleName), "%" + query + "%")));
            criteriaQuery.orderBy(builder.asc(root.get(User_.secondName)));
//            criteriaQuery.orderBy(builder.asc(builder.coalesce(root.get(User_.secondName),root.get(User_.firstName))));
            try {
                TypedQuery<User> typed = session.createQuery(criteriaQuery);
                return typed.getResultList();
            } catch (final NoResultException ignore) {
                logger.error("Exception: ", ignore);
                return null;
            }
        }

    }
}
