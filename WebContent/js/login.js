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
	});

	$("#logoutButton").click(function() {
		window.location.reload();    		
	});
	
	$("#updateUserBotton").click(function() {
		$(".loadToDiv").load("updateUser.html"); 		
	});

	$("#frontPage").click(function() {
		$(".loadToDiv").load("adminlogin.html"); 		
	});

	$("#actuallyCreatingAUser").click(function() {		
		$.ajax({
			url:"rest/functionality/createUser",
			data: $('.createUserFunction').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert(data);
			$(".loadToDiv").load("createUser.html");
			}
		})

	});
	
	$("#findUser").click(function() {
		$.ajax({
			url:"rest/functionality/showUser",
			data: $('#targetUsername').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert(data);
			}
		})
		return false;
	});
	
});