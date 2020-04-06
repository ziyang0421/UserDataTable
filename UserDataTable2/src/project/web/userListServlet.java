package project.web;

import project.domain.User;
import project.service.UserService;
import project.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ZIYANG on 2020/3/25.
 */
@WebServlet("/list")
public class userListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 调用service的方法查找所有的User对象，并封装成User的list集合
        List<User> list;
        UserService userService = new UserServiceImpl();
        list = userService.listAll();
/*        for (User el : list) {
            System.out.println(el);
        }*/

        // 将list保存在request域中
        request.setAttribute("userList", list);
        // 转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
