package servlet;

import model.Product;
import repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products", "/addProduct", "/editProduct", "/deleteProduct"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = ProductRepository.getAllProducts();
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/WEB-INF/admin/product-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        switch (action) {
            case "/addProduct":
                addProduct(request, response);
                break;
            case "/editProduct":
                editProduct(request, response);
                break;
            case "/deleteProduct":
                deleteProduct(request, response);
                break;
            default:
                response.sendRedirect("products");
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String imageUrl = request.getParameter("imageUrl");
        String description = request.getParameter("description");

        Product product = new Product(0, name, type, price, stockQuantity, imageUrl, description);
        ProductRepository.addProduct(product);

        response.sendRedirect("products");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String imageUrl = request.getParameter("imageUrl");
        String description = request.getParameter("description");

        Product product = new Product(id, name, type, price, stockQuantity, imageUrl, description);
        ProductRepository.updateProduct(product);

        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductRepository.deleteProduct(id);

        response.sendRedirect("products");
    }
}
