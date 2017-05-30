/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author OlesiaPC
 */
public class WindowsSizes {

    private static Properties properties;

    private static void loadProperties() {
        try {
            properties = new Properties();
            File file = new File("WinSize.properties");
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            //Логгер
        }
    }

    public static Dimension getDimension(String className) {
        if (properties == null) {
            loadProperties();
        }
        String[] split = properties.get(className).toString().split(", ");
        int width = Integer.parseInt(split[0]);
        int height = Integer.parseInt(split[1]);
        return new Dimension(width, height);
    }

    public static void saveSize(String className, Dimension dimension) {
        String size = Integer.toString(dimension.width) + ", " + Integer.toString(dimension.height);
        if (!properties.getProperty(className).equals(size)) {
            try {
                properties.setProperty(className, size);
                File file = new File("WinSize.properties");
                OutputStream outputStream = new FileOutputStream(file);
                properties.store(outputStream, "Sizes of windows");
                outputStream.close();
            } catch (IOException ex) {
                //Логгер
            }
        }
    }
}
