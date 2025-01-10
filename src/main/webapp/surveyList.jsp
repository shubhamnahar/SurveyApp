<%@ page import="java.util.List"%>
<%@ page import="com.boot.survey.model.SurveyDetailsTable" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Survey List</title>
    <link rel="stylesheet" href="/css/styles.css">
    <style>
        .survey-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .survey-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            width: 200px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .survey-card a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        .survey-card a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>All Surveys</h1>
    <div class="survey-container">
		<input type="hidden" name="survey" id ="survey" value="${surveys}" />
		<% 
		List<com.boot.survey.model.SurveyDetailsTable> surveys = (List<com.boot.survey.model.SurveyDetailsTable>) request.getAttribute("surveys");
		                if (surveys != null && !surveys.isEmpty()) {
		                    for (com.boot.survey.model.SurveyDetailsTable survey : surveys) {
		            %>
		            <div class="survey-card">
		                <h3><%= survey.getSurvey_name() %></h3>
		                <p><%= survey.getDescription() %></p>
		                <a href="/survey/<%= survey.getSurvey_id() %>/start">Take Survey</a>
						
		            </div>
					<% 
				}
				} %>
		       
    </div>
</body>
</html>
