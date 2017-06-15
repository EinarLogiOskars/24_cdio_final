$(document).ready(function() {
	
	$("#PBForm").submit(function(event) {
		
		//Prevent reset on form when an input is not correct...
		event.preventDefault();
		
		if (validateForm() == true) {

			var data = $('#PBForm').serializeObject();
			console.log(data.receptId);
			
			$.ajax({
				url: "http://localhost:8080/24_cdio_final/rest/pharmacist/receipt/" + data.receptId,
				method: 'GET',
				success: function(resp) {
					console.log(resp);
					if (resp.receptId != 0) {
						$.ajax({
							url: "http://localhost:8080/24_cdio_final/rest/foreman/createproductbatch",
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
					else {
						alert("You have picked a non-existing receipt id for this product batch. Pick a existing receipt to proceed.");
					}
				},
				error: function(resp) {
					console.log("Error method...")
					console.log(resp)
				}
			})
			
			//Simple javascript to reset...
			document.getElementById('PBForm').reset();
			
			// Don't submit the form again
			return false;
		}
	});

	function validateForm() {
		if ($("#pbID").val == 0) {
			return false;
		}
		else if ($("#receiptId").val == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
});

