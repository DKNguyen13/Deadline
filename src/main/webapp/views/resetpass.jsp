<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Reset Password</title>
  <style>
    .alert {
        background-color: #f44336;
        color: white;
        padding: 15px;
        margin-bottom: 20px;
        border-radius: 5px;
    }
  </style>
</head>
<body>
<div class="main">
  <h2>Forgot Password</h2>

  <c:set var="message" value="${not empty sessionScope.message ? sessionScope.message : requestScope.message}" />

  <c:if test="${not empty message}">
    <p class="alert">${message}</p> <!-- Sử dụng thẻ <p> thay vì <div> -->
    <c:set var="message" value="${null}" /> <!-- Xóa thông báo sau khi hiển thị -->
  </c:if>

  <form action="/forgot-password" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
    <button type="submit">Reset Password</button>
    <br>
        <a href="/views/login.jsp">login</a>

  </form>
</div>
</body>
</html>
