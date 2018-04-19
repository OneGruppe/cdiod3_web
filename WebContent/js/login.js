$(document).ready(function() {
	$("#loginButton").click(function() {
		$.ajax({
			url:"rest/functionality/login",
			data: $('#loginForm').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data){
				//alert(data);
				if(data == "true") {
					$(".login").hide();
					$(".loadToDiv").load("adminlogin.html");					
				}
				else{
					alert("Password doesn't match username, try again");
				}
			}
		})
		return false;
	});

	$("#createUserButton").click(function() {
		$(".loadToDiv").load("createUser.html");
	});

	$("#showUserButton").click(function() {
		$(".loadToDiv").load("showUsers.html");
	})

	$("#logoutButton").click(function() {
		window.location.reload();    		
	})

	$("#frontPage").click(function() {
		$(".loadToDiv").load("adminlogin.html"); 		
	})

	$("#actuallyCreatingAUser").click(function() {		
		$.ajax({
			url:"rest/functionality/createUser",
			data: $('.createUserFunction').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert("Du har nu sendt information afsted")
			$(".loadToDiv").load("createUser.html");
			}
		})

	});

});