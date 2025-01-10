<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
    }
    h1 {
        text-align: center;
        color: #333;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        font-weight: bold;
    }
    .form-group input[type="text"], .form-group button, .form-group input[type="password"] ,.form-group select{
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .form-group button {
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }
    .form-group button:hover {
        background-color: #45a049;
    }
</style>

</head>
<body>

<div class="container">
    <h1>New User Application</h1>
	<form id="user-form">
    <div class="form-group" id="user-id-container">
        <label for="username">Enter User Name:</label>
        <input type="text" id="username" name="username" required>
		<br><br>
        <label for="password">Enter Password:</label>
        <input type="password" id="password" name="password" required>
		<br><br>
		<label for="firstname">Enter First Name:</label>
        <input type="text" id="firstname" name="firstname" required>
		<br><br>
		<label for="lastname">Enter Last Name:</label>
        <input type="text" id="lastname" name="lastname" required>
		<br><br>
		<label for="email">Enter Email ID:</label>
        <input type="text" id="email" name="email" required>
		<br><br>
        <label for="role">Enter Roles:</label>
        <select id="role" name="role" required>
			<option value='User'>User</option>
			<option value='Admin'>Admin</option>
		</select>
		<br>
		<br>
		<label for="createdBy">Created By User Name:</label>
        <input type="text" id="createdBy" name="createdBy" required>
		<br><br>
		<br>
        <button onclick="CreateNewUserRequest()">Create User</button>
    </div>
	</form>

</body>
</html>