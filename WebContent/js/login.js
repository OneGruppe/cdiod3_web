$(document).ready(function() {
    $("#createUserButton").click(function() {
        load("createUser.html");
    });
    
    $("#showUserButton").click(function() {
        alert("show User test DING");
    })
    
    $("#logoutButton").click(function() {
        alert("Tilbage til login side");		        		
    })
});

function newUser(username, password, ini, CPR, role) {
    this.username = username;
    this.password = password;
    this.ini = ini;
    this.CPR = CPR;
    this.role = role;
}