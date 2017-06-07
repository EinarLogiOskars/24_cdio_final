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
				$('<th>').text("Material ID"),
				$('<th>').text("Material Name"),
				$('<th>').text("Supplier")
		).appendTo("#table");
		
		$.each(materials, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.materialId),
					$('<td>').text(item.materialName),
					$('<td>').text(item.supplier)
			).appendTo('#table');
		});
	}
});