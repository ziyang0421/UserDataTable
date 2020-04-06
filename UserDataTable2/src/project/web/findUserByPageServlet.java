package project.web;

import project.domain.PageBean;
import project.domain.User;
import project.service.UserService;
import project.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ZIYANG on 2020/4/1.
 */
@WebServlet("/limit")
public class findUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        UserService userService = new UserServiceImpl();

        // 获取currentPage和rows
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if (currentPage == null || rows == null) {
            currentPage = "1";
            rows = "5";
        }

        // 获取请求查询请求参数列表
        Map<String, String[]> condition = request.getParameterMap();

        // 调用service层的findUserByPage()查询
        PageBean<User> pageBean = userService.findUserByPage(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);

        // 将pageBean保存到request域中
        request.setAttribute("pb", pageBean);
        request.setAttribute("condition", condition);

        // 转发到jsp完成显示操作
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
