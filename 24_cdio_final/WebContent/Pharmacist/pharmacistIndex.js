$(document).ready(function() {
	
	$("#createMaterial").on("click", function() {
		$("#div2").load("createMaterial.html");
	});

	$("#updateMaterial").click(function() {
		$("#div2").load("updateMaterial.html");
	});	

	$("#showMaterials").click(function() {
		$("#div2").load("showMaterials.html");
	});
	
	$("#createPrescription").click(function() {
		$("#div2").load("createPrescription.html")
	});
	
	$("#showPrescriptions").click(function() {
		$("#div2").load("showPrescriptions.html");
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
