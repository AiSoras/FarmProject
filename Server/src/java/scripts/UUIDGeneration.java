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

    /**
     * Generates the string that consists of a special <code>symbol</code> and
     * {@link UUID}.
     *
     * @param symbol
     * @return The string consisting of a special <code>symbol</code> and
     * {@link UUID}.
     */
    public static synchronized String create(char symbol) {
        return symbol + "-" + UUID.randomUUID().toString();
    }
}
