package com.alan.auto.taobao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private String device;

    private int goodX, goodY, goodDelay;

    private int watchX, watchY, watchDelay1, watchDelay2;

    public Config(String path) {
        Properties properties = getProperties(path + "/config.properties");
        device = properties.getProperty("device_name");
        goodX = Integer.valueOf(properties.getProperty("good_x", "0"));
        goodY = Integer.valueOf(properties.getProperty("good_y", "0"));
        goodDelay = Integer.valueOf(properties.getProperty("good_delay", "1000"));

        watchX = Integer.valueOf(properties.getProperty("watch_x", "0"));
        watchY = Integer.valueOf(properties.getProperty("watch_y", "0"));
        watchDelay1 = Integer.valueOf(properties.getProperty("watch_delay1", "1000"));
        watchDelay2 = Integer.valueOf(properties.getProperty("watch_delay2", "1000"));
    }


    public String getDevice() {
        return device;
    }

    public int getGoodX() {
        return goodX;
    }

    public int getGoodY() {
        return goodY;
    }

    public int getGoodDelay() {
        return goodDelay;
    }

    public int getWatchX() {
        return watchX;
    }

    public int getWatchY() {
        return watchY;
    }

    public int getWatchDelay1() {
        return watchDelay1;
    }

    public int getWatchDelay2() {
        return watchDelay2;
    }

    public static Properties getProperties(String path) {

        FileInputStream in = null;
        try {
            in = new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException var15) {
            System.out.println(var15.getMessage());
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }
        }
        return null;
    }
}
