$(document).ready(function() {
	
	var numbers;
	
	$.ajax({
		url: "http://localhost:8080/24_cdio_final/rest/admin/userids",
		method: "GET",
		success: function(data) {
			numbers = data;
		},
		error: function(error) {
			console.log(error);
		}
		
	});
	
	
	$("#CMBForm").submit(function(event) {
		
		//Prevent reset on form when an input is not correct...
		event.preventDefault();
		
		if (validateForm() == true) {

			var data = $('#CPForm').serializeObject();

			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/admin/createuser",
				data: JSON.stringify(data),
				contentType: "application/json",
				method: 'POST',
				success: function(resp){
					console.log('This is the Success method')
					console.log(resp)
				},
				error: function(resp){
					console.log('This is the ERROR method')
					console.log(resp)
				}
			});
			//Simple javascript to reset...
			document.getElementById('CMBForm').reset();
			
			// Don't submit the form again
			return false;
		}
	});

//	Invalid input - mark with red border around input prescriptionName field...
	$("#materialBId").keyup(function(){
		var value = $(this).val();
		if(value.length >= 20) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around input nonNetto field...
	$("#materialId").keyup(function(){
		var value = $(this).val();
		if(value.length >= 4) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around input tolerance field...
	$("#amount").keyup(function(){
		var value = $(this).val();
		if(value.length >= 12) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around ID input fields...
	$('input[type="number"]').keyup(function(){
		var value = $(this).val();
		if(value < 1 || value > 99999999) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});


	//Validation of creating user...
	function validateForm() {
		
		// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

		if ($("#prescriptionId").val() < 1 || $("#userId").val() > 99999999) {
			alert("Your prescription id must be between 1 and 99999999.");
			return false;
		}
		else if (validateID()) {
			alert("Prescription id already taken.");
			return false;
		}
		else if ($("#prescriptionName").val().length > 20) {
			alert("The prescription name must be less than 21 letters.");
			return false;
		}
		else if ($("#nonNetto").val().length < 0.05 || $("#nonNetto").val().length > 20.0) {
			alert("The nonNetto must be between 0.05 - 20.0 kg.");
			return false;
		} 
		else if ($("#tolerance").val() < 0.1 || $("#tolerance").val() > 10.0) {
			alert("Your cpr is too long.");
			return false;
		}
		else if ($('#CUForm input:checked').length == 0) { //Something else for several material ids
			alert("You need at least 1 role.");
			return false;
		}
		else {
			if (confirm("Is all the information correct?") == true) {	
				return true;
			}
			else {
				return false;
			}
		}
	}

	//Validation of ID - to make sure a user doesnt already exist in the database with the same ID.
	function validateID() {
		var nr = parseInt($('#prescriptionId').val());
		for(i in numbers){
			if (numbers[i] == nr){
				return true;
			}
		}	
	}

});

