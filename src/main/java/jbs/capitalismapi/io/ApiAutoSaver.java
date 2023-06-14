package jbs.capitalismapi.io;

import jbs.capitalismapi.CapitalismApi;

public class ApiAutoSaver implements Runnable {
    public ApiAutoSaver(CapitalismApi plugin) {
        this.plugin = plugin;
    }
    CapitalismApi plugin;

    public void run() {
        plugin.saveData();
    }
}
