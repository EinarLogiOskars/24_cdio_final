$(document).ready(function() {
	
	var numbers;
	
	
	$("#CMBForm").submit(function(event) {
		
		//Prevent reset on form when an input is not correct...
		event.preventDefault();
		
		if (validateForm() == true) {

			var data = $('#CMBForm').serializeObject();

			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/foreman/creatematerialbatch",
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

});

