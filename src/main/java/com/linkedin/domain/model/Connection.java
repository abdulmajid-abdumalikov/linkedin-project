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
public class Connection extends BaseModel{
    private UUID senderID;
    private UUID receiverID;

    public static Connection map(ResultSet rs) throws SQLException {
        Connection connection = Connection.builder()
                .senderID(rs.getObject("sender_id", UUID.class))
                .receiverID(rs.getObject("receiver_id", UUID.class)).build();
        connection.setId(rs.getObject("id", UUID.class));
        connection.setCreatedDate(rs.getTimestamp("created_at").toLocalDateTime());
        connection.setUpdatedDate(rs.getTimestamp("updated_at").toLocalDateTime());
        return connection;
    }
}
