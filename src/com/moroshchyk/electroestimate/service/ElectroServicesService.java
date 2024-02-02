package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moroshchyk.electroestimate.model.ElectroServices;
import com.moroshchyk.electroestimate.model.Path;
import com.moroshchyk.electroestimate.model.ValidationInput;

import com.moroshchyk.electroestimate.view.CustomerConsoleUI;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Сервіс для управління електросервісами.
 */
public class ElectroServicesService {

  /**
   * Метод для додавання нового електросервісу.
   */
  public static void addService() {
    Scanner scanner = new Scanner(System.in);
    List<ElectroServices> allElectroServices = JsonDataReader.modelDataJsonReader(Path.ELECTRO_JSON.getPath(), ElectroServices[].class);
    int idElectroServices = allElectroServices.size() + 1;

    CustomerConsoleUI.printMenu("Введіть назву послуги:");
    String serviceName = scanner.nextLine();

    CustomerConsoleUI.printMenu("Введіть час виконання послуги:");
    int executionTime = ValidationInput.getValidIntInput(scanner);

    CustomerConsoleUI.printMenu("Введіть вартість виконання послуги:");
    double executionPrice = ValidationInput.getValidDoubleInput(scanner);

    ElectroServices newService = new ElectroServices(idElectroServices, serviceName, executionTime, executionPrice);
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      allElectroServices.add(newService);
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(Path.ELECTRO_JSON.getPath()), allElectroServices);
      CustomerConsoleUI.printMenu("Нова електропослуга додана успішно!");
    } catch (IOException e) {
      e.printStackTrace();
      CustomerConsoleUI.printMenu("Помилка під час додавання електропослуги.");
    }
  }

  /**
   * Метод для редагування існуючого електросервісу.
   */
  public static void editService() {
    Scanner scanner = new Scanner(System.in);
    List<ElectroServices> electroServices = JsonDataReader.modelDataJsonReader(Path.ELECTRO_JSON.getPath(), ElectroServices[].class);

    CustomerConsoleUI.printMenu("Список доступних електросервісів:");
    for (ElectroServices electroService : electroServices) {
      CustomerConsoleUI.printMenu(electroService.getIdElectroServices() + ". " + electroService.getServiceName());
    }

    CustomerConsoleUI.printMenu("Введіть id послуги:");
    int idService = ValidationInput.getValidIntInput(scanner);

    ElectroServices selectedElectroServices = null;
    for (ElectroServices electroService : electroServices) {
      if (electroService.getIdElectroServices() == idService) {
        selectedElectroServices = electroService;
        break;
      }
    }

    if (selectedElectroServices != null) {
      CustomerConsoleUI.printMenu("Бажаєте редагувати цей електросервіс? (Так/Ні):");
      String response = scanner.nextLine();

      if (response.equalsIgnoreCase("так")) {
        CustomerConsoleUI.printMenu("Введіть нову назву електросервісу:");
        String newName = scanner.nextLine();
        selectedElectroServices.setServiceName(newName);

        CustomerConsoleUI.printMenu("Введіть новий час виконання послуги:");
        int newExecutionTime = ValidationInput.getValidIntInput(scanner);
        selectedElectroServices.setExecutionTime(newExecutionTime);

        CustomerConsoleUI.printMenu("Введіть нову вартість виконання послуги:");
        double newExecutionPrice = ValidationInput.getValidDoubleInput(scanner);
        selectedElectroServices.setExecutionPrice(newExecutionPrice);

        try {
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
          objectMapper.writeValue(new File(Path.ELECTRO_JSON.getPath()), electroServices);
          CustomerConsoleUI.printMenu("Оновлені дані електросервісу збережено успішно.");
        } catch (IOException e) {
          CustomerConsoleUI.printMenu("Помилка при збереженні оновлених даних електросервісу: " + e.getMessage());
        }
      } else {
        CustomerConsoleUI.printMenu("Редагування скасовано.");
      }
    } else {
      CustomerConsoleUI.printMenu("Електросервіс з введеним ID не знайдено.");
    }
  }
}
