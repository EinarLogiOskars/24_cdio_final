$(document).ready(function() {
	
	var materials;
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/24_cdio_final/rest/pharmacist/showmats",
		success: function(data) {
			materials = data;
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
				$('<th>').text("Material ID"),
				$('<th>').text("Material Name"),
				$('<th>').text("Supplier")
		).appendTo("#table");
		
		$.each(materials, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.raavareId),
					$('<td>').text(item.raavareNavn),
					$('<td>').text(item.leverandoer)
			).appendTo('#table');
		});
	}
});