<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <title>Question Form</title>
	<script src="${pageContext.request.contextPath}/js/Common.js"></script>
	<script src="${pageContext.request.contextPath}/js/Validation.js"></script>
	 <meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<link rel="stylesheet" href="/css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <!--<style>
        
    </style>-->
</head>
<body>

	<div class="container">
	    <form id="questionForm">
	        <h1>&#x1F4CB; Question Form</h1>
	        
	        <!-- CSRF Token -->
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	        <!-- Survey Title -->
	        <div class="form-section">
	            <h3><i class="fas fa-poll"></i> ${SurveyID}. ${SurveyName}</h3>
				<input type="hidden" name="SurveyID" id ="SurveyID" value="${SurveyID}" />
				<input type="hidden" name="SurveyName" id ="SurveyName" value="SurveyName" />
	        </div>

	        <!-- Question Title -->
	        <div class="form-group">
	            <label for="questionTitle">
	                <i class="fa-solid fa-question"></i> Question Title
	                <span class="tooltip" title="Enter the main title of your question.">&#x2139;&#xFE0F;</span>
	            </label>
	            <input type="text" id="questionTitle" name="questionTitle" placeholder="e.g., What is your favorite color?" required>
	        </div>
		
		<div class="form-group">
		    <label><i class="fas fa-cogs"></i>Question Type
				
				<span class="tooltip" title="Select the type of question you want in your Survey.">&#x2139;&#xFE0F;</span>
			</label>
			
		    <div id="questionTypeContainer" class="question-type-container">
				
		        <div class="question-type-item">
		            <input type="radio" id="textType" name="questionType" value="Text" required>
		            <label for="textType" title="Open-ended text input">
		                <i class="fas fa-font"></i> Text (Open-Ended)
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="singleChoice" name="questionType" value="SingleChoice" required>
		            <label for="singleChoice" title="Choose one option from multiple choices">
		                <i class="fas fa-dot-circle"></i> Single Choice
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="multipleChoice" name="questionType" value="MultipleChoice" required>
		            <label for="multipleChoice" title="Choose multiple options from given choices">
		                <i class="fas fa-check-square"></i> Multiple Choice
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="rating" name="questionType" value="Rating" required>
		            <label for="rating" title="Provide a rating (e.g., stars or numeric scale)">
		                <i class="fas fa-star"></i> Rating
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="numeric" name="questionType" value="Numeric" required>
		            <label for="numeric" title="Enter a numeric value">
		                <i class="fas fa-hashtag"></i> Numeric
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="dateTime" name="questionType" value="DateTime" required>
		            <label for="dateTime" title="Pick a date and time">
		                <i class="fas fa-calendar-alt"></i> Date/Time
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="ranking" name="questionType" value="Ranking" required>
		            <label for="ranking" title="Rank items in order of preference">
		                <i class="fas fa-sort"></i> Ranking
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="yesNo" name="questionType" value="YesNo" required>
		            <label for="yesNo" title="Yes or No response">
		                <i class="fas fa-toggle-on"></i> Yes/No
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="fileUpload" name="questionType" value="FileUpload" required>
		            <label for="fileUpload" title="Upload a file or document">
		                <i class="fas fa-upload"></i> File Upload
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="matrix" name="questionType" value="Matrix" required>
		            <label for="matrix" title="Grid-style matrix question">
		                <i class="fas fa-th"></i> Matrix/Grid
		            </label>
		        </div>

		        <div class="question-type-item">
		            <input type="radio" id="dropdown" name="questionType" value="Dropdown" required>
		            <label for="dropdown" title="Select one option from a dropdown menu">
		                <i class="fas fa-caret-down"></i> Dropdown
		            </label>
		        </div>
		    </div>
		</div>


		
		 <!-- Number of Options -->
		        <div class="form-group"  id="optionsSection">
		            <label for="optionsAdded">
		                <i class="fas fa-list-ol"></i> Number of Options
		                <span class="tooltip" title="Select how many options this question will have.">&#x2139;&#xFE0F;</span>
		            </label>
		            <div class="options-select">
		                <input type="range" id="optionsAdded" name="optionsAdded" min="0" max="10" value="0" onchange="optionsAddedChange()">
		                <span id="optionsCountDisplay">0</span>
		            </div>
		        </div>

		        <!-- Dynamic Options -->
		        <div class="form-group options-container" id="optionsContainer">
		            <!-- Dynamic option fields will be added here -->
		        </div>

		        <!-- Mandatory Field -->
		        <div class="form-group">
		            <label for="isRequired">
		                <i class="fas fa-asterisk"></i> Is this Mandatory?
		                <span class="tooltip" title="Specify if this question is mandatory for users.">&#x2139;&#xFE0F;</span>
		            </label>
		            <div class="toggle-container">
		                <input type="checkbox" id="isRequired" name="isRequired">
		                <label for="isRequired" class="toggle-label">
		                    <span>Yes</span>
		                </label>
		            </div>
		        </div>

		        <!-- Created By -->
		        <div class="form-group">
		            <label for="createdBy">
		                <i class="fas fa-user"></i> Created By
		                <span class="tooltip" title="Enter the creator's name.">&#x2139;&#xFE0F;</span>
		            </label>
		            <input type="text" id="createdBy" name="createdBy" placeholder="e.g., John Doe" required>
		        </div>

		        <!-- Submit Button -->
		        <div class="form-actions">
		            <button type="button" onclick="generateQuestionRequest()">
		                <i class="fas fa-plus-circle"></i> Add Question
		            </button>
		        </div>
		    </form>
		</div>

</body>
</html>