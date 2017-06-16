$(document).ready(function() {

	var pbId;
	var receiptId;
	var data;
	var data2;

	$("#PBForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		if (validateForm() == true) {

			data = $('#PBForm').serializeObject();			
			pbId = data.pbId;
			receiptId = data.receptId;
			sessionStorage.setItem("pbId", pbId);

			console.log("pbId is = " + data.pbId);
			console.log("receiptId is = " + data.receptId);

			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/pharmacist/receipt/" + data.receptId,
				method: 'GET',
				success: function(resp) {
					console.log(resp.receptId);
					if (resp.receptId != 0) {
						if (pbExists()) {
							console.log("This is the result i want!")
							$.ajax({
								url: "http://localhost:8080/24_cdio_final/rest/foreman/createproductbatch",
								data: JSON.stringify(data),
								contentType: "application/json",
								method: 'POST',
								success: function(resp){
									console.log('This is the Success method')
									console.log(resp)
									console.log(sessionStorage.getItem("pbId"));
									sessionStorage.setItem("pbId", data.pbId);
									console.log(sessionStorage.getItem("pbId"));
									if(confirm("Do you want to add a product batch component?") == true) {
										pbKomponent();
									}
									else {
										location.reload();
									}
								},
								error: function(resp){
									console.log('This is the ERROR method')
									console.log(resp)
								}
							});
						}
						else {
							alert("The productbatch id is already taken...");
						}
					}
					else {
						alert("You have picked a non-existing receipt id for this product batch. Pick a existing receipt to proceed.");
					}
				},
				error: function(resp) {
					console.log("Error method...")
					console.log(resp)
				}
			});

			//Simple javascript to reset...
			//document.getElementById('PBForm').reset();

			// Don't submit the form again
			return false;
		}
	});

	function validateForm() {
		if ($("#pbId").val == 0) {
			return false;
		}
		else if ($("#receiptId").val == 0) {
			return false;
		}
		else {
			return true;
		}
	}

//	Checks if productbatch with the chosen id is not already taken...
	function pbExists() {
		var theId;
		var blabla;
		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/foreman/productbatchID/" + sessionStorage.getItem("pbId"),
			type: 'GET',
			async: false,
			success: function(resp) {
				console.log("before true/false");
				console.log("pbId object is: " + resp.pbId);
				theId = resp.pbId;
				if (theId != 0) {
					console.log("this is false");
					blabla = false;
				}
				else {
					console.log("this is true");
					blabla = true;
				}
			},
			error: function(resp) {
				console.log("Error in ajax call for productbatch id...");
			}
		});
		alert("Checking if productbatch id is avaliable...")
		console.log(Boolean(blabla))
		return blabla;
	}

	$("#PBKForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();
		
		$("#pbId").val(sessionStorage.getItem("id"));
		data2 = $('#PBKForm').serializeObject();
		console.log("pbId: " + data2.pbId);
			
		
		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/foreman/materialbatchID/" + data2.rbId,
			method: 'GET',
			success: function(resp) {
				console.log("success in validating receipt component...")
				console.log(resp)
				if (resp.rbId == 0) {
					alert("You picked a non-existing material id.");					
				}
				else {
					//Sending productbatch component... 
					$.ajax({
						url: "http://localhost:8080/24_cdio_final/rest/foreman/pbkomp",
						data: JSON.stringify(data2),
						contentType: "application/json",
						method: 'POST',
						success: function(resp){
							console.log('This is the Success method of pbkomp')
							console.log(resp)
						},
						error: function(resp){
							console.log('This is the ERROR method of pbkomp')
							console.log(resp)
						},
					});
					if(confirm("Do you want to add another productbatch component?") == true) {
						pbKomponent();	
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
	
//	Creating productbatch component
	function pbKomponent() {
		$("#div2").load("createPBKomp.html");

		$.ajax({
			type: "GET",
			url: "http://localhost:8080/24_cdio_final/rest/foreman/productbatchID/" + sessionStorage.getItem("pbId"),
			success: function(resp) {
				console.log(resp);
				console.log(resp.pbId);
				sessionStorage.setItem("id", resp.pbId);
				//$("#pbId").val(sessionStorage.getItem("pbId"));
				$("#pbId").val(sessionStorage.getItem("id"));
				console.log("I am the load receipt id success method.");
			},
			error: function(error) {
				console.log("I am the load receipt id error method!");
				console.log(error);
			}
		});
	}
});




