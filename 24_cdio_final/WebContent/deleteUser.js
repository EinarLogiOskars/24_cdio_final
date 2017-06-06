$(document).ready(function() {

	var numbers;

	// Gets all userId's from the database.
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

	$("#DUForm").submit(function(event) {

		//Prevent reset on form when an input is not correct.
		event.preventDefault();

		if (validateID() == true) {

			var userId = parseInt($('#delUserID').val());
			console.log(userId);
			debugger;
			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/admin/delete/" + userId,
				method: "DELETE",
				success: function(resp) {
					console.log("This is the delete user success method.");
					console.log(resp);
				},
				error: function(resp) {
					console.log("This is the delete user error method.");
					console.log(resp);
				}
			});
			//Simple javascript to reset...
			document.getElementById('DUForm').reset();

			// Don't submit the form again
			return false;

		}

	});


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

	// To validate whether the chosen id to delete exists.
	function validateID() {
		var nr = parseInt($('#delUserID').val());
		for(i in numbers){
			if (numbers[i] == nr){
				if (confirm("Are you sure you want to delete user: " + nr) == true){
					return true;
				}
			}
		}	
		alert("No user has that user ID...");
		return false;
	}

});