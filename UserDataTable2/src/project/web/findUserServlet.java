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

/**
 * Created by ZIYANG on 2020/3/27.
 */
@WebServlet("/find")
public class findUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 调用service层的findUser方法查找对应id的User
        UserService userService = new UserServiceImpl();
        User user = userService.findUser(Integer.parseInt(request.getParameter("id")));

        // 将user存储在request域中
        request.setAttribute("updateUser", user);

        // 转发到update.jsp完成修改操作
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
