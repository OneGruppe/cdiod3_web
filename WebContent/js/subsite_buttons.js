/*			
 * subsite_buttons.js
 */

$(document).ready(function() {

	// FRONTPAGE BUTTON
	$("#frontPage").click(function() {
		$(".loadToDiv").load("html/adminlogin.html"); 		
	});
	
	// CREATE USER BUTTON
	$("#createUserButton").click(function() {
		$(".loadToDiv").load("html/createUser.html");
	});
	
	// UPDATE USER BOTTON
	$("#updateUserBotton").click(function() {
		$(".loadToDiv").load("html/updateUser.html"); 		
	});
	
	// DELETE USER BOTTON
	$("#deleteUserBotton").click(function() {
		$(".loadToDiv").load("html/deleteUser.html"); 		
	});

	// SHOW ALL USERS BUTTON
	$("#showAllUsersButton").click(function() {
		$(".loadToDiv").load("html/showAllUsers.html");
	});
	
	// SHOW USER BUTTON
	$("#showUserButton").click(function() {
		$(".loadToDiv").load("html/showUser.html");
	});
	
	// LOG OUT BUTTON / RELOAD SITE
	$("#logoutButton").click(function() {
		window.location.reload();    		
	});
	
});