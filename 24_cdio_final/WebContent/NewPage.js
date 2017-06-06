$(document).ready(function() {
	console.log("this is the NewPage javascript");
	
	var name = sessionStorage.getItem("username");
	$("#welcomeh1").append(name);
	
	
});
