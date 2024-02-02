package com.moroshchyk.electroestimate.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє компанію.
 */
public class Company {
  /** Назва компанії. */
  private String name;

  /** Інформація про компанію. */
  private String information;

  /** Ліцензія компанії. */
  private String license;

  /** Список працівників компанії. */
  private List<User> employees;

  /**
   * Конструктор класу Company.
   *
   * @param name назва компанії
   * @param information інформація про компанію
   * @param license ліцензія компанії
   */
  public Company(String name, String information, String license) {
    this.name = name;
    this.information = information;
    this.license = license;
    this.employees = new ArrayList<>();
  }

  /**
   * Додає працівника до списку працівників компанії.
   *
   * @param employee працівник для додавання
   */
  public void addEmployee(User employee) {
    employees.add(employee);
  }

  /**
   * Повертає назву компанії.
   *
   * @return назва компанії
   */
  public String getName() {
    return name;
  }

  /**
   * Повертає інформацію про компанію.
   *
   * @return інформація про компанію
   */
  public String getInformation() {
    return information;
  }

  /**
   * Повертає ліцензію компанії.
   *
   * @return ліцензія компанії
   */
  public String getLicense() {
    return license;
  }

  /**
   * Повертає список працівників компанії.
   *
   * @return список працівників компанії
   */
  public List<User> getEmployees() {
    return employees;
  }

  /**
   * Встановлює назву компанії.
   *
   * @param name назва компанії
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Встановлює інформацію про компанію.
   *
   * @param information інформація про компанію
   */
  public void setInformation(String information) {
    this.information = information;
  }

  /**
   * Встановлює ліцензію компанії.
   *
   * @param license ліцензія компанії
   */
  public void setLicense(String license) {
    this.license = license;
  }

  /**
   * Наймає працівників з файлу.
   *
   * @param filePath шлях до файлу з працівниками
   */
  public void hireEmployee(String filePath) {
    // Логіка найму працівників з файлу
  }

  /**
   * Перевизначений метод toString().
   *
   * @return рядок з інформацією про компанію
   */
  @Override
  public String toString() {
    return "Company{" +
        "name='" + name + '\'' +
        ", information='" + information + '\'' +
        ", license='" + license + '\'' +
        ", employees=" + employees +
        '}';
  }
}
