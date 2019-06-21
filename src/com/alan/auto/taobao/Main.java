package com.alan.auto.taobao;

public class Main {

    private static String jarPath;

    public static void main(String[] args) {
        // write your code here
        jarPath = UtilsPath.getJarDirPath(Main.class);
        System.out.println(jarPath);
        System.out.println("start");
        if (args.length != 1) {
            System.out.println("参数不正确！");
            return;
        }

        try {
            if ("-a".equals(args[0])) {
                executeClickGood();
            } else if ("-b".equals(args[0])) {
                executeMoreWatch();
            }

        } catch (InterruptedException e) {
            System.out.println("程序异常！");
        }
    }

    public static void executeClickGood() throws InterruptedException {
        while (true) {
            Config config = new Config(jarPath);
            String cmd = String.format("adb -s %s shell input tap %d %d", config.getDevice(), config.getGoodX(), config.getGoodY());
            System.out.println(cmd);
            Command.exeCmd(cmd);
            Thread.sleep(config.getGoodDelay());
        }
    }

    private static void executeMoreWatch() throws InterruptedException {

        while (true) {
            Config config = new Config(jarPath);
            String cmd = String.format("adb -s %s shell input tap %d %d", config.getDevice(), config.getWatchX(), config.getWatchY());
            System.out.println(cmd);
            Command.exeCmd(cmd);
            Thread.sleep(config.getWatchDelay1());
            cmd = String.format("adb -s %s shell input keyevent 4", config.getDevice());
            Command.exeCmd(cmd);
            Thread.sleep(config.getWatchDelay2());
        }
    }
}
