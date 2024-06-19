package com.linkedin.repository;

import com.linkedin.domain.model.Chat;
import com.linkedin.domain.model.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.repository.BaseRepository.connection;

public class ChatRepository {


    // Retrieve all chats for a user, sorted by the date of the last message
    public ArrayList<Chat> getUserChats(UUID userId) {
        String GET_USER_CHATS = "select * from get_user_chats(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_CHATS)) {
            preparedStatement.setString(1, userId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Chat> userChats = new ArrayList<>();
            while (resultSet.next()) {
                userChats.add(Chat.map(resultSet));
            }
            return userChats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get or create a chat between two users
    public UUID getOrCreateChat(UUID senderId, UUID receiverId) {
        String GET_OR_CREATE_CHAT = "select get_or_create_chat(?::uuid, ?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_OR_CREATE_CHAT)) {
            preparedStatement.setString(1, senderId.toString());
            preparedStatement.setString(2, receiverId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return UUID.fromString(resultSet.getString(1));
            } else {
                throw new RuntimeException("Failed to create or retrieve chat.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete a specific chat and its associated messages
    public void deleteChat(UUID chatId) {
        String DELETE_CHAT = "select delete_chat(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHAT)) {
            preparedStatement.setString(1, chatId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieve all connections for a user, sorted by creation date
    public ArrayList<com.linkedin.domain.model.Connection> getUserConnections(UUID userId) {
        String GET_USER_CONNECTIONS = "select * from get_user_connections(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_CONNECTIONS)) {
            preparedStatement.setString(1, userId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<com.linkedin.domain.model.Connection> userConnections = new ArrayList<>();
            while (resultSet.next()) {
                userConnections.add(Connection.map(resultSet));
            }
            return userConnections;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
