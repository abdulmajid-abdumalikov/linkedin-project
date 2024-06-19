package com.linkedin.service;

import com.linkedin.domain.model.Chat;
import com.linkedin.domain.model.Connection;
import com.linkedin.repository.ChatRepository;

import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.service.BaseService.chatRepository;

public class ChatService {

    // Retrieve all chats for a user
    public ArrayList<Chat> getUserChats(UUID userId) {
        return chatRepository.getUserChats(userId);
    }

    // Get or create a chat between two users
    public UUID getOrCreateChat(UUID senderId, UUID receiverId) {
        return chatRepository.getOrCreateChat(senderId, receiverId);
    }

    // Delete a specific chat
    public boolean deleteChat(UUID chatId) {
        chatRepository.deleteChat(chatId);
        return true;
    }

    // Retrieve all connections for a user
    public ArrayList<Connection> getUserConnections(UUID userId) {
        return chatRepository.getUserConnections(userId);
    }
}

