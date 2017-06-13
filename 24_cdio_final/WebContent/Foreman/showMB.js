$(document).ready(function() {
	
	var materialbatches;
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/24_cdio_final/rest/foreman/showmaterialbatches",
		success: function(data) {
			materialbatches = data;
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
				$('<th>').text("Material Batch ID"),
				$('<th>').text("Material ID"),
				$('<th>').text("Amount")
		).appendTo("#table");
		
		$.each(materialbatches, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.materialBId),
					$('<td>').text(item.materialId),
					$('<td>').text(item.amount)
			).appendTo('#table');
		});
	}
});