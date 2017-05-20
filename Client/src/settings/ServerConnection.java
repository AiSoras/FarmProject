/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import api.AccountService;
import api.ObjectService;
import com.caucho.hessian.client.HessianProxyFactory;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OlesiaPC
 */
public class ServerConnection {

    private static AccountService accountService;
    private static ObjectService objectService;
    private static HessianProxyFactory factory;
    private static String serverAddress = "localhost";

    public static synchronized AccountService getAccountConnecttion() { //
        if (accountService == null) {
            try {
                getHessianProxyFactory();
                String url = "http://" + serverAddress + ":8080/Server/AccountService";
                accountService = (AccountService) factory.create(AccountService.class, url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return accountService;
    }

    public static synchronized ObjectService getObjectConnecttion() {
        if (objectService == null) {
            try {
                getHessianProxyFactory();
                String url = "http://" + serverAddress + ":8080/Server/ObjectService";
                objectService = (ObjectService) factory.create(ObjectService.class, url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private static synchronized void setServerAddress(String address){
        serverAddress = address;
    }
}
