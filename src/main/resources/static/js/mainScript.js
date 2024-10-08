let ipGlobal;
let portGlobal;
let statusGlobal;
const API_URL = "http://localhost:8080/";

$(function (){
	$("#changePassDiv").submit(function(e){
		if($("#newPassword").val() === $("#confirmPassword").val()){
			$.ajax({
				url: API_URL + "changePassword",
				method: "post",
				dataType: "json",
				data: {newPassword: $("#newPassword").val(), password: $("#oldPassword").val()},
				success: function(data){
					if(data.status === "OK"){
						alert("Successful!");
						$("#changePassDiv").bPopup().close();
						$("input").val("");
					}
				},
				error: function(data){
					errorStatus(data.responseJSON.status)
				}
			});
		}
		else {
			alert("The entered passwords do not match!");
		}
		e.preventDefault();
	});
});
function errorStatus(status){
	if(status === "ERROR_ENTITY_ALREADY_EXISTS"){
		alert("Error! Entity already exists!");
	}
	else if(status === "ERROR"){
		alert("Error! Bad request!");
	}
}
function rebootServer(){
	let result = confirm("You are sure?");
	if (result) {
		$.ajax({
			url: API_URL + "api/server/reboot",
			method: "post"
		});
	}
}
function changePassword(){
	$("#changePassDiv").bPopup();
}
function sendCommandToUserPC(comm) {
	if(statusGlobal === "active"){
		if(comm === "screen"){ //Если нам нужен скрин
			$.ajax({
				url: API_URL + "api/jc/controlUserPC",
				method: "post",
				dataType: "json",
				data: { ipAddress : ipGlobal, port: portGlobal, command: comm},
				success: function(data) {
					$("#control").hide();
					$("#screenShow").empty().show().append("<img src= " + API_URL + "files/photo?id="+ Math.random() +"  alt=''/>");
					$("#updateScreen").show();
				},
				error: function (data){
					errorStatus(data.responseJSON.status);
				}
			});

		}
		else {
			let choose = confirm("You are sure?");
			if(choose){
				$.ajax({
					url: API_URL + "api/jc/controlUserPC",
					method: "post",
					dataType: "json",
					data: { ipAddress : ipGlobal, port: portGlobal, command: comm},
					success: function(data) {
						if(data.status === "OK"){
							alert("Successful!");
							jcShow();
						}
					},
					error: function (data){
						errorStatus(data.responseJSON.status);
					}
				});
			}
		}
	}
	else{
		alert("PC inactive!");
	}
}
function showLogs(){ //Вывод логов
	$("#logShow").show();
	$("#logView").empty();
	$(".headerP").text("Logs");
	$("#jcShow").hide();
	$("#controlShow").hide();
	$("#usersShow").hide();
	$("#settingsShow").hide();
	$.ajax({
		url: API_URL + "api/server/showLogs",
		method: "get",
		success: function (data){
			data.forEach(log => {
				$("#logView").append(log + "\n");
			})
		}
	});
}
function logout(){
	window.location = "/logout";
}