$(document).ready(function() {

	var name;
	// creates the receipt
	$("#CRForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		if (validateForm() == true) {

			var data = $('#CRForm').serializeObject();
			name = data.receptNavn;
			sessionStorage.setItem("receiptName", name);


			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/pharmacist/createreceipt",
				data: JSON.stringify(data),
				contentType: "application/json",
				method: 'POST',
				success: function(resp){
					console.log('This is the Success method')
					console.log(resp)
					if(confirm("Do you want to add a receipt komponent?") == true) {
						receiptKomponent();
					}
					else {
						location.reload();
					}
				},
				error: function(resp){
					console.log('This is the ERROR method')
					console.log(resp)
				},
			});


			// Don't submit the form again
			return false;
		}
	});


	// creates the receipt komponent
	$("#RKForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		var data2 = $('#RKForm').serializeObject();
		
		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/pharmacist/" + data2.raavareId,
			method: 'GET',
			success: function(resp) {
				console.log("success in validating receipt component...")
				console.log(resp)
				if (resp.raavareId == 0) {
					alert("You picked a non-existing material id.");					
				}
				else {
					//Sending receipt component... 
					$.ajax({
						url: "http://localhost:8080/24_cdio_final/rest/pharmacist/createreceiptkomp",
						data: JSON.stringify(data2),
						contentType: "application/json",
						method: 'POST',
						success: function(resp){
							console.log('This is the Success method')
							console.log(resp)
						},
						error: function(resp){
							console.log('This is the ERROR method')
							console.log(resp)
						},
					});
					if(confirm("Do you want to add a receipt komponent?") == true) {
						receiptKomponent();	
					}
					else {
						location.reload();
					}
				}
			},
			error: function() {
				console.log("validation error method");
			}
		});

		
		//Simple javascript to reset...
		//document.getElementById('RKForm').reset();


		// Don't submit the form again
		return false;


	});

	//Validation of creating user...
	function validateForm() {

		// http://stackoverflow.com/questions/2684434/jquery-check-if-at-least-one-checkbox-is-checked  <---- for checking checkboxes!

		if ($("#receptNavn").val() < 2 || $("#receptNavn").val() > 20) {
			alert("Receipt name must be more than 2 letters and less than 20 letters.");
			return false;
		}
		else {
			if (confirm("Is the receipt name correct?") == true) {	
				return true;
			}
			else {
				return false;
			}
		}
	}

	//Create receipt komponent function
	function receiptKomponent() {
		$("#div2").load("createReceiptKomp.html");
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/24_cdio_final/rest/pharmacist/receiptid/" + sessionStorage.getItem("receiptName"),
			success: function(resp) {
				console.log(resp);
				sessionStorage.setItem("id", resp);
				$("#receiptId").val(sessionStorage.getItem("id"));
				console.log("I am the load receipt id success method.");
			},
			error: function(error) {
				console.log("I am the load receipt id error method!");
				console.log(error);
			}
		});

	}


});