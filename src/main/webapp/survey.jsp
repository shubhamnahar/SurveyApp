<%@ page import="java.util.List"%>
<%@ page import="com.boot.survey.model.QuestionTable" %>
<%@ page import="com.boot.survey.model.Options" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Survey List</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="container">
        <h1><i class="fas fa-poll"></i> Survey Questions</h1>
        <form method="post" action="/submitSurvey">
            <% for (QuestionTable question : (List<QuestionTable>) request.getAttribute("questions")) { %>
                <div class="form-group">
                    <h3><i class="fas fa-question-circle"></i> <%= question.getQuestion_title() %></h3>
                    <% if ("Text".equals(question.getQuestion_type())) { %>
                        <input type="text" name="q_<%= question.getQuestion_id() %>" <%= question.isIs_required() ? "required" : "" %>/>
                    <% } else if ("SingleChoice".equals(question.getQuestion_type())) { %>
                        <% for (Options option : question.getOptions()) { %>
                            <label>
                                <input type="radio" name="q_<%= question.getQuestion_id() %>" value="<%= option.getOption_id() %>"/> <%= option.getOptions_title() %>
                            </label><br/>
                        <% } %>
                    <% } else if ("MultipleChoice".equals(question.getQuestion_type())) { %>
                        <% for (Options option : question.getOptions()) { %>
                            <label>
                                <input type="checkbox" name="q_<%= question.getQuestion_id() %>" value="<%= option.getOption_id() %>"/>  <%= option.getOptions_title() %>
                            </label><br/>
                        <% } %>
                    <% } else if ("Rating".equals(question.getQuestion_type())) { %>
                        <input type="number" name="q_<%= question.getQuestion_id() %>" min="1" max="5" <%= question.isIs_required() ? "required" : "" %>/>
                    <% } else if ("Numeric".equals(question.getQuestion_type())) { %>
                        <input type="number" name="q_<%= question.getQuestion_id() %>" <%= question.isIs_required() ? "required" : "" %>/>
                    <% } else if ("DateTime".equals(question.getQuestion_type())) { %>
                        <input type="datetime-local" name="q_<%= question.getQuestion_id() %>" <%= question.isIs_required() ? "required" : "" %>/>
                    <% } else if ("Ranking".equals(question.getQuestion_type())) { %>
                        <select name="q_<%= question.getQuestion_id() %>">
                            <% for (Options option : question.getOptions()) { %>
                                <option value="<%= option.getOption_id() %>"><%= option.getOptions_title() %></option>
                            <% } %>
                        </select>
                    <% } else if ("YesNo".equals(question.getQuestion_type())) { %>
                        <label><input type="radio" name="q_<%= question.getQuestion_id() %>" value="Yes"/> Yes</label>
                        <label><input type="radio" name="q_<%= question.getQuestion_id() %>" value="No"/> No</label>
                    <% } else if ("Dropdown".equals(question.getQuestion_type())) { %>
                        <select name="q_<%= question.getQuestion_id() %>">
                            <% for (Options option : question.getOptions()) { %>
                                <option value="<%= option.getOption_id() %>"><%= option.getOptions_title() %></option>
                            <% } %>
                        </select>
                    <% } %>
                </div>
            <% } %>
            <div class="form-actions">
                <button type="submit"><i class="fas fa-paper-plane"></i> Submit Survey</button>
            </div>
        </form>
    </div>
</body>
</html>