package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.Project;
import com.moroshchyk.electroestimate.model.ValidationInput;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас, що надає сервіс для керування проектами в системі оцінки електромонтажних робіт.
 */
public class ProjectService {

  /**
   * Метод main, який є входом в клас ProjectService.
   * Він відображає інформацію про наявні проекти, зчитуючи дані з JSON-файлу.
   *
   * @param args аргументи командного рядка
   */
  public static void main(String[] args) {
    List<Project> projects = JsonDataReader.modelDataJsonReader(Path.PROJECT_JSON.getPath(), Project[].class);
    displayProjectsInfo(projects);
  }

  /**
   * Метод для відображення інформації про проекти.
   *
   * @param projects список проектів
   */
  public static void displayProjectsInfo(List<Project> projects) {
    for (Project project : projects) {
      CustomerConsoleUI.printMenu("ID проекту: " + project.getIdProject());
      CustomerConsoleUI.printMenu("ID електросервісу(ів): " + project.getIdElectroServices());
      CustomerConsoleUI.printMenu("Робоча область: " + project.getWorkArea());
      CustomerConsoleUI.printMenu("Час: " + project.getTime());
      CustomerConsoleUI.printMenu("Ціна: " + project.getPrice());
      CustomerConsoleUI.printSystemMessage(""); // Для розділення між записами
    }
  }

  /**
   * Метод для додавання нового проекту.
   * Користувач вводить дані про новий проект, які потім додаються до списку проектів.
   */
  public static void addProject() {
    Scanner scanner = new Scanner(System.in);
    List<Project> allProjects = JsonDataReader.modelDataJsonReader(Path.PROJECT_JSON.getPath(), Project[].class);
    int idProject = allProjects.size() + 1;

    List<Integer> idElectroServices = new ArrayList<>();

    CustomerConsoleUI.printMenu("Введіть ідентифікатори електросервісів (через кому):");
    String[] servicesIds;
    boolean validInput = false;
    do {
      String input = scanner.nextLine();
      servicesIds = input.split(",");
      validInput = ValidationInput.checkNumericInput(servicesIds);
      if (!validInput) {
        CustomerConsoleUI.printMenu("Неправильний формат. Будь ласка, введіть числа через кому.");
      }
    } while (!validInput);

    for (String id : servicesIds) {
      idElectroServices.add(Integer.parseInt(id.trim()));
    }

    CustomerConsoleUI.printMenu("Введіть робочу область:");
    double workArea = ValidationInput.getValidDoubleInput(scanner);

    CustomerConsoleUI.printMenu("Введіть час виконання:");
    int time = ValidationInput.getValidIntInput(scanner);

    CustomerConsoleUI.printMenu("Введіть ціну:");
    double price = ValidationInput.getValidDoubleInput(scanner);

    Project newProject = new Project(idProject, idElectroServices, workArea, time, price);

    try {
      // Читаємо існуючий JSON файл
      ObjectMapper objectMapper = new ObjectMapper();

      // Додаємо новий проєкт до списку
      allProjects.add(newProject);

      // Записуємо оновлений список у JSON файл
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(Path.PROJECT_JSON.getPath()), allProjects);

      CustomerConsoleUI.printMenu("Новий проєкт успішно доданий!");
    } catch (IOException e) {
      e.printStackTrace();
      CustomerConsoleUI.printMenu("Помилка під час додавання проєкту.");
    }
  }

  /**
   * Метод для редагування існуючого проекту.
   * Користувач може вибрати проект за ID та внести зміни до його параметрів.
   */
  public static void editProject() {
    Scanner scanner = new Scanner(System.in);
    List<Project> projects = JsonDataReader.modelDataJsonReader(Path.PROJECT_JSON.getPath(), Project[].class);

    // Вивести користувачу усі проекти
    CustomerConsoleUI.printMenu("Список доступних проєктів:");
    for (Project project : projects) {
      CustomerConsoleUI.printMenu("ID проєкту: " + project.getIdProject() + ", "
          + "Час виконання: " + project.getTime() + " год., "
          + "ID електросервісів: " + project.getIdElectroServices() + ", "
          + "Площа робочої області: " + project.getWorkArea() + " кв.м");
    }

    CustomerConsoleUI.printMenu("Введіть id проєкту:");
    int idProject = ValidationInput.getValidIntInput(scanner);

    Project selectedProject = null;
    for (Project project : projects) {
      if (project.getIdProject() == idProject) {
        selectedProject = project;
        break;
      }
    }
    if (selectedProject != null) {
      // Перевірити, чи користувач хоче редагувати проєкт
      CustomerConsoleUI.printMenu("Бажаєте редагувати цей проєкт? (Так/Ні):");
      String response = scanner.nextLine();

      if (response.equalsIgnoreCase("так")) {
        CustomerConsoleUI.printMenu("Введіть id електросервісів (через кому):");
        String[] electroServicesIdsStr = scanner.nextLine().split(",");
        Integer[] idElectroServices = new Integer[electroServicesIdsStr.length];
        for (int i = 0; i < electroServicesIdsStr.length; i++) {
          idElectroServices[i] = Integer.parseInt(electroServicesIdsStr[i].trim());
        }
        selectedProject.setIdElectroServices(List.of(idElectroServices));

        // Просимо користувача ввести робочу область
        CustomerConsoleUI.printMenu("Введіть робочу область:");
        double newWorkArea = ValidationInput.getValidDoubleInput(scanner);
        selectedProject.setWorkArea(newWorkArea);

        // Просимо користувача ввести час виконання
        CustomerConsoleUI.printMenu("Введіть час виконання:");
        int newTime = ValidationInput.getValidIntInput(scanner);
        selectedProject.setTime(newTime);

        // Просимо користувача ввести ціну
        CustomerConsoleUI.printMenu("Введіть ціну:");
        double newPrice = ValidationInput.getValidDoubleInput(scanner);
        selectedProject.setPrice(newPrice);

        try {
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
          objectMapper.writeValue(new File(Path.PROJECT_JSON.getPath()), projects);
          CustomerConsoleUI.printMenu("Оновлені дані проєкту збережено успішно.");
        } catch (IOException e) {
          CustomerConsoleUI.printMenu("Помилка при збереженні оновлених даних проєкту: " + e.getMessage());
        }
      } else {
        CustomerConsoleUI.printMenu("Редагування скасовано.");
      }
    } else {
      CustomerConsoleUI.printMenu("Проєкт з введеним ID не знайдено.");
    }
  }
}
