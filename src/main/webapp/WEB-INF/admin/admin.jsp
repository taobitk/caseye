<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            background-color: #fff8e1;
        }

        header {
            background: linear-gradient(to right, #ff5722, #ffc107);
            color: white;
        }

        .logo {
            font-size: 1.8rem;
            font-weight: bold;
        }

        .nav-link {
            color: white !important;
        }

        .nav-link:hover {
            color: #ffe082 !important;
        }

        .dropdown-menu {
            background-color: #ff5722;
            border: none;
        }

        .dropdown-item {
            color: white;
        }

        .dropdown-item:hover {
            background-color: #e64a19;
        }

        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
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

        .admin-content {
            min-height: 70vh;
        }
    </style>
</head>
<body>
<header class="py-3">
    <div class="container d-flex justify-content-between align-items-center">
        <div class="logo">Admin Dashboard</div>
        <nav>
            <ul class="nav">
                <li class="nav-item"><a href="../../index.jsp" class="nav-link">Trang Chủ</a></li>
                <li class="nav-item"><a href="login" class="nav-link">Đăng Xuất</a></li>
            </ul>
        </nav>
    </div>
</header>
<section class="admin-content py-5">
    <div class="container">
        <h2 class="text-center">Chào mừng, Quản trị viên!</h2>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Quản lý người dùng</h5>
                        <p class="card-text">Thêm, sửa, xóa người dùng.</p>
                        <a href="customers" class="btn btn-light">Quản lý</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Quản lý sản phẩm</h5>
                        <p class="card-text">Thêm, sửa, xóa sản phẩm.</p>
                        <a href="products" class="btn btn-light">Quản lý</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-danger mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Xem báo cáo</h5>
                        <p class="card-text">Xem doanh thu, đơn hàng.</p>
                        <a href="reports" class="btn btn-light">Xem</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer class="py-3 text-center">
    <p>&copy; 2024 Admin Panel. All rights reserved.</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>