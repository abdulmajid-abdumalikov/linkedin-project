package com.linkedin.controller;

import com.linkedin.service.ChatService;
import com.linkedin.service.ConnectionService;
import com.linkedin.service.RequestService;
import com.linkedin.service.UserService;

import java.util.Scanner;

public interface BaseController {
    Scanner scanNum = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    static UserService userService = new UserService();
    static ConnectionService connectionService = new ConnectionService();
    static RequestService requestService = new RequestService();
    static ChatService chatService = new ChatService();

}
