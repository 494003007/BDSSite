/**
 * Created by Hzldex on 2017/7/17.
 * Using to fill the form by using entity or ajax
 */

/**
 * @callback ajaxCallback
 * @param data
 */

/**
 * Fill the form with ajax
 * @param {Object} ajaxOption - must contain key {url};
 * @param {Object} form - the jquerySelector of the form . Like $('#TheIdOfTheForm')
 * @param {ajaxCallback} [callback] - a callback function after the ajax request success
 *
 * The json that ajax request return must contain the key named 'entity'
 * and it will be used to fill the form.
 * It was base on that the key in the entity match the id of the input in the form
 *
 */
function fillFormUseAjax(ajaxOption,form,callback){
    if(!ajaxOption['url']){
        console.log("fillFormUseAjax:Error:ajaxOption Need the key 'url'");
        return;
    }
    $.ajax({
        url:ajaxOption['url'],
        type: ajaxOption['type']?ajaxOption['type']:'GET',
        data:ajaxOption['data']?ajaxOption['data']:{},
        success:function (data) {
            fillFormUseEntity(data['entity'],form);
            if(callback){
                callback(data);
            }
        },
        error:ajaxOption['error'] ? ajaxOption['error'] :function (e) {
            console.log("fillFormUseAjax:Error:Ajax request error");
        }

    })
}

/**
 * Fill the form with entity
 * @param entity  The data source to fill the form.
 * @param form the jquerySelector of the form . Like $('#TheIdOfTheForm')
 * It was base on that the key in the entity match the id of the input in the form
 */
function fillFormUseEntity(entity,form){
    for(var key in entity){
        if(key=='sex'){
            var element = form.find("[name=sex]");
        }else
             var element = form.find('#' +  key);
        if(isExist(element)) {
            if (!element.is("input[type='hidden']")) {
                element.attr("disabled", false);
                element.attr("isChanged", false);
                element.change(
                    function (eventElement) {
                        $(eventElement.currentTarget).attr("isChanged", true);
                    }
                );
            }
            if (element.is("input[type='text']", "input[type='password']", "textarea") || element.is("textarea")) {
                element.val(entity[key]);
            } else if (element.is("select")) {
                element.find("option[value='" + entity[key] + "']").attr("selected", true);
            } else if(element.is("input[type='radio']"))  {
                form.find("[name=sex][value="+entity[key]+"]").attr("checked",true);
            }else
            {
                element.val(entity[key]);
            }
        }
    }
}

/**
 * Submit the form with ajax
 * @param ajaxOption must contain key {url} {success};
 * @param formId the Id of the form
 */
function ajaxFormSubmit(ajaxOption, formId) {
    if(!ajaxOption['url']){
        console.log("ajaxFormSubmit:Error:ajaxOption Need the key 'url'");
        return;
    }
    $('#' + formId +' [isChanged="false"]').attr("disabled",true);
    var form = new FormData(document.getElementById(formId));


    ajaxOption = {
        url:ajaxOption['url'],
        type: ajaxOption['type']?ajaxOption['type']:'GET',
        contentType: false,
        data:form,
        cache: false,
        success:ajaxOption['success'],
        processData:false,
        error:ajaxOption['error'] ? ajaxOption['error'] : function (e) {
            console.log("fillFormUseAjax:Error:Ajax request error");
        }
    };
    $.ajax(ajaxOption);
}

/**
 *
 * @param containerId
 */

function AlertBox(containerId){
    this.container = document.getElementById(containerId);

}

AlertBox.prototype.defaultMap = {
    "danger":{
        alertTitle:"操作异常!",
        alertContent:""
    },
    "warning":{
        alertTitle:"警告！",
        alertContent:""
    },
    "success":{
        alertTitle:"操作成功！",
        alertContent:""
    }
};

/**
 * 生成提示框 三种类型
 * @param alertType danger/warning/success
 * @param alertTitle
 * @param alertContent
 */
AlertBox.prototype.addNewAlert = function(alertType, alertTitle, alertContent){
    if(!alertTitle){
        alertTitle = this.defaultMap[alertType]['alertTitle'];
    }
    if(!alertContent){
        alertContent = this.defaultMap[alertType]['alertContent'];
    }
    this.container.innerHTML+=('<div id="' + alertType + 'Box" class="alert alert-' + alertType + '" role="alert">\n' +
        '    <a href="#" class="close" data-dismiss="alert"> &times;</a>\n' +
        '    <strong id="' + alertType + 'DetailTitle">' + alertTitle + '</strong>&nbsp;&nbsp;&nbsp;<span id="' + alertType + 'DetailContain">' + alertContent + '</span>\n' +
        '</div>')
    if(alertType === 'success'){
        setTimeout(function () {
            var successAlert = $('#' + alertType + 'Box');
            successAlert.hide(1000,function () {
                successAlert.remove();
            })
        },3000);

    }
};

AlertBox.prototype.clearAlert = function () {
    this.container.innerHTML = "";
};

function isExist(jqueryDom) {
    if(jqueryDom.length>=1){
        return true;
    }
    return false;
}

function kbiToMbi(kbi) {
    return (kbi/1024.0).toFixed(2);
}