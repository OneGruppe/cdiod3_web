/*			
 * showAllUsers.js
 */

$(document).ready(function() {

		$.ajax({
			url:"rest/functionality/showAllUsers",
			data: $('#showUser_search').serialize(),
			dataType: "json",
			contenttype: "application/x-ww-form-urlencoded",
			method: "GET",
			success:function(data) {

				if((document.contains(document.getElementById("showUser_table")))){
					document.getElementById("showUser_table").remove();
					var c, r, t;
					t = document.createElement('table');
					t.setAttribute('id',"showUser_table");
					r = t.insertRow(0);
					r.setAttribute('class',"table_top");
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

					for (i = 0; i < data[0].length; i++) {
						r = t.insertRow(1);
						r.setAttribute('class',"table_content");
						c = r.insertCell(0);
						c.innerHTML = data[0][i].user_id;
						c = r.insertCell(1);
						c.innerHTML = data[0][i].userName;
						c = r.insertCell(2);
						c.innerHTML = data[0][i].password;
						c = r.insertCell(3);
						c.innerHTML = data[0][i].ini;
						c = r.insertCell(4);
						c.innerHTML = data[0][i].cpr;
						c = r.insertCell(5);
						c.innerHTML = data[0][i].roles;
					}

					document.getElementById("showAllUsersContainer").appendChild(t);

				} else{

						var c, r, t;
						t = document.createElement('table');
						t.setAttribute('id',"showUser_table");
						r = t.insertRow(0);
						r.setAttribute('class',"table_top");
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

						for (i = 0; i < data[0].length; i++) {
							r = t.insertRow(1);
							r.setAttribute('class',"table_content");
							c = r.insertCell(0);
							c.innerHTML = data[0][i].user_id;
							c = r.insertCell(1);
							c.innerHTML = data[0][i].userName;
							c = r.insertCell(2);
							c.innerHTML = data[0][i].password;
							c = r.insertCell(3);
							c.innerHTML = data[0][i].ini;
							c = r.insertCell(4);
							c.innerHTML = data[0][i].cpr;
							c = r.insertCell(5);
							c.innerHTML = data[0][i].roles;
						}
						document.getElementById("showAllUsersContainer").appendChild(t);
					}
				}
			})
			return false;

	});