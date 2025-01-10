package servlet;

import repository.ReportsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ReportsServlet", urlPatterns = {"/reports"})
public class ReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> mergedData = ReportsRepository.getOrdersWithDetailsMerged();
        request.setAttribute("mergedData", mergedData);
        request.getRequestDispatcher("/WEB-INF/admin/reports.jsp").forward(request, response);
    }
}
