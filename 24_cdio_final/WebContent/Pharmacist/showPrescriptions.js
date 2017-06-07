$(document).ready(function() {
	
	var users;
	
	$.ajax({
		type: "GET",
		url: "some url",
		success: function(data) {
			users = data;
			console.log("I am the load materials success method.");
			loadtable();
		},
		error: function(error) {
			console.log("I am the load materials error method!");
			console.log(error);
		}
	});

	function loadtable(){
		
		$('<tr>').append(
				$('<th>').text("Prescription ID"),
				$('<th>').text("Prescription Name"),
				$('<th>').text("Material IDs contained"),
				$('<th>').text("nonNetto"),
				$('<th>').text("Tolerance")
		).appendTo("#table");
		
		$.each(prescriptions, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.prescriptionId),
					$('<td>').text(item.prescriptionName),
					$('<td>').text(item.prescriptionMaterials),
					$('<td>').text(item.nonNetto),
					$('<td>').text(item.tolerance)
			).appendTo('#table');
		});
	}
});