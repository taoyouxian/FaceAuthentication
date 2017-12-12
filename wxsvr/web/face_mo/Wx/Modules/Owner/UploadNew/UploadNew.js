/// <reference path="../../../../../applibs/sdk/jQuery-2.1.3.min.js" />
/// <reference path="../../../../../applibs/sdk/baiduTpls.js" />
/// <reference path="../../../../../applibs/sdk/date.js" />
/// <reference path="../../../../../applibs/sdk/hhac.js" />
/// <reference path="../../../../../applibs/sdk/hhls.js" />
/// <reference path="../../../../../applibs/sdk/hhwxVoiceContent.js" />
/// <reference path="../../../../../applibs/sdk/json.js" />



var UploadNew = {
    Datas: {
        Info: {
            Medias: []
        },
        Imgs: []
    },
    Tpls: {
        tplImgs: { P: "Modules/Owner/UploadNew/tplImgs.htm", C: "" },
        tplKeywords: { P: "Modules/Owner/UploadNew/tplKeywords.htm", C: "" },
        tplVoices: { P: "Modules/Owner/UploadNew/tplVoices.htm", C: "" },
    },
    OnTakePhotos: function () {
        var me = UploadNew;
        try {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'],  // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var aLid = res.localIds[0]; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    wx.uploadImage({
                        localId: aLid, // 需要上传的图片的本地ID，由chooseImage接口获得
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res) {
                            try {
                                var serverId = res.serverId; // 返回图片的服务器端ID
                                var aImg = { Lid: aLid, Sid: serverId };
                                me.Datas.Imgs.push(aImg);
                                me.RefreshTakePhotos();
                                Ac.acWxDownMedia({ Sid: serverId, Cate: "i" }, function (aRes1) {
                                    ;
                                });
                            }
                            catch (e1) {
                                alert(e1);
                            }
                        }
                    });
                }
            });
        }
        catch (e) {; }
    },
    RefreshTakePhotos: function () {
        var me = UploadNew;
        try {
            var aHtml = '<li class="item" style="padding:5px; padding-left:20px"> 还没有拍摄照片。 </li>';
            if (me.Datas.Imgs.length > 0) {
                aHtml = bt(me.Tpls.tplImgs.C, { tplData: me.Datas.Imgs });
            }
            hhls.fillElement("#lstImgs", aHtml);
        }
        catch (e) {; }
    },
    RemoveImg: function (aIndex) {
        var me = UploadNew;
        try {
            me.Datas.Imgs.splice(aIndex, 1);
            me.RefreshTakePhotos();
        }
        catch (cer) {; }
    },
}