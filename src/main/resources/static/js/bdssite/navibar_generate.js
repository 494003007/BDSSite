/**
 用于前端导航栏的生成（根据权限）

 Create by D
 **/
var userData;
var userPermissions;

if(isExist($('#naviBarDiv'))){
    getUserData();
    if(userData){
        getPermissions();
        fillNavibarInformation();
        generateNavibar();
    }
}


function getUserData(){
    userData = $.cookie('userData');
    if(!userData){
        $.ajax(
            {
                url:'/AuthorizationManage/currentUser',
                type: 'POST',
                async: false,
                success:function (data) {
                    if(data){
                        userData = data;
                        $.cookie('userData',JSON.stringify(data),{path:'/'});
                    }
                },
                error:function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    }else{
        userData = JSON.parse(userData);
    }
}

function getPermissions(){
    userPermissions = $.cookie('userPermissions');
    if(!userPermissions){
        $.ajax(
            {
                url:'/AuthorizationManage/permissions/byUid/' + userData['uid'],
                type: 'POST',
                async: false,
                success:function (data) {
                    userPermissions = data['entityList'];
                    $.cookie('userPermissions',JSON.stringify(data['entityList']),{path:'/'});
                },
                error:function (e) {
                    alert("获取权限信息失败");
                }

            }
        )
    }else{
        userPermissions = JSON.parse(userPermissions);
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


