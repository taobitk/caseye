<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="repository.ProductRepository" %>

<%
    // Lấy danh sách sản phẩm từ cơ sở dữ liệu
    List<Product> products = ProductRepository.getAllProducts();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NamHùngStore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        header {
            background: linear-gradient(to right, #ff5722, #ffc107);
            color: white;
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .logo {
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            margin: 0 auto;
        }

        .nav-link {
            color: white !important;
        }

        .nav-link:hover {
            color: #ffe082 !important;
        }

        .card {
            border: none;
            position: relative;
            overflow: hidden;
            border-radius: 10px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
        }

        .card img {
            height: 200px;
            object-fit: cover;
        }

        footer {
            background: #e64a19;
            color: white;
        }

        footer p {
            margin: 0;
        }

        .btn-dark {
            background-color: #ff5722;
            border: none;
        }

        .btn-dark:hover {
            background-color: #d84315;
        }
    </style>
</head>
<body>
<header class="py-3">
    <div class="container d-flex justify-content-between align-items-center">
        <a href="index.jsp" class="logo text-decoration-none text-white">Nam Hùng Store</a>
        <nav>
            <ul class="nav">
                <li class="nav-item"><a href="customerLogin" class="nav-link">Tài khoản</a></li>
                <li class="nav-item"><a href="cart.jsp" class="nav-link">Giỏ hàng</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- Product Cards -->
<div class="container mt-5">
    <h2 class="text-center mb-4">Danh sách sản phẩm</h2>
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <% if (products == null || products.isEmpty()) { %>
        <p class="text-center">Không có sản phẩm nào để hiển thị.</p>
        <% } else {
            for (Product product : products) { %>
        <div class="col">
            <div class="card shadow-sm">
                <img src="<%= product.getImageUrl() %>" class="card-img-top" alt="<%= product.getName() %>">
                <div class="card-body text-center">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text">Giá: <%= product.getPrice() %> VND</p>
                </div>
            </div>
        </div>
        <% } } %>
    </div>
</div>

<footer class="py-3 text-center">
    <p>&copy; 2025 NamHùng. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
