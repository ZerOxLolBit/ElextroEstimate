package com.moroshchyk.electroestimate.model;

import java.util.List;

/**
 * Клас, що представляє проєкт.
 */
public class Project {
  /** Унікальний ідентифікатор проєкту. */
  private int idProject;

  /** Список ідентифікаторів електросервісів, які включені до проєкту. */
  private List<Integer> idElectroServices;

  /** Площа робочої області проєкту. */
  private double workArea;

  /** Час виконання проєкту (у годинах). */
  private int time;

  /** Ціна проєкту. */
  private double price;

  /**
   * Конструктор класу Project.
   *
   * @param idProject        унікальний ідентифікатор проєкту
   * @param idElectroServices список ідентифікаторів електросервісів
   * @param workArea         площа робочої області
   * @param time             час виконання проєкту (у годинах)
   * @param price            ціна проєкту
   */
  public Project(int idProject, List<Integer> idElectroServices, double workArea, int time, double price) {
    this.idProject = idProject;
    this.idElectroServices = idElectroServices;
    this.workArea = workArea;
    this.time = time;
    this.price = price;
  }

  /**
   * Порожній конструктор класу Project.
   */
  public Project() {}

  /**
   * Отримує список ідентифікаторів електросервісів, які включені до проєкту.
   *
   * @return список ідентифікаторів електросервісів
   */
  public List<Integer> getIdElectroServices() {
    return idElectroServices;
  }

  /**
   * Встановлює список ідентифікаторів електросервісів, які включені до проєкту.
   *
   * @param idElectroServices список ідентифікаторів електросервісів
   */
  public void setIdElectroServices(List<Integer> idElectroServices) {
    this.idElectroServices = idElectroServices;
  }

  /**
   * Отримує унікальний ідентифікатор проєкту.
   *
   * @return унікальний ідентифікатор проєкту
   */
  public int getIdProject() {
    return idProject;
  }

  /**
   * Встановлює унікальний ідентифікатор проєкту.
   *
   * @param idProject унікальний ідентифікатор проєкту
   */
  public void setIdProject(int idProject) {
    this.idProject = idProject;
  }

  /**
   * Отримує площу робочої області проєкту.
   *
   * @return площа робочої області проєкту
   */
  public double getWorkArea() {
    return workArea;
  }

  /**
   * Отримує час виконання проєкту (у годинах).
   *
   * @return час виконання проєкту (у годинах)
   */
  public int getTime() {
    return time;
  }

  /**
   * Отримує ціну проєкту.
   *
   * @return ціна проєкту
   */
  public double getPrice() {
    return price;
  }

  /**
   * Встановлює площу робочої області проєкту.
   *
   * @param workArea площа робочої області проєкту
   */
  public void setWorkArea(double workArea) {
    this.workArea = workArea;
  }

  /**
   * Встановлює час виконання проєкту (у годинах).
   *
   * @param time час виконання проєкту (у годинах)
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Встановлює ціну проєкту.
   *
   * @param price ціна проєкту
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Повертає рядкове представлення об'єкта класу Project.
   *
   * @return рядкове представлення об'єкта
   */
  @Override
  public String toString() {
    return "Project{" +
        "idProject=" + idProject +
        ", idElectroServices=" + idElectroServices +
        ", workArea=" + workArea +
        ", time=" + time +
        ", price=" + price +
        '}';
  }
}
