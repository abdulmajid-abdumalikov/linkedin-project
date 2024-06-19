package com.linkedin.domain.model;

import com.linkedin.domain.enumiration.RequestStatus;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request extends BaseModel {
    private UUID senderID;
    private UUID receiverID;
    private RequestStatus status;

    public static Request map(ResultSet rs) throws SQLException {
        Request connectionRequest = Request.builder()
                .senderID(rs.getObject("sender_id", UUID.class))
                .receiverID(rs.getObject("receiver_id", UUID.class))
                .status(RequestStatus.valueOf(rs.getString("status")))
                .build();
        connectionRequest.setId(rs.getObject("id", UUID.class));
        connectionRequest.setCreatedDate(rs.getTimestamp("created_at").toLocalDateTime());
        connectionRequest.setUpdatedDate(rs.getTimestamp("updated_at").toLocalDateTime());
        return connectionRequest;
    }
}
