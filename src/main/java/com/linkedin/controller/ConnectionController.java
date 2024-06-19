package com.linkedin.controller;

import com.linkedin.domain.model.Connection;

import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.controller.BaseController.connectionService;
import static com.linkedin.controller.BaseController.scanNum;
import static com.linkedin.controller.RequestController.invitations;
import static com.linkedin.controller.RequestController.invite;
import static com.linkedin.controller.UserController.currentUser;
import static com.linkedin.controller.UserController.userMenu;

public class ConnectionController {

    public static void connectionsMenu() {
        System.out.println("Connections Menu");
        System.out.println("""
                1. Connections
                2. Invitations
                3. Invite
                0. Exit
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        switch (choice) {
            case 1:
                System.out.println("\nConnections");
                connections();
                break;
            case 2:
                System.out.println("\nInvitations");
                invitations();
                break;
            case 3:
                System.out.println("\nInvite");
                invite();
                break;
            case 0:
                userMenu();
                break;
            default:
                System.out.println("Invalid Choice");
                connectionsMenu();
                break;
        }
    }

    private static void connections() {
        System.out.println("Connections:");
        ArrayList<Connection> connections = connectionService.getConnections(currentUser.getId());
        for (int i = 0; i < connections.size(); i++) {
            System.out.println(i + 1 + ". " + connections.get(i));
        }

        System.out.println("Choose User");
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice > 0 && choice <= connections.size()) {
            Connection connection = connections.get(choice - 1);
            System.out.println(connection);
            System.out.println("""
                    Do you want to remove this connection?
                    1. Yes
                    2. No
                    """);
            System.out.print("> ");
            int choice2 = scanNum.nextInt();
            if (choice2 == 1) {
                if (connectionService.cancelConnection(connection.getId())) {
                    System.out.println("Connection Removed");
                } else {
                    System.out.println("Something went wrong, please try again");
                    connectionsMenu();
                }
            }
        }

    }
}
