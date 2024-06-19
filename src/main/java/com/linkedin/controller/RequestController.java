package com.linkedin.controller;

import com.linkedin.domain.dao.RequestDao;
import com.linkedin.domain.dao.UserDao;
import com.linkedin.domain.model.Request;

import java.util.ArrayList;

import static com.linkedin.controller.BaseController.scanNum;
import static com.linkedin.controller.BaseController.scanStr;
import static com.linkedin.controller.UserController.*;

public class RequestController {
    public static void invitations() {
        System.out.println("Invitations");
        System.out.println("""
                1. Sent Invitations
                2. Received Invitations
                0. Exit
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Sent Invitations");
                sendInvitation();
                break;
            case 2:
                System.out.println("Received Invitations");
                receiveInvitation();
                break;
            case 0:
                userMenu();
                break;
            default:
                System.out.println("Invalid Choice");
                invitations();
                break;
        }

    }

    private static void receiveInvitation() {
        ArrayList<RequestDao> receivedRequests = requestService.getReceivedRequests(currentUser.getId());
        for (int i = 0; i < receivedRequests.size(); i++) {
            System.out.println(i + 1 + " " + receivedRequests.get(i));
        }
        System.out.println("Choose request" +
                "0. Exit");
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice >= receivedRequests.size() && choice != 0) {
            System.out.println("Invalid Choice");
            invitations();
        } else if (choice == 0) {
            invitations();
        }
        RequestDao request = receivedRequests.get(choice - 1);
        System.out.println(request);

        System.out.println("""
                Do you want to do this request?
                1. Accept
                2. Ignore
                0. Exit
                """);
        System.out.print("> ");
        int choice2 = scanNum.nextInt();
        if (choice2 == 1) {
            if (requestService.accept(request.getRequestID())) {
                System.out.println("Request accepted");
                invitations();
            } else {
                System.out.println("Something went wrong. Request not accepted, try again.");
                invitations();
            }

        } else if (choice2 == 2) {
            if (requestService.ignore(request.getRequestID())) {
                System.out.println("Request ignored");
                invitations();
            } else {
                System.out.println("Something went wrong. Request not ignored, try again.");
                invitations();
            }
        } else if (choice2 == 3) {
            invitations();
        } else {
            System.out.println("Invalid Choice");
            invitations();
        }


    }

    private static void sendInvitation() {
        ArrayList<RequestDao> sentRequests = requestService.getSentRequests(currentUser.getId());
        for (int i = 0; i < sentRequests.size(); i++) {
            System.out.println(i + 1 + " " + sentRequests.get(i));
        }
        System.out.println("Choose request" +
                "0. Exit");
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice == 0) {
            invitations();
        } else if (choice >= sentRequests.size()) {
            System.out.println("Invalid Choice");
            invitations();
        }
        RequestDao request = sentRequests.get(choice - 1);
        System.out.println(request);

        System.out.println("""
                Do you want to withdraw the request?
                1. Yes
                2. No
                """);
        System.out.print("> ");
        int choice2 = scanNum.nextInt();
        if (choice2 == 1) {
            if (requestService.withdraw(request.getRequestID())) {
                System.out.println("Request withdrawn");
                invitations();
            } else {
                System.out.println("Something went wrong. Request not withdrawn, try again.");
                invitations();
            }
        } else if (choice2 == 2) {
            invitations();
        } else {
            System.out.println("Invalid Choice");
            invitations();
        }


    }

    public static void invite() {
        System.out.println("Invite");
        System.out.print("Enter username: ");
        String username = scanStr.nextLine();
        ArrayList<UserDao> users = userService.searchByUsername(username, currentUser.getId());
        if (users.isEmpty()) {
            System.out.println("User not found");
            invite();
        } else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + 1 + ". " + users.get(i));
            }
            System.out.println("Choose user");
            System.out.print("> ");
            int choice = scanNum.nextInt();
            if (choice >= users.size()) {
                System.out.println("Invalid Choice");
                invite();
            } else {
                UserDao user = users.get(choice - 1);
                System.out.println(user);
                System.out.println("""
                        Do you want to send invitation to this user?
                        1. Yes
                        2. No
                        """);
                System.out.print("> ");
                int choice2 = scanNum.nextInt();
                if (choice2 == 1) {
                    switch (user.getState()) {
                        case WITHDRAWN -> {
                            System.out.println("You can send request after 2 weeks after withdrawing");
                        }
                        case PENDING -> {
                            System.out.println("You already sent request");
                        }
                        case CONNECTED -> {
                            System.out.println("You already have connection with this user");
                        }
                        case NOT_CONNECTED -> {
                            if (requestService.save(Request.builder().senderID(currentUser.getId()).receiverID(user.getUserID()).build())) {
                                System.out.println("Request sent");
                                invitations();
                            } else {
                                System.out.println("Something went wrong. Request not sent, try again.");
                                invitations();
                            }
                        }
                    }
                } else if (choice2 == 2) {
                    invitations();
                } else {
                    System.out.println("Invalid Choice");
                    invitations();
                }
            }
        }
    }
}
