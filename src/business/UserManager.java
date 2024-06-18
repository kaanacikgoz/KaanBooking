package business;

import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

}
