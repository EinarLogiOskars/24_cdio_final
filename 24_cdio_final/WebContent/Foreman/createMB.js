$(document).ready(function() {

	$("#CMBForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();


		var data = $('#CMBForm').serializeObject();
		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/pharmacist/" + data.raavareId,
			method: 'GET',
			success: function(resp) {
				console.log("success in validating receipt component...")
				console.log(resp)
				if (resp.raavareId == 0) {
					alert("You picked a non-existing material id.");					
				}
				else {
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
				}
			},
			error: function() {
				console.log("validation error method");
			}
		});
		//Simple javascript to reset...
		document.getElementById('CMBForm').reset();

		// Don't submit the form again
		return false;

	});

});

