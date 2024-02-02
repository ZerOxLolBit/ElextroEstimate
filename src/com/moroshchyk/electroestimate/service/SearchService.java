package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moroshchyk.electroestimate.model.ElectroServices;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.Project;
import com.moroshchyk.electroestimate.model.ValidationInput;
import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.util.List;
import java.util.Scanner;

/**
 * Клас, що надає сервіс пошуку сервісів та проектів за їхнім ідентифікатором.
 */
public class SearchService {

  /**
   * Метод для пошуку сервісів та проектів за ідентифікатором.
   */
  public static void searchService() {

    Scanner scanner = new Scanner(System.in);
    CustomerConsoleUI.printSystemMessage("Введіть ідентифікатор для пошуку:");
    int searchId = ValidationInput.getValidIntInput(scanner);

    List<ElectroServices> electroServices = JsonDataReader.modelDataJsonReader(Path.ELECTRO_JSON.getPath(), ElectroServices[].class);
    List<Project> projects = JsonDataReader.modelDataJsonReader(Path.PROJECT_JSON.getPath(), Project[].class);

    // Пошук сервісів
    for (ElectroServices service : electroServices) {
      if (service.getIdElectroServices() == searchId) {

        CustomerConsoleUI.printMenu("ID сервісу: " + service.getIdElectroServices());
        CustomerConsoleUI.printMenu("Назва сервісу: " + service.getServiceName());
        CustomerConsoleUI.printMenu("Час виконання: " + service.getExecutionTime());
        CustomerConsoleUI.printMenu("Ціна: " + service.getExecutionPrice());
        CustomerConsoleUI.printSystemMessage("");
        break; // Зупиняємо цикл після знаходження співпадіння
      }
    }

    // Пошук проектів
    for (Project project : projects) {
      if (project.getIdProject() == searchId) {
        CustomerConsoleUI.printMenu("ID проекту: " + project.getIdProject());
        CustomerConsoleUI.printMenu("ID електросервісу(ів): " + project.getIdElectroServices());
        CustomerConsoleUI.printMenu("Робоча область: " + project.getWorkArea());
        CustomerConsoleUI.printMenu("Час: " + project.getTime());
        CustomerConsoleUI.printMenu("Ціна: " + project.getPrice());
        CustomerConsoleUI.printSystemMessage("");
        break; // Зупиняємо цикл після знаходження співпадіння
      }
    }
  }
}
