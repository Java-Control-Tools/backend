$(function(){
    $(".inputPass").val("");
    $("#scanForm").submit(function (e) { // Привязка к форме, делаем отправку через аякс без обновления
        let sendData = $(this).serialize();
        $.ajax({ //Добавление пользователя
            url: API_URL + "api/jc/addUserPC",
            method: "post",
            dataType: "json",
            data: sendData,
            success: function (data) {
                if (data.status === "OK") {
                    showUsers();
                    $("input").val("");
                }
            },
            error: function (data) {
                errorStatus(data.responseJSON.status);
                $("input").val("");
            }
        });
        e.preventDefault();
    });
    $("#updateForm").submit(function (e) { // Привязка к форме МОДАЛКИ, делаем отправку через аякс без обновления
        $.ajax({ //Добавление пользователя
            url: API_URL + "api/jc/updateUserPC",
            method: "post",
            dataType: "json",
            data: {oldIp: ipGlobal, oldPort: portGlobal, newIp: $("#ipUp").val(), newPort: $("#portUp").val()},
            success: function (data) {
                if (data.status === "OK") {
                    showUsers();
                    $("input").val("");
                }
            },
            error: function (data) {
                errorStatus(data.responseJSON.status);
                $("input").val("");
            }
        });
        e.preventDefault();
    });
    login();

});