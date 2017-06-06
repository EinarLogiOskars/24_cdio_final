$(document).ready(function() {
	console.log("this is the home page javascript");
	
	var name = sessionStorage.getItem("username");
	$("#welcomeh1").append(name);
	var roles;
	$.ajax({
		
		url: "http://localhost:8080/24_cdio_final/rest/login/" + name,
		method: 'GET',
		success: function(resp){
			console.log('This is the Success method')
			roles = resp
			rights(roles);
			console.log(resp)
		},
		error: function(resp){
			console.log('This is the ERROR method')
			console.log(resp)
		}
	});
	
	function rights(roles) {
		if (roles.indexOf("Admin") != -1) {
			$("#admin").css("visibility", "visible")
			$("#Pharmacist").css("visibility", "visible")
			$("#Foreman").css("visibility", "visible")
		}
		else if (roles.indexOf("Pharmacist") != -1) {
			$("#Pharmacist").css("visibility", "visible")
			$("#Foreman").css("visibility", "visible")
		}
		else if (roles.indexOf("Foreman") != -1) {
			$("#Foreman").css("visibility", "visible")
		}
	
	}
	
	$("#adminbtn").click(function() {
		window.location.assign("adminIndex.html");
	});
	
});