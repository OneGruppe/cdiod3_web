/*			
 * deleteUser.js
 */

$(document).ready(function() {
	
	$("#deleteUser").click(function() {
		
		$.ajax({
			url:"rest/functionality/deleteUser",
			data: $('#targetUsername').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert(data);
			$(".loadToDiv").load("html/deleteUser.html");
			}
		});
				return false;
	});
});