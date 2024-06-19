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
public class Chat extends BaseModel{
    private UUID senderID;
    private UUID receiverID;

    public static Chat map(ResultSet rs) throws SQLException {
        Chat chat = Chat.builder()
                .senderID(rs.getObject("sender_id", UUID.class))
                .receiverID(rs.getObject("receiver_id", UUID.class)).build();
        chat.setId(rs.getObject("id", UUID.class));
        chat.setCreatedDate(rs.getTimestamp("created_at").toLocalDateTime());
        chat.setUpdatedDate(rs.getTimestamp("updated_at").toLocalDateTime());
        return chat;
    }
}
