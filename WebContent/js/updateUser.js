/*			
 * updateUser.js
 */

$(document).ready(function() {
	
	$("#findUserToUpdate").click(function() {
		
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