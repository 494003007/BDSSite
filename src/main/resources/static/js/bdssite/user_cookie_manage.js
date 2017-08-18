var userData;
var userPermissions;

function getUserData(){
    userData = $.cookie('userData');
    if(!userData || userData === 'null'){
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
    if(!userPermissions || userPermissions === 'null'){
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

function deleteUserData() {
    $.cookie('userData',null,{path:'/'});
    $.cookie('userPermissions',null,{path:'/'});
}

function reflashUserData() {
    deleteUserData()
    getUserData();
    if(userData){
        getPermissions();
    }
}

function logout() {
    deleteUserData();
    window.location.href = "/logout";
}