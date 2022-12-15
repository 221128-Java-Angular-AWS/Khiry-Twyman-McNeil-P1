package com.amazon.persistence;

import com.amazon.exceptions.PasswordIncorrectException;
import com.amazon.exceptions.UserNotFoundException;
import com.amazon.pojos.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public User authenticate(String username, String password) throws UserNotFoundException, PasswordIncorrectException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) {
                throw new UserNotFoundException("Username Not Found");

            }

            User user = new User(rs.getInt("user_id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("username"), rs.getString("password"));

            if(user.getPassword().equals(password)) {
                return user;
            }

            throw new PasswordIncorrectException("That password is not correct");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Set<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<User> results = new HashSet<User>();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("username"), rs.getString("password"));
                results.add(user);
            }

            return results;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void updateUser(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ?, WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getUserID());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void create(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setUserID(rs.getInt("user_id"));
            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUser(Integer userId) {

        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
