
var messageManage = new MessageManage();
messageManage.showNewMessage()

function MessageManage() {
//##################################################################//
    /**
     * 请求新信息
     */
    this.showNewMessage = function() {
        _this=this
        $.ajax(
            {
                url:'/shortMessage/showNewMessage',
                type: 'GET',
                async: false,
                success:function (data) {
                    if(data){
                        _this.fillNewMessage(data['entityList'])
                        _this.fillNewMessageCount(data['entityList'])
                    }
                },
                error:function (e) {
                    alert("获取用户信息失败");
                }

            }
        )

    }

    /**
     * 填充新信息提示内容
     * @param entityList
     */
    this.fillNewMessage = function(entityList) {
        content = "";
        for (var i in entityList){
            content+=("" +
            "<a href=\"#\" class=\"list-group-item\">"+
                "<div class=\"list-group-status status-online\"></div>"+
               " <img src=\"/assets/images/users/user2.jpg\" class=\"pull-left\" alt=\"System\"/>"+
                "<span class=\"contacts-title\" id=\"otherUserId\""+entityList[i]['fromUser']['uid']+">"+entityList[i]['fromUser']['name']+"</span>"+
                "<p>"+entityList[i]['content']+"</p>"+
                "</a>"
            )
        }
        $("#message_moudle").html(content)
    }

    /**
     * 填充新信息数量
     * @param entityList
     */
    this.fillNewMessageCount=function (entityList) {
        $("#new_message_count").html(entityList.length)
        $("#new_message_inner_count").html(entityList.length+"message")
    }
//##################################################################//

//##################################################################//
    this.showContact = function () {
        _this=this
        $.ajax(
            {
                url:'/shortMessage/showNewMessage',
                type: 'GET',
                async: false,
                success:function (data) {
                    if(data){
                        _this.fillNewMessage(data['entityList'])
                        _this.fillNewMessageCount(data['entityList'])
                    }
                },
                error:function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    }
    this.fillContact = function () {

    }
    this.showMessageContent = function(){

    }
    this.fillMessageContent = function () {

    }
//##################################################################//



}



