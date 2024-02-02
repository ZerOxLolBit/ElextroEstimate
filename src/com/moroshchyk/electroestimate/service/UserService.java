package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас, що надає сервіс для управління користувачами.
 */
public class UserService {

    private final List<User> users;

    /**
     * Конструктор класу UserService.
     * Ініціалізує список користувачів.
     */
    public UserService() {
        this.users = new ArrayList<>();
    }

    /**
     * Метод для створення нового користувача.
     * @param user Новий користувач.
     */
    public void createUser(User user) {
        users.add(user);
    }

    /**
     * Метод для отримання списку всіх користувачів.
     * @return Список всіх користувачів.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Метод для отримання користувача за його ідентифікатором.
     * @param userId Ідентифікатор користувача.
     * @return Користувач з вказаним ідентифікатором або null, якщо користувача не знайдено.
     */
    public User getUserById(long userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод для оновлення інформації про користувача.
     * @param updatedUser Оновлений об'єкт користувача.
     */
    public void updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getId() == updatedUser.getId()) {
                user.setPassword(updatedUser.getPassword());
                user.setEmail(updatedUser.getEmail());
                break;
            }
        }
    }

    /**
     * Метод для видалення користувача за його ідентифікатором.
     * @param userId Ідентифікатор користувача, якого потрібно видалити.
     * @return Повертає true, якщо користувач успішно видалений, і false в іншому випадку.
     */
    public boolean deleteUser(long userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для отримання користувача за його ім'ям користувача.
     * @param username Ім'я користувача.
     * @return Користувач з вказаним ім'ям або null, якщо користувача не знайдено.
     */
    public User getUserByUsername(String username) {
        List<User> usersJson = JsonDataReader.modelDataJsonReader(Path.USER_JSON.getPath(), User[].class);
        for (User user : usersJson) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод для оновлення інформації про користувача через консольне меню.
     */
    public static void updateUserInMenu(){
        Scanner scanner = new Scanner(System.in);

        CustomerConsoleUI.printMenu("Введіть ім'я користувача, якого хочете змінити:");
        String username = scanner.nextLine();

        UserService userService = new UserService();
        User userToUpdate = userService.getUserByUsername(username);

        if (userToUpdate != null) {
            CustomerConsoleUI.printMenu("Введіть новий пароль:");
            String newPassword = scanner.nextLine();

            CustomerConsoleUI.printMenu("Введіть нову електронну пошту:");
            String newEmail = scanner.nextLine();

            userToUpdate.setPassword(newPassword);
            userToUpdate.setEmail(newEmail);

            try {
                List<User> users = JsonDataReader.modelDataJsonReader(Path.USER_JSON.getPath(), User[].class);
                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        user.setPassword(newPassword);
                        user.setEmail(newEmail);
                        break;
                    }
                }

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.writeValue(new File(Path.USER_JSON.getPath()), users);
                CustomerConsoleUI.printMenu("Дані користувача успішно оновлені та збережені.");
            } catch (IOException e) {
                CustomerConsoleUI.printMenu("Помилка при збереженні оновлених даних користувача: " + e.getMessage());
            }
        } else {
            CustomerConsoleUI.printMenu("Користувача з ім'ям " + username + " не знайдено.");
        }
    }
}
