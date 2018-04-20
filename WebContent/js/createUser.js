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
				alert(data);
			$(".loadToDiv").load("createUser.html");
			}
		})

	});
	
});