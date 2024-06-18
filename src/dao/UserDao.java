package dao;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection connection;

    public UserDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("user_username"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(User.Role.valueOf(resultSet.getString("user_role").toUpperCase()));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

}
