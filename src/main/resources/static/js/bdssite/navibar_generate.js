/**
 用于前端导航栏的生成（根据权限）

 Create by D
 **/


if(isExist($('#naviBarDiv'))){
    getUserData();
    if(userData){
        getPermissions();
        fillNavibarInformation();
        generateNavibar();
    }
}


function fillNavibarInformation() {
    $('#userNameDiv').html(userData['username']);
    if(userData['roles'][0]){
        $('#userRoleDiv').html(userData['roles'][0]['role_describe']);
    }else{
        $('#userRoleDiv').html('');
    }

}

function generateNavibar() {
    var nodes = {};
    $.each(userPermissions,function (index,element) {
        if(element['available']){
            var temp = createNaviLi(element);
            if(element['parentIds']){
                var parents = element['parentIds'].split(",");
                var parentDom = $('#' + 'navibar_' + parents[parents.length-1]);
                if(isExist(parentDom)){
                    if(isOpenableNavi(parentDom)){
                        $('#' + 'navibar_' + parents[parents.length-1]+ '_ul').append(temp);
                    }else{
                        changeToOpenableNavi(parentDom).append(temp);
                    }
                }else{
                    return;
                }
            }else{
                $('#naviBarDiv').append(temp);
                nodes[element['id']] = temp;
            }
        }
    });

}

function isOpenableNavi(jqueryDom) {
    return jqueryDom.hasClass('xn-openable');
}

function changeToOpenableNavi(jqueryDom){
    jqueryDom.addClass('xn-openable');
    var domUl = $('<ul></ul>').attr('id',jqueryDom.attr('id') + '_ul' );
    jqueryDom.append(domUl);
    return domUl;
}

function createNaviLi(element) {
    var li = $("<li></li>").attr('id','navibar_' + element['id'] );
    // alert('<a href="' + (element['url']?element['url']:'#') + '"><span class="fa fa-files-o"></span> <span class="xn-text">' + element['name'] + '</span></a>');
    li.append('<a href="' + (element['url']?element['url']:'#') + '"><span class="fa fa-cogs"></span>' + element['name'] + '</a>');
    return li;
}


