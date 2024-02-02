package com.moroshchyk.electroestimate.view;

import java.util.Scanner;

/**
 * Клас UserInputHandler надає методи для отримання введених даних від користувача з консолі.
 */
public class UserInputHandler {
    private Scanner scanner;

    /**
     * Конструктор класу UserInputHandler, що ініціалізує об'єкт Scanner для отримання введених даних з консолі.
     */
    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Метод для отримання рядкових даних від користувача.
     *
     * @param prompt Повідомлення, яке відображається користувачеві для введення даних.
     * @return Рядок, введений користувачем.
     */
    public String promptUserForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    /**
     * Метод для отримання цілочисельного значення від користувача.
     *
     * @param prompt Повідомлення, яке відображається користувачеві для введення даних.
     * @return Ціле число, введене користувачем.
     */
    public int promptUserForInteger(String prompt) {
        CustomerConsoleUI.printSystemMessage(prompt + ": ");

        while (!scanner.hasNextInt()) {
            CustomerConsoleUI.printSystemMessage("Будь ласка, введіть ціле число.");
            CustomerConsoleUI.printSystemMessage(prompt + ": ");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    /**
     * Метод для закриття об'єкта Scanner після використання.
     */
    public void closeScanner() {
        scanner.close();
    }
}
