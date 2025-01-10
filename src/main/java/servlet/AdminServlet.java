package servlet;

import repository.AdminRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = {"/login","/admin/logout","/admin"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("singinAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if (AdminRepository.validateAdmin(username, password)) {
            req.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("singinAdmin.jsp").forward(req, resp);
        }
    }



}
