package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moroshchyk.electroestimate.electroestimate.Application;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import com.moroshchyk.electroestimate.view.UserInputHandler;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Клас, що надає сервіс для реєстрації нових користувачів у системі.
 */
public class RegistrationService {

  /**
   * Метод для реєстрації нового користувача.
   * Користувач вводить дані про себе, такі як логін, пароль, email та обирає роль у системі.
   * Після цього новий користувач додається до списку користувачів та зберігається в JSON-файлі.
   */
  public static void registration() {
    String username;
    String password;
    String email;

    // Перевірка унікальності логіну
    do {
      username = CustomerConsoleUI.promptUserForInput("Введіть логін",
          new java.util.Scanner(System.in));
      if (Application.users != null && !isLoginUnique(username)) {
        CustomerConsoleUI.printSystemMessage(
            "Цей логін вже використовується. Виберіть інший.");
      }
    } while (Application.users != null && !isLoginUnique(username));

    password = CustomerConsoleUI.promptUserForInput("Введіть пароль",
        new java.util.Scanner(System.in));
    email = CustomerConsoleUI.promptUserForInput("Введіть email",
        new java.util.Scanner(System.in));

    // Додайте вивід меню для вибору ролі
    CustomerConsoleUI.printMenu("Оберіть роль:");
    CustomerConsoleUI.printMenu("1) Користувач");
    CustomerConsoleUI.printMenu("2) Робітник");
    CustomerConsoleUI.printMenu("3) Компанія");
    CustomerConsoleUI.printMenu("4) Адміністратор");

    int roleChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    String role;
    switch (roleChoice) {
      case 1:
        role = "Користувач";
        break;
      case 2:
        role = "Робітник";
        break;
      case 3:
        role = "Компанія";
        break;
      case 4:
        role = "Адмін";
        break;
      default:
        role = "Користувач";
        break;
    }

    User newUser = new User(username, password, email, role);
    Application.currentUser = newUser;

    if (Application.users == null) {
      Application.users = new User[]{};
    }
    Application.users = addNewUser(Application.users, newUser);

    saveUsersToJson(Application.users,
        Path.USER_JSON.getPath());

    CustomerConsoleUI.printSystemMessage("Реєстрація пройшла успішно.");
  }

  private static User[] addNewUser(User[] users, User newUser) {
    User[] newUsers = Arrays.copyOf(users, users.length + 1);
    newUsers[users.length] = newUser;
    return newUsers;
  }

  private static boolean isLoginUnique(String login) {
    for (User existingUser : Application.users) {
      if (existingUser.getUsername().equals(login)) {
        return false; // Логін не унікальний
      }
    }
    return true; // Логін унікальний
  }

  private static void saveUsersToJson(User[] users, String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      File file = new File(filePath);
      User[] existingUsers;

      if (file.exists()) {
        // If the file exists, try to deserialize it into an array of users
        existingUsers = objectMapper.readValue(file, User[].class);
      } else {
        // If the file doesn't exist, create an empty array
        existingUsers = new User[]{};
      }

      // Concatenate the existing users with the new user
      User[] updatedUsers = Arrays.copyOf(existingUsers, existingUsers.length + 1);
      updatedUsers[existingUsers.length] = users[0];

      // Write the updated array back to the file
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, updatedUsers);

    } catch (IOException e) {
      e.printStackTrace();
      // Handle errors during file operations
    }
  }
}
