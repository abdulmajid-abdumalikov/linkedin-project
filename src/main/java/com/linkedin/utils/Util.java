package com.linkedin.utils;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    @Getter
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/linkedin",
                    "postgres",
                    "0800"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

