$(document).ready(function() {
	
	var users;
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/24_cdio_final/rest/admin/showusers",
		success: function(data) {
			users = data;
			console.log("I am the load users success method.");
			loadtable();
		},
		error: function(error) {
			console.log("I am the load users ERROR!");
			console.log(error);
		}
	});

	function loadtable(){
		
		$('<tr>').append(
				$('<th>').text("User ID"),
				$('<th>').text("Name"),
				$('<th>').text("Initials"),
				$('<th>').text("Password"),
				$('<th>').text("CPR-number"),
				$('<th>').text("Roles")
		).appendTo("#table");
		
		$.each(users, function(i, item) {
			$('<tr>').append(
					$('<td>').text(item.userId),
					$('<td>').text(item.userName),
					$('<td>').text(item.ini),
					$('<td>').text(item.password),
					$('<td>').text(item.cpr),
					$('<td>').text(item.roles)
			).appendTo('#table');
		});
	}
});