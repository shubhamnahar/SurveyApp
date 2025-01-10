/**
 * 
 */


function do_Ajax(Req_Type, url, data = null) {
	const xhr = new XMLHttpRequest();

	var retval = '-1';
	xhr.open(Req_Type, url);

	if (Req_Type === 'POST' ) { //&& data
		//var csrfToken = document.querySelector('meta[name="_csrf"]').content;
		//var csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
		xhr.setRequestHeader('Content-Type', 'application/json');
		//xhr.setRequestHeader(csrfHeader, csrfToken); // Set the CSRF token header
	}
	xhr.onreadystatechange = function() {
		if (url.includes("createUser")) {
			CreateUserReponse(xhr.responseText,xhr);
		}
		if (url.includes("createSurvey")) {
			CreateSurveyReponse(xhr.responseText,xhr);
		}
		if (xhr.readyState === 4 && xhr.status === 200) {
			//console.log(xhr.responseText);
			parseMessages();
		}
		if (xhr.readyState === 4 && xhr.status === 201) {
			//console.log(xhr.responseText);
			if (url.includes("create")) {
				//CreateQuizReponse(xhr.responseText);
			}
		}
	}

	//xhr.send();
	// Send the request body if provided, otherwise send null
	xhr.send(data ? JSON.stringify(data) : null);

	function parseMessages() {

		retval = trimBefore(xhr.responseText);
		//alert("parseMessages=" + retval);
		if (url.includes("AllQuestions") || url.includes("category")) {
			GetQuestions(retval);
		} else if (url.includes("categoriesValues")) {
			LoadingCategories(retval);
		}else if (url.includes("getQuiz")) {
			LoadQuizQuestion(retval);
		}
		else if (url.includes("sumbit")) {
		LoadResultpage(retval);
		}

	}

//	console.log('retval = ' + retval);

	//return retval;
}

function trimBefore(str) {
	var i = 0;
	var j = 0;
	for (i = 0; i < str.length; i++) {
		if (str.charAt(i) != '\r' && str.charAt(i) != '\n' && str.charAt(i) != ' ')
			break;
	}

	for (j = str.length - 1; j >= 0; j--) {
		if (str.charAt(j) != '\r' && str.charAt(j) != '\n' && str.charAt(j) != ' ')
			break;
	}

	if (j < i) j = i - 1;
	str = str.substring(i, j + 1);
	return str;
}


function GetCategories() {
	do_Ajax('GET', '/question/categoriesValues');
}

function fetchQuestions() {
	var Category = document.getElementById("SelectCategory").value;

	if (Category == "") {
		alert("First Select Category");
		const container = document.getElementById('quiz-container');
		container.innerHTML = "";
		return false;

	} else if (Category == "All") {
		do_Ajax('GET', '/question/AllQuestions');
	}
	else {
		var url = "/question/category/" + Category;
		do_Ajax('GET', url);
	}
}




function GetQuestions(retval) {

	var questions = JSON.parse(retval);


	const container = document.getElementById('quiz-container');
	container.innerHTML = "";
	var i = 1;
	questions.forEach(question => {
		const questionDiv = document.createElement('div');
		questionDiv.classList.add('question');

		const title = document.createElement('h3');
		title.textContent = i + ") " + question.questionTitle;
		questionDiv.appendChild(title);

		const brk = document.createElement('br');
		const CategoryDiv = document.createElement('div');
		const Category = document.createElement('label');
		Category.innerHTML = '<strong>Category: </strong>';
		Category.innerHTML = Category.innerHTML + question.category;
		//Category.style.marginRight = '10px'; // Add some space between label and input
		CategoryDiv.appendChild(Category);
		questionDiv.appendChild(CategoryDiv);

		const DifficultDiv = document.createElement('div');
		const Difficulty_Level = document.createElement('label');
		Difficulty_Level.innerHTML = '<strong>Difficulty Level: </strong>';
		Difficulty_Level.innerHTML = Difficulty_Level.innerHTML + question.difficultyLevel;
		DifficultDiv.appendChild(Difficulty_Level);
		questionDiv.appendChild(DifficultDiv);

		questionDiv.appendChild(brk);

		const options = ['option1', 'option2', 'option3', 'option4'];
		var j = 1;
		options.forEach(option => {

			const optionDiv = document.createElement('div');
			const input = document.createElement('label');
			input.type = 'label';
			input.name = `question${question.id}`;
			input.value = question[option];
			input.id = `question${question.id}_${option}`;
			input.textContent = j + ") " + question[option];



			optionDiv.appendChild(input);
			//optionDiv.appendChild(label);
			questionDiv.appendChild(optionDiv);
			j++;
		});

		const Right_Ans = document.createElement('label');
		Right_Ans.innerHTML = '<strong>Right Answer: </strong>';
		Right_Ans.innerHTML = Right_Ans.innerHTML + question.rightAnswer;
		questionDiv.appendChild(brk);
		questionDiv.appendChild(Right_Ans);

		container.appendChild(questionDiv);
		i++;



	});



}

function LoadingCategories(retval) {

	var categories = JSON.parse(retval);
	categories.push("All", "");
	categories = categories.sort();

	const Category = document.createElement('label');
	Category.innerHTML = '<strong>Category: </strong>';
	const container = document.getElementById('drop-container');
	var categoryDrop = document.createElement('select');
	categoryDrop.id = "SelectCategory";
	categoryDrop.name = "SelectCategory";
	categories.forEach(category => {
		var options = document.createElement('option');
		options.value = category;
		options.textContent = category;
		categoryDrop.appendChild(options);
	});
	container.appendChild(Category);
	container.appendChild(categoryDrop);

}
//alert(retval);
/*
function ChangingData(retval) {

	var questions = JSON.parse(retval);
	const container = document.getElementById('quiz-container');
	questions.forEach(question => {
		const questionDiv = document.createElement('div');
		questionDiv.classList.add('question');

		const title = document.createElement('h3');
		title.textContent = question.questionTitle;
		questionDiv.appendChild(title);

		const options = ['option1', 'option2', 'option3', 'option4'];
		options.forEach(option => {
			const optionDiv = document.createElement('div');
			const input = document.createElement('input');
			input.type = 'radio';
			input.name = `question${question.id}`;
			input.value = question[option];
			input.id = `question${question.id}_${option}`;

			const label = document.createElement('label');
			label.htmlFor = input.id;
			label.textContent = question[option];

			optionDiv.appendChild(input);
			optionDiv.appendChild(label);
			questionDiv.appendChild(optionDiv);
		});

		container.appendChild(questionDiv);
	});
	//document.getElementsByName("question1")[0].checked
	//document.getElementsByName("question1")[0].value

}*/









function CreateQuiz() {
	var url = "/quiz/create?";
	var RequestType='POST';
	
	var category=document.getElementById('SelectCategory').value;
	var noOfQues= document.getElementById('numberOfQuestions').value;
	var title=document.getElementById('title').value;
   //http://localhost:8080/quiz/create?category=python&noOfQues=5&title=PyQuiz
   url=url+"category="+category+"&noOfQues="+noOfQues+"&title="+title;
    do_Ajax(RequestType,url,"");


}



function getQuiz() {
        var quizId = document.getElementById('quizId').value;
        
        var url="quiz/getQuiz/"+quizId;
        
        do_Ajax("GET",url,"");
        
        
        
        
    }

    
    function LoadQuizQuestion(retval){
		
		var quizData =JSON.parse(retval);
		// Hide the quiz ID input and button
        document.getElementById('quiz-id-container').style.display = 'none';
        
        // Show the quiz questions
        var quizContainer = document.getElementById('quiz-container');
        quizContainer.style.display = 'block';
       // var count=1;
        quizData.forEach(function(question, index) {
            var questionDiv = document.createElement('div');
            questionDiv.classList.add('quiz-question');
            
            var questionTitleField = document.createElement('h2');
            questionTitleField.textContent = index+1 + ") " + question.questionTitle;
            questionDiv.appendChild(questionTitleField);
            
            var questionId=document.createElement('input');
            questionId.type='hidden';
            questionId.id=`question_${index}`;
            questionId.value=`${question.id}`;
            questionDiv.appendChild(questionId);
            //document.getElementById(questionId.id).style.display = 'none';
            
          const options = ['option1', 'option2', 'option3', 'option4'];
            
           // options.forEach(function(option) {
			options.forEach(option => {
                var optionLabel = document.createElement('label');
                var optionInput = document.createElement('input');
                optionInput.type = 'radio';
                optionInput.name = 'question_' + index;
                optionInput.value =  question[option];
				optionInput.id = `question_${index}_${option}`;
                optionLabel.appendChild(optionInput);
                optionLabel.appendChild(document.createTextNode(optionInput.value));
                questionDiv.appendChild(optionLabel);
            });


            
			/*const options = ['option1', 'option2', 'option3', 'option4'];
			options.forEach(option => {
				const optionDiv = document.createElement('div');
				const label = document.createElement('label');
				const input = document.createElement('input');
				input.type = 'radio';
				input.name = `question${question.id}`;
				input.value = question[option];
				input.id = `question${question.id}_${option}`;

			//	const label = document.createElement('label');
				label.htmlFor = input.id;
				label.textContent = question[option];

				label.appendChild(input);
				label.appendChild(document.createTextNode(option));
				optionDiv.appendChild(input);
				optionDiv.appendChild(label);
				//questionDiv.appendChild(optionDiv);
				questionDiv.appendChild(label);
			});
*/
            
            quizContainer.appendChild(questionDiv);
            //count++;
        });
        
        var submitButton = document.createElement('button');
        submitButton.classList.add('quiz-submit-button');
        submitButton.textContent = 'Submit';
        submitButton.addEventListener('click', submitQuiz); 
        quizContainer.appendChild(submitButton);
		
		
		
	}
	
	function submitQuiz(){
		//alert('hello');
		var len=document.getElementsByClassName('quiz-question').length;
		var formdata=[]; 
		for(var i=0;i<len;i++){
			var question_id=document.getElementById("question_"+i).value;
			
			for(var j=1;j<5;j++){
				var radio_btn=document.getElementById("question_"+i+"_option"+j);   //question_0_option1
				
				if(radio_btn.checked){
					//response[question_id]=radio_btn.value;
					formdata.push({"id":question_id,"response":radio_btn.value});
				}
			} 
			
		}
		//console.log(formdata);
		var quiz_id=document.getElementById("quizId").value;
		var url="quiz/sumbit/"+quiz_id;
		do_Ajax('POST', url, formdata);
	}
	
	function LoadResultpage(retval){
		url="/Result?ID="+retval;
		window.location.replace(url);
	}
	//--------------------------------------------------User Start---------------------------------------------
	function CreateNewUserRequest(){
		
				
		const form = document.querySelector('#user-form');
	
		const UserJson = {
		    userName: form.username.value,
		    user_psw: form.password.value,
		    firstName: form.firstname.value,
		    lastName: form.lastname.value,
		    email: form.email.value,
		    roles: form.role.value,
			createdby:form.createdBy.value
		};
				
		
		var url="users/createUser";
		
		do_Ajax('POST',url,UserJson);
	}
	
	function CreateUserReponse(response,xhr){
		if(xhr.status===201 && xhr.readyState==4){
		alert("User Created Successfully");
		}else if (xhr.readyState===4){
			alert(response);
		}
		//window.location.href='/editQuestions';
	}
	
	function CreateNewSurveyRequest(){
		
		const form = document.querySelector('#survey-form');
			
				const SurveyJson = {
				    survey_name: form.survey_name.value,
				    description: form.description.value,
				    usertable: {userName:form.created_by.value},
				    StartDate: form.start_date.value,
				    EndDate: form.end_date.value
				};
				
				var url="survey/createSurvey";
				
				do_Ajax('POST',url,SurveyJson);
	}
	
	function CreateSurveyReponse(response,xhr){
			if(xhr.status===200 && xhr.readyState==4){
				var output= JSON.parse(response);
				alert("Survey Created Successfully with ID:"+output.survey_id);
				window.location.href="/editQuestions?SurveyID="+output.survey_id+"&SurveyName="+output.survey_Name;
			}else if (xhr.readyState===4){
				alert("Error Occured");
			}
			//window.location.href='/editQuestions';
		}
		



function generateQuestionRequest() {
	var url = '/addQuestion';
	//const selectedRadio = document.querySelector('input[name="questionType"]:checked');
    const form = document.getElementById('questionForm');
    const questionTitle = form.querySelector('input[name="questionTitle"]').value;
    const questionType = form.querySelector('input[name="questionType"]:checked').value;
    const isRequired = form.querySelector('input[name="isRequired"]').checked;
    const surveyId = form.querySelector('input[name="SurveyID"]').value;
    const createdBy = form.querySelector('input[name="createdBy"]').value;
    
    // Handle options if they exist
	const options = [];
	    const optionInputs = document.querySelectorAll('input[id^="option"]'); // Match IDs starting with 'option'
		optionInputs.forEach((option) => {
	        const optionValue = option.value.trim();
	        if (optionValue !== '' && option.id !== 'optionsAdded') {
	            options.push({ optionsTitle: optionValue });
	        }
	    });

    // Build the JSON object dynamically
    const questionRequest = {
        questionTitle: questionTitle,
        questionType: questionType,
        isRequired: isRequired,
        surveyId: surveyId,
        createdBy: createdBy,
    };

    if (options.length > 0 && (questionType === 'SingleChoice' || questionType === 'MultipleChoice' || questionType === 'Dropdown')) {
        questionRequest.options = options;
    }

    //console.log('Generated JSON:', JSON.stringify(questionRequest, null, 2));
	do_Ajax('POST', url, questionRequest);
    //return questionRequest;
}

function CreateQuestionReponse(response,xhr){
		if(xhr.status===201 && xhr.readyState==4){
			alert("Question Added Successfully");
			form.querySelector('input[name="questionTitle"]').value="";
			form.querySelector('input[name="questionType"]:checked').checked=false
			form.querySelector('input[name="isRequired"]').checked=false;
			
			document.getElementById('optionsSection').style.display = 'none';
			document.getElementById('optionsAdded').value=0;
			optionsAddedChange();
		}else if (xhr.readyState===4){
			alert(response);
		}
		//window.location.href='/editQuestions';
	}