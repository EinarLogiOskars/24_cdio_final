$(document).ready(function() {

	$("#CRForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		if (validateForm() == true) {

			var data = $('#CRForm').serializeObject();

			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/pharmacist/createreceipt",
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
			document.getElementById('CRForm').reset();

			// Don't submit the form again
			return false;
		}
	});

	//Validation of creating user...
	function validateForm() {

		// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

		if ($("#receptNavn").val() < 2 || $("#receptNavn").val() > 20) {
			alert("Receipt name must be more than 2 letters and less than 20 letters.");
			return false;
		}
		else {
			if (confirm("Is all of the information correct?") == true) {	
				return true;
			}
			else {
				return false;
			}
		}
	}
});