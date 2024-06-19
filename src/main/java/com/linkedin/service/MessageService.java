package com.linkedin.service;

import com.linkedin.domain.model.Message;

import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.service.BaseService.messageRepository;

public class MessageService {


    // Retrieve messages for a specific chat
    public ArrayList<Message> getChatMessages(UUID chatId) {
        return messageRepository.getChatMessages(chatId);
    }

    // Write a new message to a chat
    public boolean writeMessage(UUID chatId, UUID senderId, String text) {
        messageRepository.writeMessage(chatId, senderId, text);
        return true;
    }

    // Delete all messages of a specific chat
    public boolean deleteMessagesByChatId(UUID chatId) {
        messageRepository.deleteMessagesByChatId(chatId);
        return true;
    }
}

