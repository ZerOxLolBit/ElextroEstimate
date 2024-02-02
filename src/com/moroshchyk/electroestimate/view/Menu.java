package com.moroshchyk.electroestimate.view;

import com.moroshchyk.electroestimate.controller.UserController;
import com.moroshchyk.electroestimate.electroestimate.Application;
import com.moroshchyk.electroestimate.model.User;
import com.moroshchyk.electroestimate.service.AuthorizationService;
import com.moroshchyk.electroestimate.service.ElectroServicesService;
import com.moroshchyk.electroestimate.service.EmployersService;
import com.moroshchyk.electroestimate.service.JsonDataReader;
import com.moroshchyk.electroestimate.service.ProjectService;
import com.moroshchyk.electroestimate.service.RegistrationService;
import com.moroshchyk.electroestimate.service.SearchService;
import com.moroshchyk.electroestimate.service.UserService;

import java.util.Scanner;

/**
 * Клас Menu надає можливість користувачам вибирати опції з меню та взаємодіяти з системою.
 */
public class Menu {

    /**
     * Відображає головне меню програми та обробляє вибір користувача.
     *
     * @throws IllegalAccessException Виняток, що виникає при незаконному доступі.
     */
    public static void show() throws IllegalAccessException {
        CustomerConsoleUI.printLine('*', 20);
        CustomerConsoleUI.printTitle("MENU");
        CustomerConsoleUI.printLine('*', 20);

        while (true) {
            String userRole = Application.currentUser.getRole();

            if ("".equals(userRole)) {
                CustomerConsoleUI.printMenu("1) Реєстрація");
                CustomerConsoleUI.printMenu("2) Авторизація");
            } else {
                CustomerConsoleUI.printMenu("1) Вийти із системи");
                CustomerConsoleUI.printMenu("2) Переглянути свої дані");
                CustomerConsoleUI.printMenu("3) Історія робіт");
                CustomerConsoleUI.printMenu("4) Додати послугу");
                CustomerConsoleUI.printMenu("5) Список робітників");
                CustomerConsoleUI.printMenu("6) Створити проєкт");

                if ("Адмін".equals(userRole) || "Компанія".equals(userRole)) {
                    CustomerConsoleUI.printMenu("7) Змінити користувача");
                    CustomerConsoleUI.printMenu("8) Редагування даних");
                    CustomerConsoleUI.printMenu("9) Пошук");
                }
            }

            CustomerConsoleUI.printMenu("0) для виходу");

            int choice = new UserInputHandler().promptUserForInteger("Ваш вибір");

            switch (choice) {
                case 1:
                    if ("".equals(userRole)) {
                        RegistrationService.registration();
                    } else {
                        Application.currentUser.setRole("");
                        show();
                    }
                    break;
                case 2:
                    if ("".equals(userRole)) {
                        AuthorizationService.authorization();
                    } else {
                        UserConsoleUI.displayUserInfo(Application.currentUser);
                    }
                    break;
                case 3:
                    if (!"".equals(userRole)) {
                        String[] args = {};
                        ProjectService.main(args);
                    }
                    break;
                case 4:
                    if (!"".equals(userRole)) {
                        ElectroServicesService.addService();
                    }
                    break;
                case 5:
                    if (!"".equals(userRole)) {
                        String[] args = {};
                        EmployersService.main(args);
                    }
                    break;
                case 6:
                    if (!"".equals(userRole)) {
                        ProjectService.addProject();
                    }
                    break;
                case 7:
                    if (!"".equals(userRole)) {
                        UserService.updateUserInMenu();
                    }
                    break;
                case 8:
                    if (!"".equals(userRole)) {
                        CustomerConsoleUI.printMenu("1) Змінити проєкт");
                        CustomerConsoleUI.printMenu("2) Змінити послугу");
                        CustomerConsoleUI.printMenu("3) Вихід");

                        int choicePoint = new UserInputHandler().promptUserForInteger("Ваш вибір");

                        switch (choicePoint) {
                            case 1:
                                ProjectService.editProject();
                                break;
                            case 2:
                                ElectroServicesService.editService();
                                break;
                            case 3:
                                break;
                            default:
                                CustomerConsoleUI.printSystemMessage("Невірний вибір. Спробуйте ще раз.");
                                break;
                        }
                    }
                    break;
                case 9:
                    if (!"".equals(userRole)) {
                        SearchService.searchService();
                    }
                    break;
                case 0:
                    CustomerConsoleUI.printTitle("Дякую, що скористалися нашою програмою.");
                    System.exit(0);
                    break;
                default:
                    CustomerConsoleUI.printSystemMessage("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
            if (choice == 0) {
                break;
            }
        }
    }
}
