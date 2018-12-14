//设置导航栏
function setNavtab(){
    var curUrl = window.location.href;
    if (curUrl.indexOf("agreement") != -1){
        var tab = $('.header-tab2').find('li').eq(0);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("operation") != -1){
        var tab = $('.header-tab2').find('li').eq(1);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("dispatch") != -1){
        var tab = $('.header-tab2').find('li').eq(2);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("complaint") != -1){
        var tab = $('.header-tab2').find('li').eq(3);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("punish") != -1){
        var tab = $('.header-tab2').find('li').eq(4);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("state") != -1){
        var tab = $('.header-tab2').find('li').eq(5);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("declaration") != -1){
        var tab = $('.header-tab2').find('li').eq(6);
        tab.find('a').css('color', '#ff1800');
    }
    if (curUrl.indexOf("contact") != -1){
        var tab = $('.header-tab2').find('li').eq(7);
        tab.find('a').css('color', '#ff1800');
    }
}

//设置默认图片
function setDefaultImg(){
    $("img").one("error", function (e) {
        $(this).attr("src", "images/no_img.png");
    });
}

//格式化时间戳 yyyy-mm-dd HH:mm:ss
function formatDateTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
};

//格式化时间戳  yyyy-mm-dd
function fmtDate(obj){
    var date =  new Date(obj);
    var y = 1900+date.getYear();
    var m = "0"+(date.getMonth()+1);
    var d = "0"+date.getDate();
    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
}


/**
 * 获取url参数
 * @param name
 * @returns {*}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * 获取url字符串的参数
 * @param String
 */
function getUrlParam(url, name){
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let index = url.indexOf('?');
    let r = url.substr(index + 1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 * 获取滚动条位置
 * @returns {{top: *, left: *, width: *, height: *}}
 * @constructor
 */
function ScollPostion() {
    var t, l, w, h;
    if (document.documentElement && document.documentElement.scrollTop) {
        t = document.documentElement.scrollTop;
        l = document.documentElement.scrollLeft;
        w = document.documentElement.scrollWidth;
        h = document.documentElement.scrollHeight;
    } else if (document.body) {
        t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight;
    }
    return {
        top: t,
        left: l,
        width: w,
        height: h
    };
}