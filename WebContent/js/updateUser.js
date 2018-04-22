/*			
 * updateUser.js
 */

$(document).ready(function() {

	/*$("#findUserToUpdate").click(function() {

		$.ajax({
			url : "rest/functionality/showUser",
			data : $('#targetUsername').serialize(),
			contenttype : "application/x-ww-form-urlencoded",
			method : "GET",
			success : function(data) {

			}
		})
		return false;
	});*/

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
		})
		return false;
	});
});