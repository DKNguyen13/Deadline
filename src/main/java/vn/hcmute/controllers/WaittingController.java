package vn.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.models.UserModel;

import java.io.IOException;
@WebServlet("/waiting")
public class WaittingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            UserModel u=(UserModel) session.getAttribute("account");
            req.setAttribute("username", u.getUserName());
            if(u.getRoleid()==2) {
                resp.sendRedirect(req.getContextPath()+"/admin/home");}
        }else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
