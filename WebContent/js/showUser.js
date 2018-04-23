/*			
 * showUser.js
 */

$(document).ready(function() {
	$("#retrieveUserButton").click(function() {
		
		var fruits = ["Banana", "Orange", "Apple", "Mango", "Lemon", "Kiwi"];
			$jQuery.ajax({
			url:"rest/functionality/showUser",
			data: $('#showUser_search').serialize(),
			contenttype: "application/x-ww-form-urlencoded",
			method: "GET",
			success:function(data) {
				alert(data);
			}
		})
		return false;
		
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
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = fruits[0];
	    c = r.insertCell(1);
	    c.innerHTML = fruits[1];
	    c = r.insertCell(2);
	    c.innerHTML = fruits[2];
	    c = r.insertCell(3);
	    c.innerHTML = fruits[3];
	    c = r.insertCell(4);
	    c.innerHTML = fruits[4];
	    c = r.insertCell(5);
	    c.innerHTML = fruits[5];
	    
	    document.getElementById("showUserContainer").appendChild(t);
	    
	//	}
	//	})
	});
});