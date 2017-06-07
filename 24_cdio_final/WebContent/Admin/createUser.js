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
	
	
	$("#CUForm").submit(function(event) {
		
		//Prevent reset on form when an input is not correct...
		event.preventDefault();
		
		if (validateForm() == true) {

			var data = $('#CUForm').serializeObject();

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
			document.getElementById('CUForm').reset();
			
			// Don't submit the form again
			return false;
		}
	});

//	Invalid input - mark with red border around input userName field...
	$("#userName").keyup(function(){
		var value = $(this).val();
		if(value.length >= 20) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around input ini field...
	$("#ini").keyup(function(){
		var value = $(this).val();
		if(value.length >= 4) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around input cpr field...
	$("#cpr").keyup(function(){
		var value = $(this).val();
		if(value.length >= 12) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});

//	Invalid input - mark with red border around ID input field...
	$('input[type="number"]').keyup(function(){
		var value = $(this).val();
		if(value < 11 || value > 89) {
			$(this).attr('class', 'w3-border w3-border-red w3-input');
		}
		else {
			$(this).attr('class', 'w3-input');
		}
	});


	//Validation of creating user...
	function validateForm() {
		
		// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

		if ($("#userId").val() < 11 || $("#userId").val() > 89) {
			alert("Your user id must be between 11 and 89.");
			return false;
		}
		else if (validateID()) {
			alert("User id already taken.");
			return false;
		}
		else if ($("#userName").val().length > 20) {
			alert("Your user name must be less than 20 letters.");
			return false;
		}
		else if ($("#ini").val().length > 3) {
			alert("Your initials must be of maximum three letters.");
			return false;
		} 
		else if ($("#cpr").val().length > 11) {
			alert("Your cpr is too long.");
			return false;
		}
		else if ($('#CUForm input:checked').length == 0) {
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
		var nr = parseInt($('#userId').val());
		for(i in numbers){
			if (numbers[i] == nr){
				return true;
			}
		}	
	}

});

