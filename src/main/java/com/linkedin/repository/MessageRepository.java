package com.linkedin.repository;

import com.linkedin.domain.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static com.linkedin.repository.BaseRepository.connection;

public class MessageRepository {

    // Retrieve messages for a specific chat, ordered by creation date
    public ArrayList<Message> getChatMessages(UUID chatId) {
        String GET_CHAT_MESSAGES = "select * from get_chat_messages(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CHAT_MESSAGES)) {
            preparedStatement.setString(1, chatId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Message> messages = new ArrayList<>();
            while (resultSet.next()) {
                messages.add(Message.map(resultSet));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Write a new message to a chat
    public void writeMessage(UUID chatId, UUID senderId, String text) {
        String WRITE_MESSAGE = "select write_message(?::uuid, ?::uuid, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(WRITE_MESSAGE)) {
            preparedStatement.setString(1, chatId.toString());
            preparedStatement.setString(2, senderId.toString());
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete all messages of a specific chat (usually called by deleteChat method in ChatRepository)
    public void deleteMessagesByChatId(UUID chatId) {
        String DELETE_MESSAGES_BY_CHAT_ID = "select delete_messages_by_chat_id(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MESSAGES_BY_CHAT_ID)) {
            preparedStatement.setString(1, chatId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
