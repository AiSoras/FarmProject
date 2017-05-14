
import api.AccountService;
import api.ObjectService;
import database.DBService;
import implementations.AccountServiceImplementation;
import implementations.ObjectServiceImplementation;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Hangar;
import objects.Paddock;
import objects.Positions;
import objects.Ration;
import objects.SpeciesOfAnimal;
import objects.TypeOfFood;
import objects.TypeOfHangar;
import objects.User;
import scripts.Crypt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OlesiaPC
 */
public class TEST { //Чисто для проверки работы методов

    public static void main(String[] args){
        TEST test = new TEST();
//        try {
//            test.test3();
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
//            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
//        }


//          AccountService accountService = new AccountServiceImplementation();
//          System.out.println(accountService.resetPassword("5otaisora@gmail.com"));

//        System.out.println(DBService.checkByToken("avsdvsv"));  return null, because this token doesn't exist
          
//        test.test2();
    }

    private void polucheniePoTokeny() {
        User user = new User("Ivan", "Ivanovich", "Ivanov", Positions.CLEANER);
        AccountService accountService = new AccountServiceImplementation();
        accountService.createUser(user);
        User result = DBService.checkByToken(user.getToken());
        System.out.println(result.getSecondName());
    }
    
    private void test2(){
        Hangar hangar = new Hangar(Positions.CLEANER, TypeOfHangar.STANDARD);
        ObjectService objectService = new ObjectServiceImplementation();
        objectService.addObject(hangar, 'H');
        Ration ration = new Ration(TypeOfFood.WET, 0, 0);
        Time time = new Time(12, 45, 36);
        Paddock paddock = new Paddock(ration, SpeciesOfAnimal.CHICKEN, time, hangar);
        objectService.addObject(paddock, 'P');
        System.out.println(objectService.getObjectID(hangar));
        System.out.println(objectService.getObjectID(paddock));
    }
    
    private  void test3() throws NoSuchAlgorithmException, UnsupportedEncodingException{        
        AccountService accountService = new AccountServiceImplementation();
        User user = accountService.getByToken("zGYwzhrzwwL0");
        user.setLogin("NotNull");
        user.setPassword(Crypt.encryptMD5("password"));
        user.seteMail("5otaisora@gmail.com");
        accountService.signUp(user);
    }
}
