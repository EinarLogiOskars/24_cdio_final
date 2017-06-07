$(document).ready(function() {
	
	$("#createuser").on("click", function() {
		$("#div2").load("createUser.html");
	});

	$("#updateuser").click(function() {
		$("#div2").load("updateUser.html");
	});	

	$("#deleteuser").click(function() {
		$("#div2").load("deleteUser.html");
	});
	
	$("#showusers").click(function() {
		$("#div2").load("showUsers.html");
	});
	
	$("#exitbtn").click(function() {
		window.location.assign("../Home/home.html");
	});

//	$("#showusers").on("click", function(){
//		$.ajax({
//			type: 'GET',
//			url: "http://localhost:8080/24_cdio3/rest2/cdio3/showusers",
//			success: function(data) {
//				$("#div2").load("showUsers.html");
//			}, 
//			error: function(){
//				alert("error");
//			}
//		});
//	});
});
