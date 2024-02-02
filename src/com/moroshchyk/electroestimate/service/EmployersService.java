package com.moroshchyk.electroestimate.service;

import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;

import java.util.List;

/**
 * Сервіс для управління працівниками.
 */
public class EmployersService {

  /**
   * Головний метод, в якому відображається інформація про користувачів.
   */
  public static void main(String[] args) {
    List<User> users = JsonDataReader.modelDataJsonReader(Path.USER_JSON.getPath(), User[].class);
    displayUsersInfo(users);
  }

  /**
   * Метод для відображення інформації про користувачів з роллю "Працівник".
   *
   * @param users список користувачів
   */
  public static void displayUsersInfo(List<User> users) {
    boolean hasWorkers = false; // Флаг для визначення, чи є працівники

    // Перевіряємо, чи список користувачів не є порожнім
    if (users != null) {
      for (User user : users) {
        // Перевіряємо роль кожного користувача
        if ("Працівник".equals(user.getRole())) {
          hasWorkers = true; // Знайдено працівника
          CustomerConsoleUI.printMenu("ID: " + user.getId());
          CustomerConsoleUI.printMenu("Username: " + user.getUsername());
          CustomerConsoleUI.printMenu("Email: " + user.getEmail());
          CustomerConsoleUI.printMenu("Role: " + user.getRole());
          CustomerConsoleUI.printSystemMessage(""); // Для розділення між користувачами
        }
      }
    } else {
      CustomerConsoleUI.printMenu("Список користувачів порожній або виникла помилка при читанні з файлу.");
    }

    // Виводимо повідомлення, якщо працівники не знайдені
    if (!hasWorkers) {
      CustomerConsoleUI.printMenu("Працівники не знайдені.");
    }
  }
}
