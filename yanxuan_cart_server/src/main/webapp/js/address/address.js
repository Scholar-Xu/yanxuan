$(function(){

    $("#addAddr").click(function () {
        $("#addr_modal").show();
    });

    $("#addr_close").click(function () {
        $("#addr_modal").hide();
    });

    $("#city").click(function (e) {
        SelCity(this,e);
    });
    $("s").click(function (e) {
        SelCity(document.getElementById("city"),e);
    });
});