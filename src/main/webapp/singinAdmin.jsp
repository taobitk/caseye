<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #fef7e0; /* Nền màu cam nhạt */
        }
        .btn-orange {
            background-color: #f57c00; /* Màu cam */
            color: #fff;
        }
        .btn-orange:hover {
            background-color: #e65100; /* Màu cam đậm hơn khi hover */
        }
        .form-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
            color: #f57c00;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h3 class="text-center text-orange">Đăng nhập</h3>
        <form action="login" method="post">
            <div class="mb-3">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tên đăng nhập" required>
            </div>
            <div class="mb-3">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
            </div>
            <button type="submit" class="btn btn-orange w-100">Đăng nhập</button>

            <%
                String mess = (String) request.getAttribute("message");
                if (mess != null) {
            %>
            <p class="text-danger text-center mt-2"><%= mess %></p>
            <%
                }
            %>
        </form>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
