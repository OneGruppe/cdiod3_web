/*			
 * subsite_buttons.js
 */

$(document).ready(function() {

	// FRONTPAGE BUTTON
	$("#frontPage").click(function() {
		$(".loadToDiv").load("adminlogin.html"); 		
	});
	
	// CREATE USER BUTTON
	$("#createUserButton").click(function() {
		$(".loadToDiv").load("createUser.html");
	});
	
	// UPDATE USER BOTTON
	$("#updateUserBotton").click(function() {
		$(".loadToDiv").load("updateUser.html"); 		
	});
	
	// DELETE USER BOTTON
	$("#deleteUserBotton").click(function() {
		$(".loadToDiv").load("deleteUser.html"); 		
	});

	// SHOW ALL USERS BUTTON
	$("#showAllUsersButton").click(function() {
		$(".loadToDiv").load("showAllUsers.html");
	});
	
	// SHOW USER BUTTON
	$("#showUserButton").click(function() {
		$(".loadToDiv").load("showUser.html");
	});
	
	// LOG OUT BUTTON / RELOAD SITE
	$("#logoutButton").click(function() {
		window.location.reload();    		
	});
	
});