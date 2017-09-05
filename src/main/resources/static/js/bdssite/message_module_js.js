var messageManage = new MessageManage();
messageManage.showNewMessage()


/**
 * 聊天模块
 * @constructor
 */
function MessageManage() {
//###########################   --  START  --   ###############################//
    /**
     * 请求新信息
     */
    this.showNewMessage = function () {


        _this = this
        $.ajax(
            {
                url: '/shortMessage/showNewMessage',
                type: 'GET',
                async: false,
                success: function (data) {
                    if (data) {
                        _this.fillNewMessage(data['entityList'])
                        _this.fillNewMessageCount(data['entityList'])
                    }
                },
                error: function (e) {
                    alert("获取用户信息失败");
                }

            }
        )

    }
    /**
     * 填充新信息提示内容
     * @param entityList
     */
    this.fillNewMessage = function (entityList) {
        _this = this
        content = "";
        for (var i in entityList) {

            content += ("" +
                "<a href=\"#\" onclick='messageManage.clickNewMessage(" + entityList[i]['fromUser']['uid'] + ")' class=\"list-group-item\">" +
                "<div class=\"list-group-status status-online\"></div>" +
                " <img src=\"/assets/images/users/user2.jpg\" class=\"pull-left\" alt=\"System\"/>" +
                "<span class=\"contacts-title\" id=\"otherUserId\"" + entityList[i]['fromUser']['uid'] + ">" + entityList[i]['fromUser']['name'] + "</span>" +
                "<p>" + _this.cutContentByLenth(entityList[i]['content'], 25) + "</p>" +
                "</a>"
            )
        }
        $("#message_moudle").html(content)
    }
    /**
     * 填充新信息数量
     * @param entityList
     */
    this.fillNewMessageCount = function (entityList) {
        $("#new_message_count").html(entityList.length)
        $("#new_message_inner_count").html(entityList.length + " message")
    }
    /**
     * 聊天页面跳转
     * @param id
     */
    this.clickNewMessage = function (id) {
        $.cookie('otherId', id, {path: '/'});
        $(location).attr('href', '/shortMessage/messagePage');
    }
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    /**
     * 请求通讯录
     */
    this.showContact = function () {
        _this = this
        $.ajax(
            {
                url: '/shortMessage/showContact',
                type: 'GET',
                async: false,
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
    }
    /**
     * 填充通讯录
     * @param entityList
     */
    this.fillContact = function (entityList) {
        _this = this
        content = "";
        var currentUser = $.cookie("userData")
        currentUser = JSON.parse(currentUser);
        for (var i in entityList) {
            if (currentUser['uid'] == entityList[i]['fromUser']['uid']) {
                content += (
                    "<a href=\"# \" onclick=\'messageManage.showMessageContent(" + entityList[i]['toUser']['uid'] + ")\' class=\"list-group-item\">" +
                    "<div class=\"list-group-status status-online\"></div>" +
                    "<img src=\"/assets/images/users/user.jpg\" class=\"pull-left\" alt=\"" + entityList[i]['toUser'][name] + "\">" +
                    "<span class=\"contacts-title\">" + entityList[i]['toUser']['name'] + "</span>" +
                    "<p>" + _this.cutContentByLenth(entityList[i]['content'], 20) + "</p>" +
                    "</a>"
                )
            }
            else if (currentUser['uid'] == entityList[i]['toUser']['uid']) {
                content += (
                    "<a href=\"#\" onclick=\'messageManage.showMessageContent(" + entityList[i]['fromUser']['uid'] + ")\' class=\"list-group-item\">" +
                    "<div class=\"list-group-status status-online\"></div>" +
                    "<img src=\"/assets/images/users/user.jpg\" class=\"pull-left\" alt=\"" + entityList[i]['fromUser'][name] + "\">" +
                    "<span class=\"contacts-title\">" + entityList[i]['fromUser']['name'] + "</span>" +
                    "<p>" + _this.cutContentByLenth(entityList[i]['content'], 20) + "</p>" +
                    "</a>"
                )

            }
        }
        $("#contactList").html(content)

    }
    /**
     * 请求聊天内容
     * @param id
     */
    this.showMessageContent = function (id) {
        _this = this
        $.ajax(
            {
                url: '/shortMessage/readMessage/' + id,
                type: 'GET',
                async: false,
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
    }
    /**
     * 填充聊天内容
     * @param entity
     */
    this.fillMessageContent = function (entity) {
        var currentUser = $.cookie("userData")
        currentUser = JSON.parse(currentUser);
        content = "";
        for (var i in entity['messageInfo']) {
            if (entity['messageInfo'][i]['fromUserId'] == currentUser['uid']) {
                content +=
                    " <div style=\"max-width:70%;float:right\" class=\"item in\">" +
                    " <div class=\"image\">" +
                    "<img src=\"/assets/images/users/user2.jpg\" alt=\"" + entity['currentUser']['name'] + "\">" +
                    "</div>" +
                    "<div class=\"text\">" +
                    "<div class=\"heading\">" +
                    "<a href=\"#\">" + entity['currentUser']['name'] + "</a>" +
                    "<span class=\"date\">" + entity['messageInfo'][i]['sendTime'] + "</span>" +
                    " </div>" +
                    entity['messageInfo'][i]['messageContent'] +
                    "</div>" +
                    "</div>"
            }
            else {
                content +=
                    " <div style=\"max-width:70%\" class=\"item\">" +
                    "<div class=\"image\">" +
                    "<img src=\"/assets/images/users/user.jpg\" alt=\"" + entity['otherUser']['name'] + "\">" +
                    "</div>" +
                    "<div class=\"text\">" +
                    "<div class=\"heading\">" +
                    "<a href=\"#\">" + entity['otherUser']['name'] + "</a>" +
                    "<span class=\"date\">" + entity['messageInfo'][i]['sendTime'] + "</span>" +
                    "</div>" +
                    entity['messageInfo'][i]['messageContent'] +
                    "</div>" +
                    "</div>"
            }

        }
        $("#messageContent").html(content)
        $("#messageSend").html(
            "<div class=\"input-group-btn\">" +
            "<button class=\"btn btn-default\"><span class=\"fa fa-camera\"></span></button>" +
            "<button class=\"btn btn-default\"><span class=\"fa fa-chain\"></span></button>" +
            "</div>" +
            "<input id='messagePost' type=\"text\" class=\"form-control\" placeholder=\"Your message...\"/>" +
            "<div class=\"input-group-btn\">" +
            "<button class=\"btn btn-default\" onclick='messageManage.sendMessage("+ entity["otherUser"]["uid"]+",\""+entity['currentUser']['name']+"\")'>Send</button>" +
            "</div>"
        )
        $(".messages .item").each(function (index) {
            var elm = $(this);
            setInterval(function () {
                elm.addClass("item-visible");
            }, index * 300);
        });

    }
//###########################   --   END   --   ###############################//

//###########################   --  START  --   ###############################//
    /**
     * 发送信息
     * @param form
     * @param id
     */
    this.sendMessage = function (id,currentUserName) {
        $.ajax({
            url: '/shortMessage/sendMessage/'+id,
            type: 'POST',
            async: false,
            data: {content:$("#messagePost").val()},
            success: function (data) {
                if (data) {
                    $("#messageContent").prepend(
                        " <div class=\"item in item-visible\">" +
                        " <div class=\"image\">" +
                        "<img src=\"/assets/images/users/user2.jpg\" alt=\"" + currentUserName + "\">" +
                        "</div>" +
                        "<div class=\"text\">" +
                        "<div class=\"heading\">" +
                        "<a href=\"#\">" + currentUserName + "</a>" +
                        "<span class=\"date\">" + Date.now() + "</span>" +
                        " </div>" +
                        $("#messagePost").val() +
                        "</div>" +
                        "</div>"
                    )
                    $("#messagePost").val("")
                }
            },
            error: function (e) {
                alert("发送失败");
            }
        })
    }
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
    }

//###########################   --   END   --   ###############################//

}



