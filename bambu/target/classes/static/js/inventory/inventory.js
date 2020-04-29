$(function () {
    $('#container-modal-entry').load("entrada", function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success")
            alert("External content loaded successfully!");
        if (statusTxt == "error")
            alert('willie');
    });

});