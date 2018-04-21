/*			
 * showUser.js
 */

$(document).ready(function() {
	
	$("#retrieveUserButton").click(function() {
		$.ajax({
			url:"rest/functionality/showUser",
			data: $('#showUser_search').serialize(),
			contenttype: "multipart/form-data",
			method: "POST",
			success:function(resultData) {
				var x = []
				x = resultData;
				for (i = 0 ; i <= 2 ; i++){
					var retString = x[i];
				alert(retString);
				}
			}
		})
		return false;
	});
});