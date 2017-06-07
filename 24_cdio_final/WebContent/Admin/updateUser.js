$(document).ready(function() {

	var numbers;

	//	Gets all userId's from the database.
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


	$("#UButton").click(function() {	
		if(validateID()){
			$("#UUDiv").load("updateUserForm.html");
			var userId = parseInt($('#userIDChosen').val());
			var user;
			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/admin/" + userId,
				method: "GET",
				data: user,
				contentType: "application/json",
				success: function(data) {
					user = data;
					$("#userId").val(user.userId);
					$("#userName").val(user.userName);
					$("#ini").val(user.ini);
					$("#password").val(user.password);
					$("#cpr").val(user.cpr);
					$('#UUForm').submit(function(event){
						
						console.log("Start of update user submit")
						//Prevent reset on form when an input is not correct...
						event.preventDefault();
						
						$("#userId").removeAttr('disabled');
						
						
						if (validateForm() == true) {
							
							
							
							var data = $('#UUForm').serializeObject();
							
							
							$.ajax({
								url: "http://localhost:8080/24_cdio_final/rest/admin/updateuser",
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
							document.getElementById('UUForm').reset();

							// Don't submit the form again
							return false;
						}
					});
				},
				error: function(error) {
					alert("Error: " + error);
				}

			});			
		}

//		$('#UUForm').submit(function(event){
//		console.log("Start of update user submit")
//		//Prevent reset on form when an input is not correct...
//		event.preventDefault();

//		if (validateForm() == true) {

//		var data = $('#UUForm').serializeObject();

//		console.log(data);

//		$.ajax({
//		url: "http://localhost:8080/24_cdio3/rest2/cdio3/updateuser",
//		data: JSON.stringify(data),
//		contentType: "application/json",
//		method: 'PUT',
//		success: function(resp){
//		console.log('This is the Success method')
//		console.log(resp)
//		},
//		error: function(resp){
//		console.log('This is the ERROR method')
//		console.log(resp)
//		}
//		});
//		//Simple javascript to reset...
//		document.getElementById('UUDiv').empty();

//		// Don't submit the form again
//		return false;
//		}
//		});

		//Invalid input - mark with red border around ID input field...
		$('input[type="number"]').keyup(function(){
			var value = $(this).val();
			if(value < 11 || value > 89) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

//		Invalid input - mark with red border around input userName field...
		$("#userName").keyup(function(){
			var value = $(this).val();
			if(value.length >= 20) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

//		Invalid input - mark with red border around input ini field...
		$("#ini").keyup(function(){
			var value = $(this).val();
			if(value.length >= 4) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

//		Invalid input - mark with red border around input cpr field...
		$("#cpr").keyup(function(){
			var value = $(this).val();
			if(value.length >= 12) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});


		//Validation of creating user...
		function validateForm() {

			// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

			if ($("#userName").val().length > 20) {
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
			else if ($('#UUForm input:checked').length == 0) {
				alert("You need at least 1 role.");
				return false;
			}
			else {
				if (confirm("Are all the information correct?") == true) {	
					return true;
				}
				else {
					return false;
				}
			}
		}


		// To validate whether the chosen id to delete exists.
		function validateID() {
			var nr = parseInt($('#userIDChosen').val());
			for(i in numbers){
				if (numbers[i] == nr){
					return true;
				}
			}	
			alert("No user has that user ID...");
			return false;
		}

	});
});