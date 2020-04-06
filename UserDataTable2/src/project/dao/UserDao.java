package project.dao;

import project.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ZIYANG on 2020/3/25.
 */
public interface UserDao {
    /**
     * listAll：查找所有的User对象，封装成List集合
     * @return List<User>
     */
    List<User> listAll();

    void remove(int id);

    User findUserById(int id);

    void update(User user);

    void add(User user);

    int getCount(Map<String, String[]> condition);

    List<User> findUserByPage(int start, int rows, Map<String, String[]> condition);
}
