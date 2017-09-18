
/**
*	feature: 保存类弹窗模板
*	@parameter:{
*		header:定义弹窗title
*		content:定义body内容
*	}		
*	example：
*		function openSavelayer() {
*           var layerParameter = {
*				 header: "信息输入"
*               content: '<div style="text-align:center;font-size:20px;"><strong>文档输出成功</strong></div>'
*           };
*           var openlayer = new OpenSaveModalLayer();
*           openlayer.OpenSaveModalLayer(layerParameter.header,layerParameter.content);
*       }
*/

function OpenModalLayer() {

}

OpenModalLayer.prototype = {
    OpenModalLayer: function (header, content, footer, endFunction) {
        //参数初始化
        var header = header || '';
        var content = content || '';
        var footer = footer || '';
        var endFunction = endFunction || function () { };
        parent.layer.open({
            id: 1,
            offset: '20%',
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: true, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content" style="border-radius:0px;">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" onclick="closeLayer(event);">&times;</button>' +
                    '<h4 class="modal-title topTitle">' + header + '</h4>' +
                    '</div>' +
                    '<div class="modal-body">' + content +
                    '</div>' +
                    '<div class="modal-footer">' + footer +
                    '</div>' +
                    '</div>',
            end: endFunction
        });
    },
    OpenSaveModalLayer: function (header, content) {
        parent.layer.open({
            id: 1,
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: true, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" onclick="layer.closeAll();">&times;</button>' +
            '<h4 class="modal-title topTitle">' + header + '</h4>' +
            '</div>' +
            '<div class="modal-body">' + content +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default CloseLayer" onclick="layer.closeAll();">关闭</button>' +
            '<button type="submit" id="btnSubmitData" class="btn btn-primary" >保存</button>' +
            '</div>' +
            '</div>'
        });
    },
    OpenConfirmModalLayer: function (header, content) {
        parent.layer.open({
            id: 1,
            anim: 0,//动画
            title: false,//标题
            type: 1,//页面层
            closeBtn: 0,//关闭按钮
            area: '600px',//宽高
            skin: 'layui-layer-rim',//风格
            shade: 0.6,//遮罩层透明度
            shadeClose: true, //点击遮罩关闭
            resize: false,//拉伸
            move: false,//拖动
            content: '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" onclick="layer.closeAll();">&times;</button>' +
                    '<h4 class="modal-title topTitle">' + header + '</h4>' +
                    '</div>' +
                    '<div class="modal-body">' + content +
                    '</div>' +
                    '<div class="modal-footer">' +
                    '<button type="button" class="btn btn-default" onclick="layer.closeAll();">否</button>' +
                    '<button type="button" id="btnConfirm" class="btn btn-default">是</button>' +
                    '</div>' +
                    '</div>'
        })
    },
    AlertMessage: function (message) {
        let self = this;
        parent.layer.alert(message, {
            icon: 1,
            skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
        });
    },
    CloseLayer: function () {
        parent.layer.closeAll();
    }
}




