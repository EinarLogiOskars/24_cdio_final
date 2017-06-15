$(document).ready(function() {

	var numbers;

	//	Gets all materialId's from the database.
	$.ajax({
		url: "http://localhost:8080/24_cdio_final/rest/pharmacist/matids",
		method: "GET",
		success: function(data) {
			console.log("Get matIDs success function");
			numbers = data;
		},
		error: function(error) {
			console.log(error);
		}

	});


	$("#MButton").click(function() {	
		if(validateID()){
			$("#UMDiv").load("updateMaterialForm.html");
			var matId = parseInt($('#materialIDChosen').val());
			var mat;
			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/pharmacist/" + matId,
				method: "GET",
				data: mat,
				contentType: "application/json",
				success: function(data) {
					mat = data;
					console.log(mat);
					$("#materialId").val(mat.raavareId);
					$("#materialName").val(mat.raavareNavn);
					$("#supplier").val(mat.leverandoer);
					$('#UMForm').submit(function(event){
						console.log("Start of update material submit")
						//Prevent reset on form when an input is not correct...
						event.preventDefault();
						
						$("#materialId").removeAttr('disabled');
						
						if (validateForm() == true) {
							
							var data = $('#UMForm').serializeObject();
					
							$.ajax({
								url: "http://localhost:8080/24_cdio_final/rest/pharmacist/updatemat",
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
							document.getElementById('UMForm').reset();

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

		//Invalid input - mark with red border around ID input field...
		$('input[type="number"]').keyup(function(){
			var value = $(this).val();
			if(value < 1 || value > 99999999) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

//		Invalid input - mark with red border around input materialName field...
		$("#materialName").keyup(function(){
			var value = $(this).val();
			if(value.length >= 20) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

//		Invalid input - mark with red border around input supplier field...
		$("#supplier").keyup(function(){
			var value = $(this).val();
			if(value.length >= 20) {
				$(this).attr('class', 'w3-border w3-border-red w3-input');
			}
			else {
				$(this).attr('class', 'w3-input');
			}
		});

		//Validation of updating material...
		function validateForm() {

			// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

			if ($("#materialName").val().length > 20) {
				alert("The material name must be less than 21 letters.");
				return false;
			}
			else if ($("#supplier").val().length > 20) {
				alert("The supplier of the material must less than 21 letters.");
				return false;
			} 
			else {
				if (confirm("Is the information correct?") == true) {	
					return true;
				}
				else {
					return false;
				}
			}
		}


		// To validate whether the chosen id to delete exists.
		function validateID() {
			var nr = parseInt($('#materialIDChosen').val());
			for(i in numbers){
				if (numbers[i] == nr){
					return true;
				}
			}	
			alert("No material has that ID...");
			return false;
		}

	});
});