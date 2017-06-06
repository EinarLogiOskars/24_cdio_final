$(document).ready(function() {
	console.log("this is the NewPage javascript");
	
	var name = sessionStorage.getItem("username");
	$("#welcomeh1").append(name);
	var roles;
	$.ajax({
		
		url: "http://localhost:8080/24_cdio_final/rest/login/" + name,
		method: 'GET',
		success: function(resp){
			console.log('This is the Success method')
			roles = resp
			console.log(resp)
			roles = rights(roles);
			$("#welcomeh1").append(", and you have " + roles + " privileges.");
		},
		error: function(resp){
			console.log('This is the ERROR method')
			console.log(resp)
		}
	});
	
	function rights(roles) {
		if (roles.indexOf("Admin") != -1) {
			return roles = "Admin";
		}
		else if (roles.indexOf("Pharmacist") != -1) {
			return roles = "Pharmacist";
		}
		else if (roles.indexOf("Foreman") != -1) {
			return roles = "Foreman";
		}
		else if (roles.indexOf("Operator") != -1) {
			return roles = "Operator";
		}
		else {
			return roles = "Error... no roles";
		}
	}
	
});
