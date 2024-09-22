$(function (){
	$("#formAddUser").submit(function(e){
		e.preventDefault();
	});
});
function readUsers(){
    $("#accountsBody").empty();
    $.ajax({
        url: "/api/showUsers",
        method: "GET",
        success: function (data){
            data.forEach(user => $("#accountsBody").append("<tr><td>" + user.id +
                "</td><td>" + user.username + "</td><td>"
                + user.role + "</td><td><button class='buttonTable' onclick='editUser(\"" + user.id + "\",\"" + user.username + "\",\"" + user.role + "\")'>Edit</button></td>"
            ));
        }
    });
}
function editUser(id,username, role){
    userUpdateDivShow(username);
    $("#username").empty().append("User name: " + username + "<button class='settingsButton' id = 'smallSettingsButton'>Edit</button>");
    $("#id").empty().append("ID: " + id);
    $("#role").empty().append("Role: " + role + "<button class='settingsButton' id = 'smallSettingsButton'>Edit</button>");
}