package com.moroshchyk.electroestimate.model;

import java.util.UUID;

/**
 * Клас, що представляє користувача системи.
 */
public class User {
    /** Унікальний ідентифікатор користувача. */
    private String id;

    /** Ім'я користувача. */
    private String username;

    /** Пароль користувача. */
    private String password;

    /** Email користувача. */
    private String email;

    /** Роль користувача. */
    private String role;

    /**
     * Конструктор класу User з параметрами.
     *
     * @param username ім'я користувача
     * @param password пароль користувача
     * @param email    email користувача
     * @param role     роль користувача
     */
    public User(String username, String password, String email, String role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Порожній конструктор класу User.
     */
    public User() {}

    /**
     * Отримує унікальний ідентифікатор користувача.
     *
     * @return унікальний ідентифікатор користувача
     */
    public String getId() {
        return id;
    }

    /**
     * Встановлює унікальний ідентифікатор користувача.
     *
     * @param id унікальний ідентифікатор користувача
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Отримує ім'я користувача.
     *
     * @return ім'я користувача
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює ім'я користувача.
     *
     * @param username ім'я користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Отримує пароль користувача.
     *
     * @return пароль користувача
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     *
     * @param password пароль користувача
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Отримує email користувача.
     *
     * @return email користувача
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює email користувача.
     *
     * @param email email користувача
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Отримує роль користувача.
     *
     * @return роль користувача
     */
    public String getRole() {
        return role;
    }

    /**
     * Встановлює роль користувача.
     *
     * @param role роль користувача
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Повертає рядкове представлення об'єкта класу User.
     *
     * @return рядкове представлення об'єкта
     */
    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            '}';
    }

    /**
     * Виводить інформацію про користувача.
     */
    public void displayUserInfo() {
        System.out.println("User Information:");
        System.out.println("ID: " + id);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
