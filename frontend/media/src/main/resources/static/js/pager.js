//设置当前页码
function setCurPage(page, maxPage, oldPage) {
    if (oldPage !== 0) {
        var obj = $('.pager li').eq(oldPage).children('button');
        obj.css('color', '#000');
        obj.removeAttr('disabled');
        if (oldPage === 1) {
            var obj = $('.pager li').eq(0).children('button');
            obj.removeAttr('disabled');
            obj.css('color', '#000');
        }
        if (oldPage === maxPage) {
            var obj = $('.pager li').eq(maxPage + 1).children('button');
            obj.removeAttr('disabled');
            obj.css('color', '#000');
        }
    }
    var obj = $('.pager li').eq(page).children('button');
    obj.css('color', '#ff1800');
    obj.attr('disabled', 'true');
    if (page === 1) {
        var obj1 = $('.pager li').eq(0).children('button');
        obj1.css('color', '#aaa');
        obj1.attr('disabled', 'true');
    }
    if (page === maxPage) {
        var obj2 = $('.pager li').eq(maxPage + 1).children('button');
        obj2.css('color', '#aaa');
        obj2.attr('disabled', 'true');
    }
}

