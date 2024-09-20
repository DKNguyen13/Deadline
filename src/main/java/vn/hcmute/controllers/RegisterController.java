package vn.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.dao.IUserDao;
import vn.hcmute.dao.impl.UserDaoImpl;
import vn.hcmute.models.UserModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    IUserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("passWord");
        String phone = request.getParameter("phone");
        UserModel userModel = new UserModel(username, password, email, fullName, phone, 2);
        try {
            // Kiểm tra xem tài khoản đã tồn tại
            if (userDao.FindByUsername(username) != null) {
                request.getSession().setAttribute("alert", "Tài khoản đã tồn tại.");
                System.out.printf("TK tồn tại");
                resp.sendRedirect("views/register.jsp"); // Redirect về trang đăng ký
            } else {
                userDao.Insert(userModel);
                request.getSession().setAttribute("alert", "Đăng ký thành công!");
                request.getRequestDispatcher("/views/login.jsp").forward(request, resp);

            }
        } catch (ClassNotFoundException | SQLException e) {
            request.getSession().setAttribute("alert", "Đã xảy ra lỗi: " + e.getMessage());
        }

    }
}