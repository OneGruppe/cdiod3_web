$(document).ready(function() {
	$("#loginButton").click(function() {
		$(".login").hide();
		$(".loadToDiv").load("adminlogin.html");
	});
	
    $("#createUserButton").click(function() {
        $(".loadToDiv").load("createUser.html");
    });
    
    $("#showUserButton").click(function() {
        $(".loadToDiv").load("showUsers.html");
    })
    
    $("#logoutButton").click(function() {
        window.location.reload();    		
    })

    $("#frontPage").click(function() {
        $(".loadToDiv").load("adminlogin.html"); 		
    })

});