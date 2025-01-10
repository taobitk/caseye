<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Combined Reports</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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
  <h1 class="text-center">Combined Reports</h1>

  <!-- Combined Table -->
  <table class="table table-bordered table-striped mt-4">
    <thead class="table-dark">
    <tr>
      <th>Customer ID</th>
      <th>Order Date</th>
      <th>Total Amount</th>
      <th>Status</th>
      <th>Product ID</th>
      <th>Quantity</th>
      <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="row" items="${mergedData}">
      <tr>
        <td>${row.CustomerId}</td>
        <td>${row.OrderDate}</td>
        <td>${row.TotalAmount}</td>
        <td>${row.OrderStatus}</td>
        <td>${row.ProductId}</td>
        <td>${row.Quantity}</td>
        <td>${row.Price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
