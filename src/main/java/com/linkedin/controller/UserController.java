package com.linkedin.controller;

import com.linkedin.domain.model.User;
import com.linkedin.service.RequestService;
import com.linkedin.service.UserService;

import java.util.Scanner;

import static com.linkedin.Main.mainMenu;
import static com.linkedin.controller.BaseController.scanNum;
import static com.linkedin.controller.BaseController.scanStr;
import static com.linkedin.controller.ChatController.messagingMenu;
import static com.linkedin.controller.ConnectionController.connectionsMenu;
import static com.linkedin.controller.PostController.postsMenu;

public class UserController {

    public static User currentUser = null;
    public static final UserService userService = new UserService();
    public static final RequestService requestService = new RequestService();

    public static void login() {
        System.out.println("\nPlease enter your details for Login");
        System.out.print("Username: ");
        String username = scanStr.nextLine();
        System.out.print("Password: ");
        String password = scanStr.nextLine();

        currentUser = userService.login(
                username, password
        );
        userMenu();
    }

    public static void register() {
        System.out.println("\nPlease enter your details for Registration");
        System.out.print("Name: ");
        String name = scanStr.nextLine();
        System.out.print("Username: ");
        String username = scanStr.nextLine();
        System.out.print("Password: ");
        String password = scanStr.nextLine();
        System.out.print("Position: ");
        String position = scanStr.nextLine();

        User user = User.builder()
                .name(name)
                .username(username)
                .password(password)
                .position(position)
                .build();
        String register = userService.register(user);

        System.out.println(register);

        mainMenu();

    }

    static void userMenu() {
        System.out.println("\nWelcome " + currentUser.getName() + "!");
        System.out.println("""
                Please select one of the following options:
                1. Connections
                2. Messaging
                3. Posts
                4. Logout
                0. Exit
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        switch (choice) {
            case 1:
                System.out.println("\nConnections");
                connectionsMenu();
                break;
            case 2:
                System.out.println("\nMessaging");
                messagingMenu();
                break;
            case 3:
                System.out.println("\nPosts");
                postsMenu();
                break;
            case 4:
                System.out.println("""
                        Do you want to Logout?
                        1. Yes
                        2. No
                        """);
                System.out.println("> ");
                int choice2 = scanNum.nextInt();
                if (choice2 == 1) {
                    currentUser = null;
                    mainMenu();
                } else if (choice2 == 2) {
                    userMenu();
                } else {
                    System.out.println("Invalid choice");
                    userMenu();
                }
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
