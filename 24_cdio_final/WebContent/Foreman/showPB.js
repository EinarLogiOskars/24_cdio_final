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
				$('<th>').text("Receipt ID"),
				$('<th>').text("status")
		).appendTo("#table");
		
		$.each(users, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.pbId),
					$('<td>').text(item.status),
					$('<td>').text(item.receptId)
			).appendTo('#table');
		});
	}
});