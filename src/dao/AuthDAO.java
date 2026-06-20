package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO {

    public User login(String username, String password) {

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    User user = new User();

                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    user.setDoctorId(resultSet.getInt("doctor_id"));

                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }
}