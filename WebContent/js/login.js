$(document).ready(function() {
	$("#loginButton").click(function() {
		$(".login").hide();
		$(".loadToDiv").load("adminlogin.html");
	});
	
    $("#createUserButton").click(function() {
        load("createUser.html");
    });

    //Det Mathias brugte 4 timer på :(
    $("#adminButtons").click(function() {
        $("createUserButton").load("../createUser.html");
        // $("showUserButton").load("showUser.html", alert("lort"));
        // $("logoutButton").load("Login.html", alert("AGAGRAHGRAHRGAHRGAHRGAHRG"));
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