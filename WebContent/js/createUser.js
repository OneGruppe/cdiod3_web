/*			
 * createUsers.js
 */

$(document).ready(function() {

	$("#actuallyCreatingAUser").click(function() {		
		$.ajax({
			url:"rest/functionality/createUser",
			data: $('.createUserFunction').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				if(data==="User successfully created"){
					alert(data);
			$(".loadToDiv").load("html/createUser.html");
				}
				else{alert(data);}
			}
		})
		return false;
	});
	
});