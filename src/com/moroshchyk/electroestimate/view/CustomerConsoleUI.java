package com.moroshchyk.electroestimate.view;

import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Клас CustomerConsoleUI надає методи для виведення інформації на консоль у зручному форматі для користувача.
 */
public class CustomerConsoleUI {

    /**
     * Метод printTitle виводить заголовок з підкресленням.
     * @param text Текст заголовку.
     */
    public static void printTitle(String text)
    {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Color.GREEN)
            .fg(Color.BLACK)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("__ " + text + " __")
            .reset());
    }

    /**
     * Метод printFieldName виводить назву поля з двокрапкою.
     * @param text Текст назви поля.
     */
    public static void printFieldName(String text)
    {
        System.out.print(Ansi.ansi()
            .fgDefault()
            .bg(Color.BLACK)
            .fg(Color.WHITE)
            .a(text + ": ")
            .reset());
    }

    /**
     * Метод printField виводить значення поля.
     * @param text Текст значення поля.
     */
    public static void printField(String text)
    {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Color.BLACK)
            .fg(Color.GREEN)
            .a(Ansi.Attribute.ITALIC)
            .a(text)
            .reset());
    }

    /**
     * Метод printLine виводить лінію з заданим символом заповнення.
     * @param fillChar Символ заповнення.
     * @param length Довжина лінії.
     */
    public static void printLine(Character fillChar, int length )
    {
        String text = String.valueOf(fillChar).repeat(length);

        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Color.BLACK)
            .fg(Color.MAGENTA)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a(text)
            .reset());
    }

    /**
     * Метод printMenu виводить пункт меню.
     * @param text Текст пункту меню.
     */
    public static void printMenu(String text)
    {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Color.YELLOW)
            .fg(Color.BLACK)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("- " + text + " ")
            .reset());
    }

    /**
     * Метод printSystemMessage виводить системне повідомлення.
     * @param text Текст системного повідомлення.
     */
    public static void printSystemMessage(String text)
    {
        System.out.println(Ansi.ansi()
            .fgDefault()
            .bg(Color.RED)
            .fg(Color.BLACK)
            .a(Ansi.Attribute.INTENSITY_BOLD)
            .a("- " + text + " ")
            .reset());
    }

    /**
     * Метод promptUserForInput виводить запит для введення користувачем даних.
     * @param prompt Підказка для користувача.
     * @param scanner Об'єкт Scanner для зчитування введення користувача з консолі.
     * @return Введені користувачем дані у вигляді рядка.
     */
    public static String promptUserForInput(String prompt, Scanner scanner) {
        System.out.print(Ansi.ansi()
            .fgDefault()
            .bg(Color.YELLOW)
            .fg(Color.BLACK)
            .a(prompt + ": ")
            .reset());

        return scanner.nextLine();
    }
}
