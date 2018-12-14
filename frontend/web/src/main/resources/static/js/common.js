
//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//导航栏适配
function setNavbar() {
    if ($(window).width() <= 576) {
        $('#logo').width('100px');
        $('#header').css('padding', '9px 8px');
        $('#left-nav').css('padding-right', '0');
    }
}