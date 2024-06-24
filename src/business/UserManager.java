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

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User user:users) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i] = user.getRole();
            userList.add(rowObject);
        }
        return userList;
    }

    public boolean addUser(User user) {
        return this.userDao.addUser(user);
    }

    public boolean updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    public boolean deleteUser(int user_id) {
        return this.userDao.deleteUser(user_id);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<User> searchForTable(int userId, User.Role role) {
        StringBuilder query = new StringBuilder("SELECT * FROM public.user");
        ArrayList<Object> parameters = new ArrayList<>();
        boolean hasConditions = false;

        if (userId != 0) {
            query.append(" WHERE user_id=?");
            parameters.add(userId);
            hasConditions = true;
        }

        if (role != null) {
            if (hasConditions) {
                query.append(" AND user_role=?");
            } else {
                query.append(" WHERE user_role=?");
            }
            parameters.add(role.toString());
        }

        return this.userDao.selectByQuery(query.toString(), parameters);
    }

}
