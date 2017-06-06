$(document).ready(function() {
	
	
	$("#loginForm").submit(function(event) {
		//Prevent form from reseting on incorrect login credentials
		event.preventDefault();
		
		var data = $("#loginForm").serializeObject();
		
		
		$.ajax({
			url: "http://localhost:8080/24_cdio_final/rest/login/logincheck",
			data: JSON.stringify(data),
			contentType: "application/json",
			method: 'POST',
			success: function(resp){
				console.log('This is the Success method')
				console.log(resp)
				if(resp === "false") {
					$("#invld").css("visibility", "visible");
				}
				else {
					$("#invld").css("visibility", "hidden");
					window.location.assign("NewPage.html");
				}
			},
			error: function(resp){
				console.log('This is the ERROR method')
				console.log(resp)
			}
		})
		
		// Don't submit the form again
		return false;
		
	});
	
	
});
