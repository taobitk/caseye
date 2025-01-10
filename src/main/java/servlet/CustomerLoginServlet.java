package servlet;

import repository.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "customerLoginServlet", urlPatterns = "/customerLogin")
public class CustomerLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Email and Password are required.");
            request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            return;
        }

        // Verify email
        if (!CustomerRepository.isEmailDuplicate(email)) {
            request.setAttribute("error", "Email does not exist. Please register first.");
            request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            return;
        }

        // Verify password
        if (!CustomerRepository.isPasswordDuplicate(password)) {
            request.setAttribute("error", "Incorrect password. Please try again.");
            request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
            return;
        }

        // Successful login
        HttpSession session = request.getSession();
        session.setAttribute("loggedCustomerEmail", email);
        response.sendRedirect("customerDashboard"); // Redirect to CustomerDashboardServlet
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("customerLogin.jsp");
    }
}
