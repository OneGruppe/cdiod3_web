/*			
 * showUser.js
 */

$(document).ready(function() {
	
	$("#retrieveUserButton").click(function() {
		/*
		$.ajax({
			url:"rest/functionality/showUser",
			data: $('#targetUsername').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert(data);
			}
		})
		alert("An error occured")
		return false;
		*/
		
		$.ajax({
		    type: "GET",
		    url: "rest/functionality/getjson",
		    data: $('#targetUsername').serialize(),
		    dataType: "json", // Set the data type so jQuery can parse it for you
		    success: function (data) {
		        document.getElementById("loadToDiv").innerHTML.getElementById("name").innerHTML = data[0];
		        document.getElementById("loadToDiv").innerHTML.getElementById("age").innerHTML = data[1];
		        document.getElementById("loadToDiv").innerHTML.getElementById("location").innerHTML = data[2];
		    }
		})
		return false;
		
	});
});