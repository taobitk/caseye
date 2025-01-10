package servlet;

import model.Customer;
import repository.CustomerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers", "/addCustomer", "/editCustomer", "/deleteCustomer"})
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = CustomerRepository.getAllCustomers();
        request.setAttribute("customers", customerList);
        request.getRequestDispatcher("/WEB-INF/admin/customer-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/addCustomer":
                addCustomer(request, response);
                break;
            case "/editCustomer":
                editCustomer(request, response);
                break;
            case "/deleteCustomer":
                deleteCustomer(request, response);
                break;
            default:
                response.sendRedirect("customers");
                break;
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        String errorMessage = null;

        // Kiểm tra dữ liệu đầu vào
        if (name == null || name.trim().isEmpty()) {
            errorMessage = "Tên không được để trống.";
        } else if (email == null || email.trim().isEmpty()) {
            errorMessage = "Email không được để trống.";
        } else if (CustomerRepository.isEmailDuplicate(email)) {
            errorMessage = "Email đã tồn tại trong hệ thống.";
        } else if (phone == null || phone.trim().isEmpty()) {
            errorMessage = "Số điện thoại không được để trống.";
        } else if (CustomerRepository.isPhoneDuplicate(phone)) {
            errorMessage = "Số điện thoại đã tồn tại trong hệ thống.";
        }

        if (errorMessage != null) {
            request.setAttribute("addCustomerError", errorMessage);
            doGet(request, response);
            return;
        }

        try {
            Customer customer = new Customer(0, name, phone, email, address, gender, password);
            CustomerRepository.addCustomer(customer);
            response.sendRedirect("customers");
        } catch (Exception e) {
            request.setAttribute("addCustomerError", "Đã xảy ra lỗi khi thêm khách hàng. Vui lòng thử lại.");
            doGet(request, response);
        }
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        Customer customer = new Customer(id, name, phone, email, address, gender, password);
        CustomerRepository.updateCustomer(customer);

        response.sendRedirect("customers");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerRepository.deleteCustomer(id);
        response.sendRedirect("customers");
    }
}
