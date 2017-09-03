
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
        _this = this
        content = "";
        for (var i in entityList){

            content+=("" +
            "<a href=\"/shortMessage/messagePage/"+entityList[i]['fromUser']['uid']+"\" class=\"list-group-item\">"+
                "<div class=\"list-group-status status-online\"></div>"+
               " <img src=\"/assets/images/users/user2.jpg\" class=\"pull-left\" alt=\"System\"/>"+
                "<span class=\"contacts-title\" id=\"otherUserId\""+entityList[i]['fromUser']['uid']+">"+entityList[i]['fromUser']['name']+"</span>"+
                "<p>"+_this.cutContentByLenth(entityList[i]['content'],25)+"</p>"+
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
                url:'/shortMessage/showContact',
                type: 'GET',
                async: false,
                success:function (data) {
                    if(data){
                        _this.fillContact(data['entityList'])
                    }
                },
                error:function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    }
    this.fillContact = function (entityList) {
        _this=this
        content = "";
        var currentUser = $.cookie("userData")
        currentUser = JSON.parse(currentUser);
        for (var i in entityList){
            if(currentUser['uid'] == entityList[i]['fromUser']['uid']){
                content+=(
                    "<a href=\"# \" onclick=\'messageManage.showMessageContent("+entityList[i]['toUser']['uid']+")\' class=\"list-group-item\">"+
                    "<div class=\"list-group-status status-online\"></div>"+
                    "<img src=\"/assets/images/users/user.jpg\" class=\"pull-left\" alt=\""+entityList[i]['toUser'][name]+"\">"+
                    "<span class=\"contacts-title\">"+entityList[i]['toUser']['name']+"</span>"+
                    "<p>"+_this.cutContentByLenth(entityList[i]['content'],20)+"</p>"+
                    "</a>"
                )
            }
            else if(currentUser['uid'] == entityList[i]['toUser']['uid']){
                content+=(
                    "<a href=\"#\" onclick=\'messageManage.showMessageContent("+entityList[i]['fromUser']['uid']+")\' class=\"list-group-item\">"+
                    "<div class=\"list-group-status status-online\"></div>"+
                    "<img src=\"/assets/images/users/user.jpg\" class=\"pull-left\" alt=\""+entityList[i]['fromUser'][name]+"\">"+
                    "<span class=\"contacts-title\">"+entityList[i]['fromUser']['name']+"</span>"+
                    "<p>"+_this.cutContentByLenth(entityList[i]['content'],20)+"</p>"+
                    "</a>"
                )

            }
        }
        $("#contactList").html(content)

    }
    this.showMessageContent = function(id){
        _this=this
        $.ajax(
            {
                url:'/shortMessage/readMessage/'+id,
                type: 'GET',
                async: false,
                success:function (data) {
                    if(data){
                        _this.fillMessageContent(data['entity'])
                    }
                },
                error:function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    }
    /**
     * 填充聊天内容
     * @param entity
     */
    this.fillMessageContent = function (entity) {
        var currentUser = $.cookie("userData")
        currentUser = JSON.parse(currentUser);
        content="";
        for (var i in entity['messageInfo']){
            if(entity['messageInfo'][i]['fromUserId'] == currentUser['uid']){
                content+=
                    " <div class=\"item in\">"+
                    " <div class=\"image\">"+
                    "<img src=\"/assets/images/users/user2.jpg\" alt=\""+entity['currentUser']['name']+"\">"+
                    "</div>"+
                    "<div class=\"text\">"+
                    "<div class=\"heading\">"+
                    "<a href=\"#\">"+entity['currentUser']['name']+"</a>"+
                    "<span class=\"date\">"+entity['messageInfo'][i]['sendTime']+"</span>"+
                    " </div>"+
                    entity['messageInfo'][i]['messageContent']+
                    "</div>"+
                    "</div>"
            }
            else {
                content+=
                    " <div class=\"item\">"+
                    "<div class=\"image\">"+
                    "<img src=\"/assets/images/users/user.jpg\" alt=\""+entity['otherUser']['name']+"\">"+
                    "</div>"+
                    "<div class=\"text\">"+
                    "<div class=\"heading\">"+
                    "<a href=\"#\">"+entity['otherUser']['name']+"</a>"+
                    "<span class=\"date\">"+entity['messageInfo'][i]['sendTime']+"</span>"+
                    "</div>"+
                    entity['messageInfo'][i]['messageContent']+
                    "</div>"+
                    "</div>"
            }
            $("#messageContent").html(content)
            $(".messages .item").each(function(index){
                var elm = $(this);
                setInterval(function(){
                    elm.addClass("item-visible");
                },index*300);
            });
        }

    }
//##################################################################//








//##################################################################//
    this.cutContentByLenth = function (content,lenth) {
        var  messageContent="";
        if(content != null && content.length>25){
             messageContent = (content.substring(0,lenth)+"......");
            return messageContent;
        }
        else
          {
            messageContent = content;
            return messageContent;
          }
    }


//##################################################################//







}



