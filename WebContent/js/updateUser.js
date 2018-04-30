/*			
 * updateUser.js
 */

$(document).ready(function() {

	$("#findUserToUpdate").click(function() {
		
		$.ajax({
			url:"rest/functionality/showUser",
			data: $('.updateUserContainer').serialize(),
			dataType: "json",
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				
				$("#setUsername").attr("value", data.name);
				$("#setPassword").attr("value", data.password);
				$("#setIni").attr("value", data.ini);
				
			}	
		});	
		return false;
	});


	$("#actuallyUpdatingAUser").click(function() {

		$.ajax({
			url : "rest/functionality/updateUser",
			data : $('.updateUserContainer').serialize(),
			contenttype : "application/x-ww-form-urlencoded",
			method : "POST",
			success : function(data) {
				alert(data);
				$(".loadToDiv").load("html/updateUser.html");
			}
		});
		return false;
	});
});