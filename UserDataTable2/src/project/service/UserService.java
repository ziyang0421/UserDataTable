package project.service;

import project.domain.PageBean;
import project.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ZIYANG on 2020/3/25.
 */
public interface UserService {

    /**
     * 查找mysql中所有的User，并封装成List集合
     * @return List></User>
     */
    List<User> listAll();

    /**
     * 按id删除用户
     * @param id
     */
    void removeUser(int id);

    /**
     * 按id查询用户信息
     * @param id
     * @return
     */
    User findUser(int id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 删除多个用户
     * @param uids
     */
    void deletedUser(String[] uids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param contdition
     * @return
     */
    PageBean findUserByPage(int currentPage, int rows, Map<String, String[]> condition);
}
