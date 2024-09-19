package vn.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;
import vn.hcmute.services.impl.UserService;
import vn.hcmute.utils.Constant;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    IUserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String remember = req.getParameter("remember");
        //Kiểm tra tham số
        boolean isRememberMe = false;
        if("on".equals(remember)){
            isRememberMe = true;
        }
        String alertMsg="";
        if(username.isEmpty() || password.isEmpty()){
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }
        //Xử lý bài toán

        UserModel user = service.Login(username, password);
        if(user!=null){
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if(isRememberMe){
                saveRemeberMe(resp, username);
            }
            resp.sendRedirect(req.getContextPath()+"/waiting");
            System.out.println("Login sucessful");  // In thử biến alertMsg
        }else{
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            System.out.println("Alert Message: " + alertMsg);  // In thử biến alertMsg
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void saveRemeberMe(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER,
                username);
        cookie.setMaxAge(30*60);
        resp.addCookie(cookie);
    }

}
