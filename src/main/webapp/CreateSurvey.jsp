<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>New Survey</title>
    <script src="${pageContext.request.contextPath}/js/Common.js"></script>
	<script src="${pageContext.request.contextPath}/js/Validation.js"></script>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
	<link rel="stylesheet" href="/css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

	<!--
    <style>
        /* General Body Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        /* Container for the form */
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Header Styling */
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Form Group Styling */
        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
        }

        .form-group input[type="text"],
        .form-group textarea,
        .form-group input[type="datetime-local"],
        .form-group select {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .form-group textarea {
            resize: vertical;
            height: 100px;
        }

        .form-group button {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: bold;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }

        .form-group button:hover {
            background-color: #45a049;
        }
		.error {
		           color: red;
		           font-size: 12px;
		           margin-top: 5px;
		       }
    </style>-->
</head>
<body onload="setMinDate()">
    <div class="container">
		<form id="survey-form">
        <h1>New Survey Application</h1>
        
            <div class="form-group" id="user-id-container">
                <label for="survey_name">Survey Name:
					<span class="tooltip" title="Enter the Name of your Survey.">&#x2139;&#xFE0F;</span>
				</label>
                <input type="text" id="survey_name" name="survey_name" placeholder="e.g., Product Survey 2024-25" required>
            </div>

            <div class="form-group">
                <label for="description">Description:
					<span class="tooltip" title="Give some Description for the user to understand the purpose of Survey .">&#x2139;&#xFE0F;</span>
				</label>
                <textarea id="description" name="description" placeholder="e.g., Review for all the Product launch by XYZ company in 2024-25" required></textarea>
            </div>

            <div class="form-group">
                <label for="created_by">Created By:
					<span class="tooltip" title="Enter the Name of Creator of this Survey.">&#x2139;&#xFE0F;</span>
				</label>
                <input type="text" id="created_by" name="created_by" placeholder="e.g., John Tucker" required>
            </div>

            <div class="form-group">
                <label for="start_date">Start Date:
					<span class="tooltip" title="Enter the date from when you what to conduct the survey.">&#x2139;&#xFE0F;</span>
				</label>
                <input type="datetime-local" id="start_date" name="start_date" required>
				<div class="error" id="start_date_error"></div>
            </div>

            <div class="form-group">
                <label for="end_date">End Date:
					<span class="tooltip" title="Enter the date till when you what to conduct the survey">&#x2139;&#xFE0F;</span>
				</label>
                <input type="datetime-local" id="end_date" name="end_date" required>
				<div class="error" id="end_date_error"></div>
            </div>

            <div class="form-group">
                <button type="button" onclick="validateForm()">Create Survey</button>
            </div>
        </form>
    </div>
</body>
</html>
