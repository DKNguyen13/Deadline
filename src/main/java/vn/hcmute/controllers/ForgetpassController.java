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

@WebServlet("/forgot-password")
public class ForgetpassController extends HttpServlet {
    IUserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
String email = request.getParameter("email");
        // Kiểm tra xem tài khoản đã tồn tại
        UserModel user = userDao.FindByUsername(username);
        UserModel user1 = userDao.FindByEmail(email);
        if (user == null && user1==null || user != null && user1==null || user == null && user1!= null) {
            request.getSession().setAttribute("message", "Tài khoản không tồn tại.");
            resp.sendRedirect("views/resetpass.jsp"); // Redirect về resetpass
        } else if(user != null && user1!=null) {
            String newPassword = generateRandomPassword();

            // Cập nhật mật khẩu mới
            try {
                userDao.updatePassword(username, newPassword);
                request.getSession().setAttribute("message", "Mật khẩu mới đã được cập nhật: " + newPassword);
            } catch (SQLException e) {
                request.getSession().setAttribute("message", "Đã xảy ra lỗi khi cập nhật mật khẩu.");
            }
            resp.sendRedirect("views/resetpass.jsp"); // Redirect về trang resetpass để hiển thị thông báo
        }
    }

    private String generateRandomPassword() {
        int randomNum = (int) (Math.random() * 1000); // Sinh số ngẫu nhiên từ 0 đến 999
        return String.format("%03d", randomNum); // Đảm bảo rằng mật khẩu có 3 chữ số
    }
}
