package com.moroshchyk.electroestimate.model;

import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.util.Scanner;

/**
 * Клас, який надає методи для перевірки та отримання введення від користувача.
 */
public class ValidationInput {

  /**
   * Перевіряє, чи всі введені значення є числовими.
   *
   * @param inputs масив рядків для перевірки
   * @return true, якщо всі введені значення є числовими, в іншому випадку - false
   */
  public static boolean checkNumericInput(String[] inputs) {
    for (String input : inputs) {
      if (!input.trim().matches("-?\\d+(\\.\\d+)?")) {
        return false;
      }
    }
    return true;
  }

  /**
   * Отримує від користувача правильне числове введення типу double.
   *
   * @param scanner екземпляр Scanner для зчитування введення
   * @return правильне числове значення типу double, введене користувачем
   */
  public static double getValidDoubleInput(Scanner scanner) {
    double input;
    while (true) {
      try {
        input = Double.parseDouble(scanner.nextLine().trim());
        break; // Правильний ввід, виходимо з циклу
      } catch (NumberFormatException e) {
        CustomerConsoleUI.printMenu("Неправильний формат. Будь ласка, введіть числове значення:");
      }
    }
    return input;
  }

  /**
   * Отримує від користувача правильне числове введення типу int.
   *
   * @param scanner екземпляр Scanner для зчитування введення
   * @return правильне ціле числове значення, введене користувачем
   */
  public static int getValidIntInput(Scanner scanner) {
    int input;
    while (true) {
      try {
        input = Integer.parseInt(scanner.nextLine().trim());
        break; // Правильний ввід, виходимо з циклу
      } catch (NumberFormatException e) {
        CustomerConsoleUI.printMenu("Неправильний формат. Будь ласка, введіть ціле числове значення:");
      }
    }
    return input;
  }
}
