/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import objects.Hangar;
import objects.Paddock;
import objects.Passport;
import objects.Ration;
import objects.User;
import objects.Vaccination;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author OlesiaPC
 */
public class DBManager {

    private static DBManager dBManager;
    private SessionFactory sessionFactory;

    public static synchronized DBManager getInstance() {
        if (dBManager == null) {
            dBManager = new DBManager();
        }
        return dBManager;
    }

    private DBManager() {
        connectionInit();
    }

    public void connectionInit() {
        Configuration configuration = new Configuration()
//                .addResource("persistence.xml") doesn't work
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/farmproject")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.connection.pool_size", "5")
                .addAnnotatedClass(Hangar.class)
                .addAnnotatedClass(Paddock.class)
                .addAnnotatedClass(Ration.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Vaccination.class)
                .addAnnotatedClass(User.class);
        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
