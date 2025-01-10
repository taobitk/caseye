<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="repository.OrderRepository" %>

<%
  // Lấy ID khách hàng từ session
  String loggedCustomerEmail = (String) session.getAttribute("loggedCustomerEmail");
  if (loggedCustomerEmail == null) {
    response.sendRedirect("customerLogin.jsp");
    return;
  }

  int loggedCustomerId = (int) session.getAttribute("loggedCustomerId");

  // Lấy danh sách sản phẩm trong giỏ hàng
  List<Map<String, Object>> cartItems = OrderRepository.getCartItems(loggedCustomerId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Giỏ hàng</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">Giỏ hàng của bạn</h2>

  <table class="table table-bordered mt-4">
    <thead class="table-dark">
    <tr>
      <th>Tên sản phẩm</th>
      <th>Hình ảnh</th>
      <th>Số lượng</th>
      <th>Giá</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (cartItems == null || cartItems.isEmpty()) {
    %>
    <tr>
      <td colspan="4" class="text-center">Giỏ hàng của bạn hiện đang trống.</td>
    </tr>
    <%
    } else {
      for (Map<String, Object> item : cartItems) {
    %>
    <tr>
      <td><%= item.get("ProductName") %></td>
      <td>
        <img src="<%= item.get("ImageURL") %>" alt="<%= item.get("ProductName") %>" style="width: 100px; height: auto;">
      </td>
      <td><%= item.get("Quantity") %></td>
      <td><%= item.get("Price") %> VND</td>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
