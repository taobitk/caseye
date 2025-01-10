package servlet;

import repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addToCartServlet", urlPatterns = "/addToCart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String loggedCustomerEmail = (String) session.getAttribute("loggedCustomerEmail");
        if (loggedCustomerEmail == null) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        Integer customerId = (Integer) session.getAttribute("loggedCustomerId");
        if (customerId == null) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        String productIdParam = request.getParameter("productId");
        if (productIdParam == null || productIdParam.isEmpty()) {
            response.sendRedirect("customerDashboard?error=InvalidProduct");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("customerDashboard?error=InvalidProduct");
            return;
        }

        OrderRepository.ensurePendingOrderExists(customerId);
        OrderRepository.addToCart(customerId, productId);

        response.sendRedirect("customerDashboard?success=ProductAdded");
    }
}
