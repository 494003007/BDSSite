var messageManage = new MessageManage();




/**
 * 聊天模块
 * @constructor
 */
function MessageManage() {

    this.alert = new AlertBox("messageAlert");
//###########################   --  START  --   ###############################//
    /**
     * 请求新信息
     */
    this.showNewMessage = function () {

        var _this = this;
        $.ajax(
            {
                url: '/shortMessage/showNewMessage',
                type: 'GET',
                async: true,
                success: function (data) {
                    if (data['entityList']) {
                        _this.fillNewMessage(data['entityList']);
                        _this.fillNewMessageCount(data['entityList'])
                    }else{
                        _this.alert.addNewAlert("warning","未登陆","登陆后才可使用私信，请登录或注册");
                        clearInterval(_this.interval);
                    }
                },
                error: function () {
                    clearInterval(_this.interval);
                    _this.alert.addNewAlert("danger","与服务器失去连接","请检查您的网络或稍等一段时间");
                }

            }
        )

    };
    /**
     * 填充新信息提示内容
     * @param entityList
     */
    this.fillNewMessage = function (entityList) {
        var _this = this;
        var content = "";
        for (var i in entityList) {

            content += (
                "<a href=\"#\" onclick='messageManage.clickNewMessage(" + entityList[i]['fromUser']['uid'] + ")' class=\"list-group-item\">" +
                "<div class=\"list-group-status status-online\"></div>" +
                "<img src=\"/AuthorizationManage/getUserIcon?id="+entityList[i]['fromUser']['uid']+"\" class=\"pull-left\" alt=\"System\"/>" +
                "<span class=\"contacts-title\" id=\"otherUserId\"" + entityList[i]['fromUser']['uid'] + ">" + entityList[i]['fromUser']['name'] + "</span>" +
                "<p>&nbsp;" + _this.cutContentByLenth(entityList[i]['content'], 25) + "</p>" +
                "</a>"
            )
        }
        $("#message_moudle").html(content);
    };
    /**
     * 填充新信息数量
     * @param entityList
     */
    this.fillNewMessageCount = function (entityList) {
        $("#new_message_count").html(entityList.length);
        $("#new_message_inner_count").html(entityList.length + " message")
    };
    /**
     * 聊天页面跳转
     * @param id
     */
    this.clickNewMessage = function (id) {
        $.cookie('otherId', id, {path: '/'});
        $(location).attr('href', '/shortMessage/messagePage');
    };
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    /**
     * 请求通讯录
     */
    this.showContact = function () {
        var _this = this;
        $.ajax(
            {
                url: '/shortMessage/showContact',
                type: 'GET',
                async: true,
                success: function (data) {
                    if (data) {
                        _this.fillContact(data['entityList'])
                    }
                },
                error: function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    };
    /**
     * 填充通讯录
     * @param entityList
     */
    this.fillContact = function (entityList) {
        var _this = this;
        var content = "";
        var currentUser = $.cookie("userData")
        currentUser = JSON.parse(currentUser);
        for (var i in entityList) {
            if (currentUser['uid'] == entityList[i]['fromUser']['uid']) {
                content += (
                    "<a href=\"# \" onclick=\'messageManage.showMessageContent(" + entityList[i]['toUser']['uid'] + ")\' class=\"list-group-item\">" +
                    "<div id='status"+entityList[i]['toUser']['uid']+"' class=\"list-group-status status-online\"></div>" +
                    "<img src=\"/AuthorizationManage/getUserIcon?id="+entityList[i]['toUser']['uid']+"\" class=\"pull-left\" alt=\"" + entityList[i]['toUser'][name] + "\">" +
                    "<span class=\"contacts-title\">" + entityList[i]['toUser']['name'] + "</span>" +
                    "<p>&nbsp;" + _this.cutContentByLenth(entityList[i]['content'], 20) + "</p>" +
                    "</a>"
                )
            }
            else if (currentUser['uid'] == entityList[i]['toUser']['uid']) {
                content += (
                    "<a href=\"#\" onclick=\'messageManage.showMessageContent(" + entityList[i]['fromUser']['uid'] + ")\' class=\"list-group-item\">" +
                    "<div id='status"+entityList[i]['fromUser']['uid']+"' class=\"list-group-status status-online\"></div>" +
                    "<img src=\"/AuthorizationManage/getUserIcon?id="+entityList[i]['fromUser']['uid']+"\" class=\"pull-left\" alt=\"" + entityList[i]['fromUser'][name] + "\">" +
                    "<span class=\"contacts-title\">" + entityList[i]['fromUser']['name'] + "</span>" +
                    "<p>&nbsp;" + _this.cutContentByLenth(entityList[i]['content'], 20) + "</p>" +
                    "</a>"
                )

            }
        }
        $("#contactList").html(content)

    };
    /**
     * 请求聊天内容
     * @param id
     */
    this.showMessageContent = function (id) {
        var _this = this;
        $("#status"+ $.cookie("otherId")).removeClass("status-away").addClass("status-online");
        $.cookie("otherId",id, {path: '/'});
        $("#status"+ $.cookie("otherId")).removeClass("status-online").addClass("status-away");
        setInterval("messageManage.updateContent("+$.cookie("otherId")+")",8000);
        $.ajax(
            {
                url: '/shortMessage/readMessage/' + id,
                type: 'GET',
                async: true,
                success: function (data) {
                    if (data) {
                        _this.fillMessageContent(data['entity'])
                    }
                },
                error: function (e) {
                    alert("获取用户信息失败");
                }

            }
        )
    };
    /**
     * 填充聊天内容
     * @param entity
     */
    this.fillMessageContent = function (entity) {
        var _this = this;
        var content = _this.makeUpcontent(entity);

        $("#chatUserName").text(" • "+entity["otherUser"]["name"]);
        $("#messageContent").html(content);
        $("#messageSend").html(
            "<div class=\"input-group-btn\">" +
            "<button class=\"btn btn-default\"><span class=\"fa fa-camera\"></span></button>" +
            "<button class=\"btn btn-default\"><span class=\"fa fa-chain\"></span></button>" +
            "</div>" +
            "<input id='messagePost' type=\"text\" class=\"form-control\" placeholder=\"Your message...\"/>" +
            "<div class=\"input-group-btn\">" +
            "<button class=\"btn btn-default\" onclick='messageManage.sendMessage("+ entity["otherUser"]["uid"]+",\""+entity['currentUser']['name']+"\")'>Send</button>" +
            "</div>"
        );
        _this.setMessageVisible();


    };
    /**
     * 请求更新聊天内容
     */
    this.updateContent = function (id) {
        var _this = this;
        $.ajax({
            url: '/shortMessage/updateMessage/' + id,
            type:'GET',
            async:true,
            success:function (data) {
                if (data){
                    _this.fillUpdateContent(data['entity'])
                }

            },
            error:function (e) {

            }
        })
    };
    /**
     * 填充更新的聊天内容
     */
    this.fillUpdateContent = function (entity) {
        var _this = this;
        var content = _this.makeUpcontent(entity);
        $("#messageContent").prepend(content);
        _this.setMessageVisible();
    };
    /**
     * 拼接聊天内容
     */
    this.makeUpcontent = function (entity) {
        var _this = this;
        var currentUser = $.cookie("userData");
        currentUser = JSON.parse(currentUser);
            var content = "";
            for (var i in entity['messageInfo']) {
                if (entity['messageInfo'][i]['fromUserId'] == currentUser['uid']) {
                    content +=
                        " <div style=\"max-width:70%;float:right\" class=\"item in\">" +
                        " <div class=\"image\">" +
                        "<img src=\"/AuthorizationManage/getUserIcon?id="+entity['messageInfo'][i]['fromUserId']+"\" alt=\"" + entity['currentUser']['name'] + "\">" +
                        "</div>" +
                        "<div class=\"text\">" +
                        "<div class=\"heading\">" +
                        "<a href=\"#\">" + entity['currentUser']['name'] + "</a>" +
                        "<span class=\"date\">" + _this.timeFormatter(entity['messageInfo'][i]['sendTime'] )+ "</span>" +
                        " </div>" +
                        entity['messageInfo'][i]['messageContent'] +
                        "</div>" +
                        "</div>"
                }
                else {
                    content +=
                        " <div style=\"max-width:70%\" class=\"item\">" +
                        "<div class=\"image\">" +
                        "<img src=\"/AuthorizationManage/getUserIcon?id="+entity['messageInfo'][i]['fromUserId']+"\" alt=\"" + entity['otherUser']['name'] + "\">" +
                        "</div>" +
                        "<div class=\"text\">" +
                        "<div class=\"heading\">" +
                        "<a href=\"#\">" + entity['otherUser']['name'] + "</a>" +
                        "<span class=\"date\">" + _this.timeFormatter(entity['messageInfo'][i]['sendTime']) + "</span>" +
                        "</div>" +
                        entity['messageInfo'][i]['messageContent'] +
                        "</div>" +
                        "</div>"
                }

            }
            return content
        };
    /**
     * 聊天内容模块可见
     */
    this.setMessageVisible =function () {
        $(".messages .item").each(function (index) {

            var elm = $(this);
            setInterval(function () {
                elm.addClass("item-visible");
            }, index * 100);
        });
    };
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    /**
     * 发送信息
     * @param id
     * @param currentUserName
     */
    this.sendMessage = function (id,currentUserName) {
        var _this = this;
        var currentUser = $.cookie("userData");
        currentUser = JSON.parse(currentUser);
        var messagePost = $("#messagePost");
        $.ajax({
            url: '/shortMessage/sendMessage/'+id,
            type: 'POST',
            async: true,
            data: {content:messagePost.val()},
            success: function (data) {
                if (data) {
                    $("#messageContent").prepend(
                        " <div style=\"max-width:70%;float:right\" class=\"item in item-visible\">"  +
                        " <div class=\"image\">" +
                        "<img src=\"/AuthorizationManage/getUserIcon?id="+currentUser['uid']+"\" alt=\"" + currentUserName + "\">" +
                        "</div>" +
                        "<div class=\"text\">" +
                        "<div class=\"heading\">" +
                        "<a href=\"#\">" + currentUserName + "</a>" +
                        "<span class=\"date\">" + _this.timeFormatter(Date.now()) + "</span>" +
                        " </div>" +
                        messagePost.val() +
                        "</div>" +
                        "</div>"
                    );
                    messagePost.val("")
                }
            },
            error: function (e) {
                alert("发送失败");
            }
        })
    };
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    /**
     *  取一定长度字符串
     */
    this.cutContentByLenth = function (content, lenth) {
        var messageContent = "";
        if (content != null && content.length > 25) {
            messageContent = (content.substring(0, lenth) + "......");
            return messageContent;
        }
        else {
            messageContent = content;
            return messageContent;
        }
    };
    /**
     * datetime日期转换
     */
    this.timeFormatter = function(value) {

        var da = new Date(value);

        return da.getFullYear() + "-" + (da.getMonth() + 1) + "-" + da.getDate() + " " + da.getHours() + ":" + da.getMinutes() + ":" + da.getSeconds();

    };
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    this.interval = setInterval("messageManage.showNewMessage();",8000);
    this.showNewMessage();
//###########################   --   END   --   ###############################//
}



