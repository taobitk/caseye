package servlet;

import model.Product;
import repository.CustomerRepository;
import repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerDashboardServlet", urlPatterns = "/customerDashboard")
public class CustomerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loggedCustomerEmail = (String) session.getAttribute("loggedCustomerEmail");

        // Kiểm tra khách hàng đã đăng nhập
        if (loggedCustomerEmail == null || !CustomerRepository.isEmailDuplicate(loggedCustomerEmail)) {
            response.sendRedirect("customerLogin.jsp");
            return;
        }

        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        List<Product> products = ProductRepository.getAllProducts();
        request.setAttribute("products", products);

        // Chuyển hướng tới trang customerDashboard.jsp
        request.getRequestDispatcher("/WEB-INF/Customer/customerDashboard.jsp").forward(request, response);
    }
}
