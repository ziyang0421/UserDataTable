package project.service.serviceImpl;

import project.dao.UserDao;
import project.dao.daoImpl.UserDaoImpl;
import project.domain.PageBean;
import project.domain.User;
import project.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by ZIYANG on 2020/3/25.
 * UserServiceImpl实现了UserService接口
 */
public class UserServiceImpl implements UserService{
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> listAll() {
        // 调用dao层完成操作
        return dao.listAll();
    }

    @Override
    public void removeUser(int id) {
        // 调用dao层完成删除操作
        dao.remove(id);
    }

    @Override
    public User findUser(int id) {
        // 调用dao层完成删除操作
        return dao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        // 调用dao层完成删除操作
        dao.update(user);
    }

    @Override
    public void addUser(User user) {
        // 调用dao层完成添加操作
        dao.add(user);
    }

    @Override
    public void deletedUser(String[] uids) {
        // 删除前先判断是否uids长度是否符合
        if (uids != null && uids.length > 0) {
            // 调用dao层完成添加操作
            for (String el : uids) {
                dao.remove(Integer.parseInt(el));
            }
        }
    }

    @Override
    public PageBean findUserByPage(int currentPage, int rows, Map<String, String[]> condition) {
        if (currentPage <= 0) {     // 用户点击上一页，此时页面为1
            currentPage = 1;
        }

        // 创建PageBean对象
        PageBean<User> pageBean = new PageBean<>();
        // 调用dao层的getCount()获取所有的记录数
        int totalCount = dao.getCount(condition);
        pageBean.setTotalCount(totalCount);
        // 设置PageBean属性
        pageBean.setRows(rows);
        pageBean.setCurrentPage(currentPage);
        // 计算总共有多少页面
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;
        pageBean.setTotalPage(totalPage);

        if (currentPage > totalPage) {  // 用户点击下一页，此时在最后一页
            currentPage = totalPage;
            pageBean.setCurrentPage(currentPage);
        }
        // 计算当前页面第一个记录号
        int start = (pageBean.getCurrentPage() - 1) * pageBean.getRows();
        // 调用dao层的findUserByPage()查询List集合
        List<User> list = dao.findUserByPage(start, rows, condition);
        pageBean.setList(list);

        return pageBean;
    }
}
