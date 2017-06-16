$(document).ready(function() {

	$("#pbId").val(sessionStorage.getItem("pbId"));
	
	// creates the receipt komponent
	$("#PBKButtonForm").submit(function(event) {

		//Prevent reset on form when an input is not correct...
		event.preventDefault();

		var data = $('#PBKForm').serializeObject();
		var id = data.receptId;


		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/foreman/createproductbatchkomp",
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
		if(confirm("Do you want to add a product batch component?") == true) {
			productBatchComponent(id);
		}
		//Simple javascript to reset...
		document.getElementById('PBKForm').reset();


		// Don't submit the form again
		return false;


	});

	//Create receipt komponent function
	function productBatchComponent(name) {
		//$("#div2").load("createPBKomp.html");
		$("#pbId").val(id);
	}
});