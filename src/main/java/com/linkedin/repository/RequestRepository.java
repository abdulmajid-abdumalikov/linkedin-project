package com.linkedin.repository;

import com.linkedin.domain.dao.RequestDao;
import com.linkedin.domain.model.BaseModel;
import com.linkedin.domain.model.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class RequestRepository implements BaseRepository {
    private static final String INSERT = "select * from send_request(?::uuid, ?::uuid)";
    private static final String IGNORE = "select * from ignore_request(?::uuid)";
    private static final String ACCEPT = "select * from accept_request(?::uuid)";
    private static final String WITHDRAW = "select * from withdraw_request(?::uuid)";


    public String save(Request request) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, request.getSenderID().toString());
            preparedStatement.setString(2, request.getReceiverID().toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String create(BaseModel baseModel) {
        return "";
    }

    @Override
    public String update(UUID id, BaseModel update) {
        return "";
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Optional get(UUID id) {
        return Optional.empty();
    }

    public ArrayList<RequestDao> getReceivedRequestsOfUser(UUID userId) {
        String GET_RECEIVED_REQUESTS_OF_USER = "select * from get_received_requests_of_user(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_RECEIVED_REQUESTS_OF_USER)) {
            preparedStatement.setString(1, userId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<RequestDao> incomingRequests = new ArrayList<>();
            while (resultSet.next()) {
                incomingRequests.add(RequestDao.map(resultSet));
            }
            return incomingRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<RequestDao> getSentRequestsOfUser(UUID userId) {
        String GET_SENT_REQUESTS_OF_USER = "select * from get_sent_requests_of_user(?::uuid)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SENT_REQUESTS_OF_USER)) {
            preparedStatement.setString(1, userId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<RequestDao> sentRequests = new ArrayList<>();
            while (resultSet.next()) {
                sentRequests.add(RequestDao.map(resultSet));
            }
            return sentRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String ignore(String requestID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(IGNORE)) {
            preparedStatement.setString(1, requestID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String accept(String requestID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT)) {
            preparedStatement.setString(1, requestID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String withdraw(String requestID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(WITHDRAW)) {
            preparedStatement.setString(1, requestID);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
