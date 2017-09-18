var MyAjax = function () {
    this.ajaxLock = false;
}

MyAjax.prototype = {
    onAjaxGet: function (options, callback) {
        if (typeof options === "undefined") {
            throw "param is undefined!"
        }
        if (this.ajaxLock) {
            $.alert("抱歉", "系统正在处理前一次请求，请耐心等待...");
        } else {
            //加锁
            this.ajaxLock = true;
            $.ajax({
                type: "GET",
                url: options.url,
                async: options.async === undefined ? true : options.async,       //是否异步
                data: options.data,         //要发送的数据
                dataType: "json",       //使用JSONP方法进行AJAX,json有跨域问题；
                success: function (data) {
                    if (!options.fullData) {
                        callback(data.data);
                    } else {
                        callback(data);
                    }
                },
                //增加错误处理
                error: function (jqXHR, textStatus, errorThrown) {
                    switch (jqXHR.status) {
                        case (500):
                            $.alert("服务器系统内部错误!");
                            break;
                        case (401):
                            $.alert("未登录!");
                            break;
                        case (403):
                            $.alert("无权限执行此操作!");
                            break;
                        case (404):
                            $.alert("Not Found!");
                            break;
                        case (408):
                            $.alert("请求超时!");
                            break;
                        default:
                            $.alert("未知错误:" + jqXHR.status + textStatus);
                    }
                }
            });
            //解锁
            this.ajaxLock = false;
        }
    },

    onAjaxPost: function (options, callback) {
        if (typeof options === "undefined") {
            throw "param is undefined!"
        }
        if (this.ajaxLock) {
            $.alert("抱歉", "系统正在处理前一次请求，请耐心等待...");
        } else {
            //加锁
            this.ajaxLock = true;
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: options.url,
                async: options.async === undefined ? true : options.async,       //是否异步
                data: JSON.stringify(options.data),         //要发送的数据
                dataType: "json",       //使用JSONP方法进行AJAX,json有跨域问题；
                success: function (data, status) {
                    callback(data);
                },
                //增加错误处理
                error: function (jqXHR, textStatus, errorThrown) {
                    switch (jqXHR.status) {
                        case (500):
                            $.alert("服务器系统内部错误!");
                            break;
                        case (401):
                            $.alert("未登录!");
                            break;
                        case (403):
                            $.alert("无权限执行此操作!");
                            break;
                        case (404):
                            $.alert("Not Found!");
                            break;
                        case (408):
                            $.alert("请求超时!");
                            break;
                        default:
                            $.alert("未知错误!");
                    }
                }
            });
            //解锁
            this.ajaxLock = false;
        }
    }
}