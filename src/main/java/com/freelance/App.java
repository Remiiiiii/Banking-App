package com.freelance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freelance.consoleViews.MainMenu;

/**
 * --Full stack path
 * --1. create your db schema and validate that all operations work
 * --2. have your models in java, mimic your db tables
 * --3. create your dao layer
 * --4. create your views
 *
 */
public class App {

    static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();

        mainMenu.view();
    }
}
