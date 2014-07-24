package com.lge.githubasync;

/**
 * Log utility class.
 * 
 * DO NOT MODIFY THIS!
 */
public class Logger {
    public static final int NORMAL = 0;
    public static final int VERBOSE = 1;
    private static final String INFO = "INFO";
    private static final String PRINT = "PRNT";
    public long startTimestamp = System.currentTimeMillis();
    private int mode;

    public Logger(int mode) {
        this.mode = mode;
    }

    private String thread() {
        return Thread.currentThread().getName();
    }

    private String timestamp() {
        long current = System.currentTimeMillis() - startTimestamp;
        return String.format("[%4d.%03d] ", current / 1000, current % 1000);
    }

    private String tag(String tag) {
        if (mode >= VERBOSE) {
            return tag + "-" + thread() + "-" + timestamp();
        }
        return "";
    }

    /**
     * Prints log message with thread name and elapsed time since program starts.
     * 
     * @param str
     */
    public void info(String str) {
        if (mode >= VERBOSE)
            System.out.println(tag(INFO) + str);
    }
    
    /**
     * Sets logging mode 
     * 
     * @param mode VERBOSE or NORMAL
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Prints log message. This method should be called on main thread.
     * 
     * @param str
     */
    public void print(String str) {
        checkMainThread();
        System.out.println(tag(PRINT) + str);
    }

    private void checkMainThread() {
        if (!Thread.currentThread().getName().equals("main")) {
            throw new RuntimeException("print() should be called on main thread.");
        }
    }

}