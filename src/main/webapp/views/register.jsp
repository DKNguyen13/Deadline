<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f3f3;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .main {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 300px;
        }

        .main h2 {
            color: #4caf50;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="file"],
        select {
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button[type="submit"] {
            padding: 15px;
            border-radius: 10px;
            border: none;
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="main">
        <h2>Registration Form</h2>
        <c:if test="${not empty alert}">
                    <div class="alert">${alert}</div>
                    <c:set var="alert" value="${null}" />
                </c:if>
        <form action="/register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />

            <label for="password">Password:</label>
            <input type="password" id="password" name="passWord"
                   title="Password must contain at least 3 number and be at least 3 characters long" required />

            <label for="repassword">Re-type Password:</label>
            <input type="password" id="repassword" name="repassword" required />

            <label for="mobile">Phone:</label>
            <input type="text" id="mobile" name="phone" maxlength="10" required />

             <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
