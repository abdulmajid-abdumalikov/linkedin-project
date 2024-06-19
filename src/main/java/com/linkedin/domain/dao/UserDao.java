package com.linkedin.domain.dao;

import com.linkedin.domain.enumiration.RelationState;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDao {
    private UUID userID;
    private String name;
    private String username;
    private String position;
    private RelationState state;

    public static UserDao map(ResultSet resultSet) throws SQLException {
        return UserDao.builder()
                .name(resultSet.getString("name"))
                .username(resultSet.getString("username"))
                .position(resultSet.getString("position"))
                .userID(resultSet.getObject("id", UUID.class))
                .state(RelationState.valueOf(resultSet.getString("state")))
                .build();
    }

    @Override
    public String toString() {
        return name + " | " + username + " | " + position + " (" + state + ')';
    }
}
