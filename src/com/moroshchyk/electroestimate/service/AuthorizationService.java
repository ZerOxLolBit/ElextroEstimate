package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moroshchyk.electroestimate.electroestimate.Application;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Сервіс для авторизації користувачів.
 */
public class AuthorizationService {

  /**
   * Метод для авторизації користувача за логіном та паролем.
   */
  public static void authorization() {
    String username = CustomerConsoleUI.promptUserForInput("Введіть логін", new Scanner(System.in));
    String password = CustomerConsoleUI.promptUserForInput("Введіть пароль", new Scanner(System.in));

    CustomerConsoleUI.printMenu("Ви ввели логін: " + username + " і пароль: " + password);

    List<User> users = readUsersFromJson(Path.USER_JSON.getPath());

    User user = findUserByUsername(users, username);

    if (user != null && user.getPassword().equals(password)) {
      Application.currentUser = user;
      CustomerConsoleUI.printSystemMessage("Авторизація пройшла успішно.");

    } else {
      CustomerConsoleUI.printSystemMessage("Помилка авторизації. Перевірте логін та пароль.");
    }
  }

  /**
   * Метод для знаходження користувача за логіном.
   *
   * @param users    список користувачів
   * @param username логін користувача
   * @return знайдений користувач або null, якщо користувач не знайдений
   */
  private static User findUserByUsername(List<User> users, String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Метод для зчитування користувачів з JSON файлу.
   *
   * @param filePath шлях до JSON файлу з користувачами
   * @return список користувачів, зчитаний з JSON файлу
   */
  private static List<User> readUsersFromJson(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return Arrays.asList(objectMapper.readValue(new File(filePath), User[].class));
    } catch (IOException e) {
      e.printStackTrace();
      // Обробка помилок при читанні з файлу
      return new ArrayList<>();
    }
  }
}
