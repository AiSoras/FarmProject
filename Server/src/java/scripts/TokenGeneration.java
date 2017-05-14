/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author OlesiaPC
 */
public class TokenGeneration {

    public static synchronized String create() {
        return RandomStringUtils.random(12, true, true);
    }
}
