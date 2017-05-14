/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.util.UUID;

/**
 *
 * @author OlesiaPC
 */
public class UUIDGeneration {

    public static synchronized String create(char symbol) {
        return symbol + "-" + UUID.randomUUID().toString();
    }
}
