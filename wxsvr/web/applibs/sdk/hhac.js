/// <reference path="../plugins/jQuery/jQuery-2.1.3.min.js" />
/// <reference path="json.js" />
/// <reference path="baiduTpls.js" />
var Ac = {
    Info: {
        SvcUrl: "",
        DBKey: "",
        WxSrc: ""
    },
    Call_Get: function (aAction, aPs, aCallback) {
        try {
            var aUrl = Ac.Info.SvcUrl;
            aUrl += Ac.Info.SvcUrl.indexOf("?") > 0 ? "&" : "?";
            aUrl += "randomtm=" + Math.random();
            aUrl += "&Action=" + aAction;
            var aPostParas = hhls.getClone(aPs);
            for (p in Ac.Info) {
                aPostParas[p] = Ac.Info[p];
            }
            $.ajax({
                url: aUrl,
                data: aPostParas,
                type: 'POST',
                dataType: 'json',
                jsonp: 'callback',
                timeout: 50000,
                success: function (aCallbackData, b, c) {
                    try {
                        hhls.callBack(aCallback, aCallbackData);
                    }
                    catch (Ex) {; }
                },
                error: function (a, b, c) {
                    var aResult = { State: 0, Datas: { Ea: a, Eb: b, Ec: c } };
                    hhls.callBack(aCallback, aResult);
                }
            });
        }
        catch (E) {; }
    },
    acWxGetJsConfig: function (aCallback) {
        try {
            var aPostPs = {
                SrcID: Ac.Info.WxSrc,//可不用
                Url: window.location.href
            };
            Ac.Call_Get("acGetWxJsConfig", aPostPs, function (aRes) {
                hhls.callBack(aCallback, aRes);
            });
        }
        catch (E) {
            alert(E);
        }
    },
    acWxInit: function () {
        try {
            Ac.acWxGetJsConfig(function (aRes) { 
                var aConfig = aRes.Datas;
                aConfig.debug = false;
                aConfig.jsApiList = [
                    "onMenuShareTimeline",
                    "onMenuShareAppMessage",
                    "onMenuShareQQ",
                    "onMenuShareWeibo",
                    "onMenuShareQZone",
                    "startRecord",
                    "stopRecord",
                    "onVoiceRecordEnd",
                    "playVoice",
                    "pauseVoice",
                    "stopVoice",
                    "onVoicePlayEnd",
                    "uploadVoice",
                    "downloadVoice",
                    "chooseImage",
                    "previewImage",
                    "uploadImage",
                    "downloadImage",
                    "translateVoice",
                    "getNetworkType",
                    "openLocation",
                    "getLocation",
                    "hideOptionMenu",
                    "showOptionMenu",
                    "hideMenuItems",
                    "showMenuItems",
                    "hideAllNonBaseMenuItem",
                    "showAllNonBaseMenuItem",
                    "closeWindow",
                    "scanQRCode",
                    "chooseWXPay",
                    "openProductSpecificView",
                    "addCard",
                    "chooseCard",
                    "openCard"
                ];
                wx.config(aConfig);
            });
        }
        catch (E) {
            alert(E);
        }
    },
    /*
        提交微信素材
    */
    acWxDownMedia: function (aMediaIDs, aCallback) {
        try {
            var aInfo = {
                SrcID: Ac.Info.WxSrc,
                MediaIDs: hhls.getObjJson(aMediaIDs)
            };
            Ac.Call_Get("acWxDownloadVoice", aInfo, aCallback);
        }
        catch (e) {
            alert(e.Message);
        }
    },

};
 