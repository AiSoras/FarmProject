/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import objects.Hangar;
import objects.Paddock;
import objects.Passport;
import objects.Ration;
import objects.User;
import objects.Vaccination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Provides the connection between the database and the server.
 * @author OlesiaPC
 */
public final class DBManager {

    private static final Logger logger = LogManager.getLogger(DBManager.class.getName());

    private static DBManager dBManager;
    private SessionFactory sessionFactory;
    private Properties properties;
    
//    public static void main(String[] args) {
//        DBManager db = DBManager.getInstance();
//    }
    
    public static synchronized DBManager getInstance() {
        if (dBManager == null) {
            dBManager = new DBManager();
        }
        return dBManager;
    }

    private DBManager() {
        connectionInit();
    }

    /**
     * Builds hibernate session.
     */
    public void connectionInit() {
        loadProperties();
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"))
                .setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"))
                .setProperty("hibernate.connection.pool_size", properties.getProperty("hibernate.connection.pool_size"))
                .setProperty("hibernate.connection.url", properties.getProperty("hibernate.connection.url"))
                .setProperty("hibernate.connection.driver_class", properties.getProperty("hibernate.connection.driver_class"))
                .setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"))
                .addAnnotatedClass(Hangar.class)
                .addAnnotatedClass(Paddock.class)
                .addAnnotatedClass(Ration.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Vaccination.class)
                .addAnnotatedClass(User.class);

        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            logger.error("Exception:", ex);
        }
        logger.info("Session has been built");
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Loads hibernate properties from XML file.
     */
    private void loadProperties() {
        try {
            properties = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("../config/hibernate.configuration.xml");
            properties.loadFromXML(inputStream);
            logger.info("Loading properties is successful");
            inputStream.close();
        } catch (IOException ex) {
            logger.error("Exception:", ex);
        }
    }
}
