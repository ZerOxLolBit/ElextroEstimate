package com.moroshchyk.electroestimate.electroestimate;

import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import com.moroshchyk.electroestimate.view.Menu;

/**
 * Головний клас додатку, який містить точку входу.
 */
public class Application {

    /** Масив користувачів. */
    public static User[] users;

    /** Поточний користувач. */
    public static User currentUser = new User("NazikTazik", "NazikTazik12443", "NazikTazik@gmail.com", "");

    /**
     * Головний метод, який запускає програму.
     *
     * @param args аргументи командного рядка.
     * @throws IllegalAccessException виняток, яке виникає при намаганні доступу до класу, поля або методу, які заборонено доступати.
     */
    public static void main(String[] args) throws IllegalAccessException {
        CustomerConsoleUI.printMenu("Електромонтажні послуги");
        runner();
    }

    /**
     * Метод для запуску додатку.
     *
     * @throws IllegalAccessException виняток, яке виникає при намаганні доступу до класу, поля або методу, які заборонено доступати.
     */
    public static void runner() throws IllegalAccessException {
        Menu.show();
    }
}
