$(document).ready(function() {
	
	var users;
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/24_cdio_final/rest/foreman/showproductbatches",
		success: function(data) {
			users = data;
			console.log("I am the load material batch success method.");
			loadtable();
		},
		error: function(error) {
			console.log("I am the load material batch error method!");
			console.log(error);
		}
	});

	function loadtable(){
		
		$('<tr>').append(
				$('<th>').text("Product batch ID"),
				$('<th>').text("Prescription ID"),
				$('<th>').text("status"),
				$('<th>').text("User ID"),
				$('<th>').text("Tara weight"),
				$('<th>').text("Netto weight")
		).appendTo("#table");
		
		$.each(users, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.pBId),
					$('<td>').text(item.prescriptionId),
					$('<td>').text(item.status),
					$('<td>').text(item.userId),
					$('<td>').text(item.materialBId),
					$('<td>').text(item.taraWeight),
					$('<td>').text(item.nettoWeight)
			).appendTo('#table');
		});
	}
});