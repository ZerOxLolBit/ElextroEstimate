/**
 * Клас, що відповідає за управління користувачами.
 */
package com.moroshchyk.electroestimate.controller;

import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.service.UserService;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.util.List;

public class UserController {
    private final UserService userService;

    /**
     * Конструктор класу UserController.
     *
     * @param userService сервіс, що надає функціональність для користувачів.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для створення нового користувача.
     *
     * @param username логін нового користувача.
     * @param password пароль нового користувача.
     * @param email електронна пошта нового користувача.
     * @param role роль нового користувача.
     */
    public void createUser(String username, String password, String email, String role) {
        User newUser = new User(username, password, email, role);
        userService.createUser(newUser);
        CustomerConsoleUI.printMenu("Користувач " + username + " успішно створений.");
    }

    /**
     * Метод для отримання списку всіх користувачів.
     *
     * @return список усіх користувачів.
     */
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Метод для отримання користувача за ідентифікатором.
     *
     * @param userId ідентифікатор користувача.
     * @return користувач за вказаним ідентифікатором.
     */
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    //
    /**
     * Метод для оновлення інформації про користувача.
     *
     * @param userId ідентифікатор користувача, якого потрібно оновити.
     * @param newPassword новий пароль користувача.
     * @param newEmail нова електронна пошта користувача.
     */
    public void updateUser(long userId, String newPassword, String newEmail) {
        User userToUpdate = userService.getUserById(userId);
        if (userToUpdate != null) {
            userToUpdate.setPassword(newPassword);
            userToUpdate.setEmail(newEmail);
            userService.updateUser(userToUpdate);
            CustomerConsoleUI.printMenu("Інформацію про користувача " + userId + " успішно оновлено.");
        } else {
            CustomerConsoleUI.printMenu("Користувача з ідентифікатором " + userId + " не знайдено.");
        }
    }

    /**
     * Метод для видалення користувача за ідентифікатором.
     *
     * @param userId ідентифікатор користувача, якого потрібно видалити.
     */
    public void deleteUser(long userId) {
        if (userService.deleteUser(userId)) {
            CustomerConsoleUI.printMenu("Користувача " + userId + " успішно видалено.");
        } else {
            CustomerConsoleUI.printMenu("Користувача з ідентифікатором " + userId + " не знайдено.");
        }
    }
}
