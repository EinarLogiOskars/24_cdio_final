$(document).ready(function() {

	$("#createMB").on("click", function() {
		$("#div2").load("createMB.html");
	});

	$("#showMB").click(function() {
		$("#div2").load("showMB.html");
	});	

	$("#createPB").click(function() {
		$("#div2").load("createPB.html");
	});

	$("#showPB").click(function() {
		$("#div2").load("showPB.html")
	});

	$("#exitbtn").click(function() {
		window.location.assign("../Home/home.html");
	});

//	$("#showusers").on("click", function(){
//	$.ajax({
//	type: 'GET',
//	url: "http://localhost:8080/24_cdio3/rest2/cdio3/showusers",
//	success: function(data) {
//	$("#div2").load("showUsers.html");
//	}, 
//	error: function(){
//	alert("error");
//	}
//	});
//	});
});
