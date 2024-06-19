package com.linkedin.controller;

import com.linkedin.domain.model.Chat;
import com.linkedin.domain.model.Connection;

import java.util.ArrayList;

import static com.linkedin.controller.BaseController.chatService;
import static com.linkedin.controller.BaseController.scanNum;
import static com.linkedin.controller.UserController.currentUser;
import static com.linkedin.controller.UserController.userMenu;
import static com.linkedin.repository.BaseRepository.messageService;

public class ChatController {

    public static void messagingMenu(){
        System.out.println("Messaging Menu");
        System.out.println("""
                1. Chats
                2. New chat
                0. Exit
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        switch (choice) {
            case 1:
                System.out.println("\nChats");
                chats();
                break;
            case 2:
                System.out.println("\nNew chat");
                newChat();
                break;
            case 0:
                userMenu();
                break;
            default:
                System.out.println("\nInvalid choice");
                break;
        }

    }

    private static void newChat() {
        System.out.println("\nNew chat");
        System.out.println("""
                Do you want to create a new chat?
                1. Yes
                2. No
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice == 1) {
            ArrayList<Connection> userConnections = chatService.getUserConnections(currentUser.getId());
            System.out.println("\nConnections");
            for (int i = 0; i < userConnections.size(); i++) {
                System.out.println((i + 1) + ". " + userConnections.get(i));
            }
            System.out.print("> ");
            int choice2 = scanNum.nextInt();
            if (choice2 > userConnections.size()) {
                System.out.println("\nInvalid choice");
                newChat();
            } else {
                chatService.getOrCreateChat(currentUser.getId(), userConnections.get(choice2 - 1).getId());
                userMenu();
            }
        } else if (choice == 2) {
            userMenu();
        }

    }

    private static void chats() {
        ArrayList<Chat> userChats = chatService.getUserChats(currentUser.getId());
        for (int i = 0; i < userChats.size(); i++) {
            System.out.println(i+1 + ". " + userChats.get(i));
        }
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice > userChats.size()) {
            System.out.println("\nInvalid choice");
            chats();
        } else {
            chatMenu(userChats.get(choice - 1));
            chats();
        }

    }

    private static void chatMenu(Chat chat) {
        System.out.println("\n" + chat);
        System.out.println("""
                Do you want to send a message?
                1. Yes
                2. No
                """);
        System.out.print("> ");
        int choice = scanNum.nextInt();
        if (choice == 1) {
            System.out.println("\nMessage");
            System.out.print("> ");
            String message = scanNum.next();
            messageService.writeMessage(chat.getId(), currentUser.getId(), message);
            chatMenu(chat);
        } else if (choice == 2) {
            userMenu();
        } else {
            System.out.println("\nInvalid choice");
            chatMenu(chat);
        }
    }
}
