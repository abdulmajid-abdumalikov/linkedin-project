package com.linkedin.domain.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestDao {
    private UUID requestID;
    private UUID userID;
    private String name;
    private String username;
    private String position;
    private LocalDateTime sentAt;

    public static RequestDao map(ResultSet resultSet) throws SQLException {
        return RequestDao.builder()
                .requestID(resultSet.getObject("req_id", UUID.class))
                .userID(resultSet.getObject("receiver_id", UUID.class))
                .name(resultSet.getString("name"))
                .username(resultSet.getString("username"))
                .position(resultSet.getString("position"))
                .sentAt(resultSet.getTimestamp("sent_date").toLocalDateTime())
                .build();
    }

    @Override
    public String toString() {
        return requestID + " | " + name + " | " + username + " | " + position;
    }
}
