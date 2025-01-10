<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">ADMIN</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="customers">customer</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products">product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reports">reports</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center">Customer List</h1>

    <!-- Add Customer Button -->
    <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addCustomerModal">Thêm khách hàng</button>

    <table class="table table-bordered table-striped mt-4">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Address</th>
            <th>Gender</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.name}</td>
                <td>${customer.phone}</td>
                <td>${customer.email}</td>
                <td>${customer.address}</td>
                <td>${customer.gender}</td>
                <td>
                    <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editCustomerModal${customer.customerId}">Sửa</button>
                    <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteCustomerModal${customer.customerId}">Xóa</button>
                </td>
            </tr>

            <!-- Edit Customer Modal -->
            <div class="modal fade" id="editCustomerModal${customer.customerId}" tabindex="-1" aria-labelledby="editCustomerModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editCustomerModalLabel">Sửa khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="editCustomer" method="post">
                            <div class="modal-body">
                                <input type="hidden" name="id" value="${customer.customerId}">
                                <div class="mb-3">
                                    <label for="name${customer.customerId}" class="form-label">Name</label>
                                    <input type="text" class="form-control" id="name${customer.customerId}" name="name" value="${customer.name}">
                                </div>
                                <div class="mb-3">
                                    <label for="phone${customer.customerId}" class="form-label">Phone</label>
                                    <input type="text" class="form-control" id="phone${customer.customerId}" name="phone" value="${customer.phone}">
                                </div>
                                <div class="mb-3">
                                    <label for="email${customer.customerId}" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email${customer.customerId}" name="email" value="${customer.email}">
                                </div>
                                <div class="mb-3">
                                    <label for="address${customer.customerId}" class="form-label">Address</label>
                                    <textarea class="form-control" id="address${customer.customerId}" name="address">${customer.address}</textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="gender${customer.customerId}" class="form-label">Gender</label>
                                    <select class="form-control" id="gender${customer.customerId}" name="gender">
                                        <option value="Male" ${customer.gender == 'Male' ? 'selected' : ''}>Male</option>
                                        <option value="Female" ${customer.gender == 'Female' ? 'selected' : ''}>Female</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="password${customer.customerId}" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="password${customer.customerId}" name="password" value="${customer.password}">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-warning">Lưu thay đổi</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Delete Customer Modal -->
            <div class="modal fade" id="deleteCustomerModal${customer.customerId}" tabindex="-1" aria-labelledby="deleteCustomerModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteCustomerModalLabel">Xóa khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="deleteCustomer" method="post">
                            <div class="modal-body">
                                <input type="hidden" name="id" value="${customer.customerId}">
                                <p>Bạn có chắc chắn muốn xóa khách hàng <strong>${customer.name}</strong> không?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <button type="submit" class="btn btn-danger">Xóa</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Add Customer Modal -->
<div class="modal fade" id="addCustomerModal" tabindex="-1" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCustomerModalLabel">Thêm khách hàng mới</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="addCustomer" method="post">
                <div class="modal-body">
                    <!-- Hiển thị thông báo lỗi -->
                    <c:if test="${not empty addCustomerError}">
                        <div class="alert alert-danger">
                                ${addCustomerError}
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${param.name}">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${param.phone}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${param.email}">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <textarea class="form-control" id="address" name="address">${param.address}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="gender" class="form-label">Gender</label>
                        <select class="form-control" id="gender" name="gender">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Thêm khách hàng</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Auto-open modal nếu có lỗi -->
<c:if test="${not empty addCustomerError}">
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var addCustomerModal = new bootstrap.Modal(document.getElementById("addCustomerModal"));
            addCustomerModal.show();
        });
    </script>
</c:if>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
