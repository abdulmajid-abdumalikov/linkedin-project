package com.linkedin;

import java.util.Scanner;

import static com.linkedin.controller.UserController.login;
import static com.linkedin.controller.UserController.register;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n--Welcome To Linkedin!--");
        mainMenu();
    }

    public static void mainMenu() {
        System.out.print("""
                1. Login
                2. Register
                0. Exit
                """);
        System.out.print("> ");
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
        }
    }
}