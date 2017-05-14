/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import api.ObjectService;
import implementations.ObjectServiceImplementation;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objects.Positions;
import objects.User;
import objects.User_;
import org.hibernate.Session;
import scripts.Crypt;

/**
 *
 * @author OlesiaPC
 */
public class DBService {

    public static synchronized void saveObject(Object object) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.flush();
        }
    }

    public static synchronized List<Object> loadObjects(Class classObject) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<Object> createQuery = builder.createQuery(classObject);
            TypedQuery<Object> typed = session.createQuery(createQuery);
            return typed.getResultList();
        }
    }

    public static synchronized void deleteObject(Object object) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            ObjectService objectService = new ObjectServiceImplementation();
            try {
                Object persistentInstance = session.load(object.getClass(), objectService.getObjectID(object));
                session.delete(persistentInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized User checkByToken(String token) {
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
                return null;
            }
        }
    }

    public static synchronized Object getObjectByID(Class classObject, String ID) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            Object object = session.get(classObject, ID); //null if object doesn't exist
            return object;
        }
    }

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
                return null;
            }
        }
    }

    public static synchronized Positions chechByLoginAndPassword(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.or(builder.equal(root.get(User_.login), login), builder.equal(root.get(User_.password), Crypt.encryptMD5(password))));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult().getLevelOfAccess();
            } catch (final NoResultException ignore) {
                return null;
            }
        }
    }

    public static synchronized boolean existenceOfLoginAndEMail(String login, String eMail) {
        final DBManager dBManager = DBManager.getInstance();
        try (Session session = dBManager.getSession()) {
            session.beginTransaction();
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(builder.or(builder.equal(root.get(User_.login), login), builder.equal(root.get(User_.eMail), eMail)));
            final TypedQuery<User> typed = session.createQuery(criteriaQuery);
            try {
                return typed.getSingleResult() != null;
            } catch (final NoResultException ignore) {
                return false;
            }
        }
    }
}
