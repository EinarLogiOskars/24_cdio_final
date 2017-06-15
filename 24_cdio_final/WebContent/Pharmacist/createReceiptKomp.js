$(document).ready(function() {

	// creates the receipt komponent
	$("#RKForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		var data = $('#RKForm').serializeObject();
		var id = data.receptId;


		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/pharmacist/createreceiptkomp",
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
			},
		});
		if(confirm("Do you want to add a receipt komponent?") == true) {
			receiptKomponent(id);
		}
		//Simple javascript to reset...
		document.getElementById('RKForm').reset();


		// Don't submit the form again
		return false;


	});

	//Create receipt komponent function
	function receiptKomponent(name) {
		$("#div2").load("createReceiptKomp.html");
		$("#receiptId").val(id);
	}
});