/*			
 * showAllUsers.js
 */

$(document).ready(function() {
	
	$("#retrieveAllUsersButton").click(function() {
		
		/*$.ajax({
			url:"rest/functionality/showUser",
			data: $('targetUsername').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				alert(data);
			}
		})
				return false;
				*/
		
		//create a form
		var f = document.createElement("form");
		f.setAttribute('method',"post");

		//create input element
		var i = document.createElement("input");
		i.type = "text";
		i.name = "user_name";
		i.id = "user_name1";

		//create a checkbox
		var c = document.createElement("input");
		c.type = "checkbox";
		c.id = "checkbox1";
		c.name = "check1";

		//create a button
		var s = document.createElement("input");
		s.type = "submit";
		s.value = "Submit";

		// add all elements to the form
		f.appendChild(i);
		f.appendChild(c);
		f.appendChild(s);

		// add the form inside the body
		$("body").append(f);   //using jQuery or
		document.getElementsById('body')[0].appendChild(f); //pure javascript
		var x = document.getElementsById('body');
		x = f;

	});
	
});