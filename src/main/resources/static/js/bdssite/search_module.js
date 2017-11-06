function search(){
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
}