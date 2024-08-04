function showUsers(){
    $.ajax({
        url: API_URL + "api/jc/showUsersPC",
        method: "get",
        dataType: "json",
        success: function(data){
            $("#tableBody").empty();
            data.forEach((user) => {
                if(user.status === "active"){
                    $("#tableUsers").append("<tr><td>" + user.ipAddress
                        + "</td><td>" + user.port
                        + "</td><td style='color: green'>" + user.status
                        + "</td><td><button class='buttonTable' onclick='controlShow(\""+ user.ipAddress + "\",\"" + user.port +"\",\""+ user.status + "\")'>Control</button></td></tr>");
                }
                else if(user.status === "inactive"){
                    $("#tableUsers").append("<tr><td>" + user.ipAddress
                        + "</td><td>" + user.port
                        + "</td><td style='color: red'>" + user.status
                        + "</td><td><button class='buttonTable' onclick='controlShow(\""+ user.ipAddress + "\",\"" + user.port +"\",\""+ user.status + "\")'>Control</button></td></tr>");
                }
            });
        }
    });
}
function deleteUser(){
    $.ajax({
        url: API_URL + "api/jc/deleteUserPC",
        method: "post",
        dataType: "json",
        data: {ipAddress : ipGlobal, port: portGlobal},
        success: function(data) {
            if(data.status === "OK"){
                alert("Success!");
                jcShow();
            }
        },
        error: function (data){
            errorStatus(data.responseJSON.status);
        }
    });
}
function updateUser(){ // Модалка с обновлением полей
    $("#updateDiv").bPopup();
    $("#ipUp").val(ipGlobal);
    $("#portUp").val(portGlobal);
}