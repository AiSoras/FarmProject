/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import api.AccountService;
import api.ObjectService;
import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains methods for establishing a connection to the server
 * 
 * @author OlesiaPC
 */
public class ServerConnection {

    private static AccountService accountService;
    private static ObjectService objectService;
    private static HessianProxyFactory factory;
    private static String serverAddress = "localhost";
    private static final Logger logger = LogManager.getLogger(ServerConnection.class.getName());

    public static synchronized AccountService getAccountConnection() { //
        if (accountService == null) {
            try {
                getHessianProxyFactory();
                String url = "http://" + serverAddress + ":8080/Server/AccountService";
                accountService = (AccountService) factory.create(AccountService.class, url);
                logger.info("Connection is established. URL: " + url);
            } catch (MalformedURLException ex) {
                logger.error("Exception: ", ex);
            }
        }
        return accountService;
    }

    public static synchronized ObjectService getObjectConnection() {
        if (objectService == null) {
            try {
                getHessianProxyFactory();
                String url = "http://" + serverAddress + ":8080/Server/ObjectService";
                objectService = (ObjectService) factory.create(ObjectService.class, url);
                logger.info("Connection is established. URL: " + url);
            } catch (MalformedURLException ex) {
                logger.error("Exception: ", ex);
            }
        }
        return objectService;
    }

    private static synchronized HessianProxyFactory getHessianProxyFactory() {
        if (factory == null) {
            factory = new HessianProxyFactory();
        }
        return factory;
    }

    public static synchronized void setServerAddress(String address) {
        serverAddress = address;
    }

    public static synchronized String getServerAddress() {
        return serverAddress;
    }
}
