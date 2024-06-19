package com.linkedin.service;

import com.linkedin.repository.*;

public interface BaseService {
    UserRepository userRepository = new UserRepository();
    ConnectionRepository connectionRepository = new ConnectionRepository();
    RequestRepository requestRepository = new RequestRepository();
    ChatRepository chatRepository = new ChatRepository();
    MessageRepository messageRepository = new MessageRepository();



}
