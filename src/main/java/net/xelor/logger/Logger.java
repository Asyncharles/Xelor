package net.xelor.logger;

import java.util.Date;

public final class Logger {

    public static void info(String str) {
        System.out.println("[INFO] " + new Date().toLocaleString() + " : " + str);
    }

    public static void logFine(String str) {
        System.out.println("[FINE] " + new Date().toLocaleString() + " : " + str);
    }

    public static void warn(String str) {
        System.out.println("[WARN] " + new Date().toLocaleString() + " : " + str);
    }

    public static void severe(String str) {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("[SEVERE] " + new Date().toLocaleString() + " : " + str);
        System.out.println("------------------------------------------------------------------------------------");
    }
}
