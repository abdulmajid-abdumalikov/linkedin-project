package com.linkedin.domain.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message extends BaseModel {
    private String message;
    private UUID senderID;
    private UUID receiverID;
    private UUID chatID;

    public static Message map(ResultSet rs) throws SQLException {
        Message message = Message.builder()
                .message(rs.getString("message"))
                .senderID(rs.getObject("sender_id", UUID.class))
                .receiverID(rs.getObject("receiver_id", UUID.class))
                .chatID(rs.getObject("chat_id", UUID.class))
                .build();
        message.setId(rs.getObject("id", UUID.class));
        message.setCreatedDate(rs.getTimestamp("created_at").toLocalDateTime());
        message.setUpdatedDate(rs.getTimestamp("updated_at").toLocalDateTime());
        return message;
    }
}
