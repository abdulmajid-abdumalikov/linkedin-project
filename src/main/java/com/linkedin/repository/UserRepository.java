package com.linkedin.repository;

import com.linkedin.domain.dao.UserDao;
import com.linkedin.domain.model.BaseModel;
import com.linkedin.domain.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements BaseRepository {

    private static final String SEARCH = "select * from search_username(?, ?::uuid)";
    private static final String REGISTER = "select * from save_user(?,?,?,?)";
    private static final String LOGIN = "select * from sign_in(?,?)";

    public ArrayList<UserDao> getUsers(String username, UUID userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userID.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<UserDao> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(UserDao.map(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public User login(String username, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return User.map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String register(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REGISTER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPosition());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
