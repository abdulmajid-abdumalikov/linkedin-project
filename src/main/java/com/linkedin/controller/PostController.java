package com.linkedin.controller;

import static com.linkedin.Main.mainMenu;
import static com.linkedin.controller.BaseController.scanNum;

public class PostController {

    public static void postsMenu() {
        System.out.println("Posts Menu");
        System.out.println("coming soon...");
        System.out.println("0. Exit");
        int choice = scanNum.nextInt();
        if (choice == 0) {
            mainMenu();
        } else {
            System.out.println("Invalid choice");
            postsMenu();
        }

    }
}
