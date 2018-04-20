/*			
 * login.js
 */

$(document).ready(function() {
	
	$("#loginButton").click(function() {
		$.ajax({
			url:"rest/functionality/login",
			data: $('#loginForm').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data){
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
	
});