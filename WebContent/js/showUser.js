/*			
 * showUser.js
 */

$(document).ready(function() {
	$("#retrieveUserButton").click(function() {
			
		$.ajax({
			url:"rest/functionality/showUser",
			data: $('#showUser_search').serialize(),
			dataType: "json",
			contenttype: "application/x-ww-form-urlencoded",
			method: "POST",
			success:function(data) {
				console.log(data);
				if((document.contains(document.getElementById("showUser_table")))){
					document.getElementById("showUser_table").remove();
					var c, r, t;
					t = document.createElement('table');
					t.setAttribute('id',"showUser_table");
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

					r = t.insertRow(1);
					r.setAttribute('class',"table_content")
					c = r.insertCell(0);
					c.innerHTML = data.user_id;
					c = r.insertCell(1);
					c.innerHTML = data.name;
					c = r.insertCell(2);
					c.innerHTML = data.password;
					c = r.insertCell(3);
					c.innerHTML = data.ini;
					c = r.insertCell(4);
					c.innerHTML = data.cpr;
					c = r.insertCell(5);
					c.innerHTML = data.roles;
					
					document.getElementById("showUserContainer").appendChild(t);
					
				} else{					
					var c, r, t;
					t = document.createElement('table');
					t.setAttribute('id',"showUser_table");
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

					r = t.insertRow(1);
					r.setAttribute('class',"table_content")
					c = r.insertCell(0);
					c.innerHTML = data.user_id;
					c = r.insertCell(1);
					c.innerHTML = data.name;
					c = r.insertCell(2);
					c.innerHTML = data.password;
					c = r.insertCell(3);
					c.innerHTML = data.ini;
					c = r.insertCell(4);
					c.innerHTML = data.cpr;
					c = r.insertCell(5);
					c.innerHTML = data.roles;
					
					document.getElementById("showUserContainer").appendChild(t);
				}
			}
		})
		return false;
	});
});