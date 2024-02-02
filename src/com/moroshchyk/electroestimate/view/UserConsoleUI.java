package com.moroshchyk.electroestimate.view;

import com.moroshchyk.electroestimate.model.User;

import java.lang.reflect.Field;

/**
 * Клас UserConsoleUI надає можливість виводу інформації про користувача в консоль.
 */
public class UserConsoleUI {

    /**
     * Метод для виведення інформації про користувача в консоль.
     *
     * @param user Об'єкт користувача, інформацію про якого необхідно вивести.
     * @throws IllegalAccessException Виняток, що виникає при незаконному доступі.
     */
    public static void displayUserInfo(User user) throws IllegalAccessException {
        for (Field field : User.class.getDeclaredFields()) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object value = field.get(user);
            CustomerConsoleUI.printFieldName(fieldName);

            if (value instanceof String || value instanceof Long) {
                CustomerConsoleUI.printField(value.toString());
            } else {
                CustomerConsoleUI.printField(String.valueOf(value));
            }
        }
    }
}
