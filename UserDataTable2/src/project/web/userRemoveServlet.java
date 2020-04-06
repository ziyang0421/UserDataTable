package project.web;

import project.service.UserService;
import project.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZIYANG on 2020/3/27.
 */
@WebServlet("/remove")
public class userRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 调用service层的removeUser方法删除对象
        UserService userService = new UserServiceImpl();
        userService.removeUser(Integer.parseInt(request.getParameter("id")));

        // 转发到userListServlet，重新刷新列表
        request.getRequestDispatcher("/list").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
