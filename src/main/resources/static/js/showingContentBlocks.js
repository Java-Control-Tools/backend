function jcShow() {
    $(".headerP").text("Java control");
    $("#jcShow").show();
    $("#settingsShow").hide();
    $("#controlShow").hide();
    $("#logShow").hide();
    $("#updateScreen").hide();
    $("#usersShow").hide();
    $('#updateUserDiv').hide();
    showUsers();
}
function settingsShow(){
    $(".headerP").text("Settings");
    $("#jcShow").hide();
    $("#controlShow").hide();
    $("#settingsShow").show();
    $("#updateScreen").hide();
    $("#usersShow").hide();
    $("#logShow").hide();
    $('#updateUserDiv').hide();
}
function controlShow(ipAddress, port, status){ //Заход на пользователя
    ipGlobal = ipAddress;
    portGlobal = port;
    statusGlobal = status;
    $(".headerP").text(ipAddress + ":" + port + " - " + status);
    $("#jcShow").hide();
    $("#controlShow").show();
    $("#control").show();
    $("#screenShow").hide();
    $("#usersShow").hide();
    $("#settingsShow").hide();
    $("#logShow").hide();
    $("#updateScreen").hide();
    $('#updateUserDiv').hide();
}
function usersShow(){
    $(".headerP").text("Accounts settings");
    $("#jcShow").hide();
    $("#controlShow").hide();
    $("#settingsShow").hide();
    $("#updateScreen").hide();
    $("#usersShow").show();
    $("#logShow").hide();
    $('#updateUserDiv').hide();
    readUsers();
}
function userUpdateDivShow(username){
    $(".headerP").text(username);
    $("#jcShow").hide();
    $("#controlShow").hide();
    $("#settingsShow").hide();
    $("#updateScreen").hide();
    $("#usersShow").hide();
    $("#logShow").hide();
    $('#updateUserDiv').show();
}