package service;

import dao.AuthDAO;
import model.User;

public class AuthService {

    private AuthDAO authDAO = new AuthDAO();

    public User login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return null;
        }

        return authDAO.login(username, password);
    }
}