function setMinDate() {
   const currentDate = new Date();
   const formattedDate = currentDate.toISOString().slice(0, 16); // yyyy-MM-ddTHH:mm

   const startDateInput = document.getElementById('start_date');
   const endDateInput = document.getElementById('end_date');

   startDateInput.min = formattedDate;

   startDateInput.addEventListener('change', () => {
       const startDate = startDateInput.value;
       endDateInput.min = startDate; // End date can't be before start date
   });
}


function validateForm() {
    const startDateInput = document.getElementById('start_date');
    const endDateInput = document.getElementById('end_date');
    const startDateError = document.getElementById('start_date_error');
    const endDateError = document.getElementById('end_date_error');

    const startDate = new Date(startDateInput.value);
    const endDate = new Date(endDateInput.value);
    const currentDate = new Date();

    let isValid = true;

    // Reset error messages
    startDateError.textContent = '';
    endDateError.textContent = '';

    // Start Date Validation
    if (startDate < currentDate) {
        startDateError.textContent = 'Start date cannot be in the past.';
        isValid = false;
    }

    // End Date Validation
    if (endDate <= startDate) {
        endDateError.textContent = 'End date must be after start date.';
        isValid = false;
    }

    if (isValid) {
       // alert('Survey created successfully!');
        CreateNewSurveyRequest();
    }
}




function optionsAddedChange(){
	const optionsAdded = document.getElementById('optionsAdded');
	const optionsCountDisplay = document.getElementById('optionsCountDisplay');
	optionsCountDisplay.textContent = optionsAdded.value;
	    const optionsContainer = document.getElementById('optionsContainer');
        const numberOfOptions = parseInt(optionsAdded.value);
		optionsContainer.innerHTML = ''; // Clear existing options

		    for (let i = 1; i <= numberOfOptions; i++) {
		        const optionDiv = document.createElement('div');
		        optionDiv.className = 'form-group';
		        optionDiv.innerHTML = `
		            <label for="option${i}"><i class="fas fa-edit"></i> Option ${i}</label>
		            <input type="text" id="option${i}" name="option${i}" placeholder="Enter option ${i}" required>
		        `;
		        optionsContainer.appendChild(optionDiv);
		    }
    }
	
	
	document.addEventListener('DOMContentLoaded', () => {
	    const radioButtons = document.querySelectorAll('input[name="questionType"]');
	    const optionsSection = document.getElementById('optionsSection');
		const optionsContainer=document.getElementById('optionsContainer');

	    // Function to toggle options section
	    function toggleOptionsSection() {
	        const selectedRadio = document.querySelector('input[name="questionType"]:checked');
	        if (selectedRadio && (selectedRadio.value === 'SingleChoice' || selectedRadio.value === 'MultipleChoice')) {
	            optionsSection.style.display = 'block'; // Show options section
	        } else {
	            optionsSection.style.display = 'none'; // Hide options section
				document.getElementById('optionsAdded').value=0;
				optionsAddedChange();
	        }
	    }

	    // Attach event listeners to radio buttons
	    radioButtons.forEach(radio => {
	        radio.addEventListener('change', toggleOptionsSection);
	    });

	    // Set initial visibility on page load
	    toggleOptionsSection();
	});


	
	