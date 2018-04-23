/*			
 * showAllUsers.js
 */

$(document).ready(function() {

	$("#retrieveAllUsersButton").click(function() {
		$.ajax({
			url:"rest/functionality/showAllUsers",
			data: $('#showUser_search').serialize(),
			dataType: "json",
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				console.log(data);

				var c, r, t;
				t = document.createElement('table');
				t.setAttribute('class',"showUser_table");
				r = t.insertRow(0);
				r.setAttribute('class',"table_top")
				c = r.insertCell(0);
				c.innerHTML = "Id";
				c = r.insertCell(1);
				c.innerHTML = "Username";
				c = r.insertCell(2);
				c.innerHTML = "Password";
				c = r.insertCell(3);
				c.innerHTML = "Ini";
				c = r.insertCell(4);
				c.innerHTML = "Cpr";
				c = r.insertCell(5);
				c.innerHTML = "Role";

				for (var i = 0; i < data.length; i++) {
					r = t.insertRow(1);
					r.setAttribute('class',"table_content")
					c = r.insertCell(0);
					c.innerHTML = data.userId[i];
					c = r.insertCell(1);
					c.innerHTML = data.name[i];
					c = r.insertCell(2);
					c.innerHTML = data.password[i];
					c = r.insertCell(3);
					c.innerHTML = data.ini[i];
					c = r.insertCell(4);
					c.innerHTML = data.cpr[i];
					c = r.insertCell(5);
					c.innerHTML = data.roles[i];
				}

				document.getElementById("showAllUsersContainer").appendChild(t);

			}
		})
		return false;
	});

});