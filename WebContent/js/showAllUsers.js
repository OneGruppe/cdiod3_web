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
		
		var c, r, t;
	    t = document.createElement('table');
	    t.setAttribute('class',"showAllUsers_table");
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
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    r = t.insertRow(1);
	    r.setAttribute('class',"table_content")
	    c = r.insertCell(0);
	    c.innerHTML = "Test";
	    c = r.insertCell(1);
	    c.innerHTML = "Test";
	    c = r.insertCell(2);
	    c.innerHTML = "Test";
	    c = r.insertCell(3);
	    c.innerHTML = "Test";
	    c = r.insertCell(4);
	    c.innerHTML = "Test";
	    c = r.insertCell(5);
	    c.innerHTML = "Test";
	    
	    document.getElementById("showAllUsersContainer").appendChild(t);
	});
	
});