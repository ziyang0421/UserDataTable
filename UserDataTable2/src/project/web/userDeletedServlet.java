package project.web;

import project.service.UserService;
import project.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by ZIYANG on 2020/3/28.
 */
@WebServlet("/deleted")
public class userDeletedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取请求参数uid的List
        String[] uids = request.getParameterValues("uid");

        // 调用service层的deletedUser方法删除uid所有对象
        UserService userService = new UserServiceImpl();
        userService.deletedUser(uids);

        // 转发到list.jsp
        request.getRequestDispatcher("/list").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
