$(document).ready(function() {
	
	var receipts;
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/24_cdio_final/rest/pharmacist/showreceipts",
		success: function(data) {
			receipts = data;
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
				$('<th>').text("Receipt ID"),
				$('<th>').text("Receipt Name")
//				$('<th>').text("Material IDs contained"),
//				$('<th>').text("nonNetto"),
//				$('<th>').text("Tolerance")
		).appendTo("#table");
		
		$.each(receipts, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.receptId),
					$('<td>').text(item.receptNavn)
//					$('<td>').text(item.receptMaterials)
//					$('<td>').text(item.nonNetto),
//					$('<td>').text(item.tolerance)
			).appendTo('#table');
		});
	}
});