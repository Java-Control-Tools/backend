function readUsers(){
    $("#accountsBody").empty();
    $.ajax({
        url: "/api/showUsers",
        method: "GET",
        success: function (data){
            data.forEach(user => $("#accountsBody").append("<tr><td>" + user.id +
                "</td><td>" + user.username + "</td><td>"
                + user.role + "</td><td><button class='buttonTable' onclick='editUser(\""+ user.id + "\")'>Edit</button></td>"
            ));
        }
    });
}
function editUser(id){

}