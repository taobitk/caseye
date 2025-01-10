<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }

    .login-container {
      max-width: 400px;
      margin: 50px auto;
      background: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }

    .login-container h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #ff5722;
    }

    .form-label {
      font-weight: bold;
      color: #343a40;
    }

    .form-control {
      border-radius: 8px;
    }

    .btn-custom {
      background-color: #ff5722;
      color: white;
      border: none;
      width: 100%;
      padding: 10px;
      border-radius: 8px;
      font-size: 16px;
    }

    .btn-custom:hover {
      background-color: #e64a19;
    }

    .register-link {
      text-align: center;
      display: block;
      margin-top: 15px;
      color: #007bff;
      text-decoration: none;
    }

    .register-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="login-container">
    <h2>Customer Login</h2>
    <form action="customerLogin" method="post">
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input type="email" id="email" name="email" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input type="password" id="password" name="password" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-custom">Login</button>
    </form>
    <a href="register.jsp" class="register-link">Register here</a> if you don't have an account.
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
