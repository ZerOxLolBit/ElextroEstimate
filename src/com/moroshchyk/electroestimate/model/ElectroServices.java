package com.moroshchyk.electroestimate.model;

/**
 * Клас, що представляє електропослугу.
 */
public class ElectroServices {
  /** Ідентифікатор електропослуги. */
  private int idElectroServices;

  /** Назва електропослуги. */
  private String serviceName;

  /** Час виконання електропослуги. */
  private int executionTime;

  /** Вартість виконання електропослуги. */
  private double executionPrice;

  /**
   * Конструктор класу ElectroServices.
   *
   * @param idElectroServices ідентифікатор електропослуги
   * @param serviceName назва електропослуги
   * @param executionTime час виконання електропослуги
   * @param executionPrice вартість виконання електропослуги
   */
  public ElectroServices(int idElectroServices, String serviceName, int executionTime, double executionPrice) {
    this.idElectroServices = idElectroServices;
    this.serviceName = serviceName;
    this.executionTime = executionTime;
    this.executionPrice = executionPrice;
  }

  /** Порожній конструктор класу ElectroServices. */
  public ElectroServices() {
  }

  /**
   * Отримує ідентифікатор електропослуги.
   *
   * @return ідентифікатор електропослуги
   */
  public int getIdElectroServices() {
    return idElectroServices;
  }

  /**
   * Встановлює ідентифікатор електропослуги.
   *
   * @param idElectroServices ідентифікатор електропослуги
   */
  public void setIdElectroServices(int idElectroServices) {
    this.idElectroServices = idElectroServices;
  }

  /**
   * Отримує назву електропослуги.
   *
   * @return назва електропослуги
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * Встановлює назву електропослуги.
   *
   * @param serviceName назва електропослуги
   */
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  /**
   * Отримує час виконання електропослуги.
   *
   * @return час виконання електропослуги
   */
  public int getExecutionTime() {
    return executionTime;
  }

  /**
   * Встановлює час виконання електропослуги.
   *
   * @param executionTime час виконання електропослуги
   */
  public void setExecutionTime(int executionTime) {
    this.executionTime = executionTime;
  }

  /**
   * Отримує вартість виконання електропослуги.
   *
   * @return вартість виконання електропослуги
   */
  public double getExecutionPrice() {
    return executionPrice;
  }

  /**
   * Встановлює вартість виконання електропослуги.
   *
   * @param price вартість виконання електропослуги
   */
  public void setExecutionPrice(double price) {
    this.executionPrice = executionPrice;
  }

  /**
   * Перевизначений метод toString().
   *
   * @return рядок з інформацією про електропослугу
   */
  @Override
  public String toString() {
    return "Service{" +
        "ID=" + idElectroServices + '\'' +
        ", serviceName=" + serviceName +
        ", executionTime=" + executionTime +
        ", price=" + executionPrice +
        '}';
  }
}
