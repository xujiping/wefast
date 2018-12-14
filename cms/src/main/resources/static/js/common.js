
// 时间戳转日期
function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2,3}$/,' ');
}