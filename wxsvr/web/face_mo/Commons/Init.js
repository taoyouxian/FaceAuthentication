/// <reference path="../../Libs/sdk/jQuery-2.1.3.min.js" />

/// <reference path="../../Libs/sdk/json.js" />

/// <reference path="../../Libs/sdk/baiduTpls.js" />

/// <reference path="../../Libs/sdk/date.js" />

/// <reference path="../../Libs/sdk/hhls.js" />

/// <reference path="../../Libs/sdk/hhac.js" />


function doInitSys() {
    //Ac.Info.SvcUrl = "http://127.0.0.1:8080/BT_DataPro/SvrServlet";
    Ac.Info.SvcUrl = "http://ghosttao.xicp.net/BT_DataPro/SvrServlet";
    Ac.Info.Ak = "5055d4bec610439fb83174155446ddb0";
    Ac.Info.Sk = "2bbc70e9dd097c699dbd5ba453e8f8e6";
    Ac.Info.AppCode = "ICommunity";
    Ac.Info.DBKey = "BT_Wx";
    Ac.Info.WxSrc = "gh_f9d1fbe07ce7";
}
 


var Init = {
    Datas: {
        MediaPath: "http://ghosttao.xicp.net/WxServer/WxMedias/",
        MsgUrl: "http://ghosttao.xicp.net/Apps/webs/ICommunity/Wx/",
        WxTargetUrl: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5db60f482f783e3e&redirect_uri=http%3A%2F%2Fghosttao.xicp.net%2FBT_DataPro%2FWxRoleTarget&response_type=code&scope=snsapi_base&state=gh_f9d1fbe07ce7_ICommunity#wechat_redirect",
    },
    Path: {
         
    }, 
    Utility: {
        WxToast: "<div id=\"wxToast\">"
                    + "<div class=\"wx_transparent\"></div>"
                    + "<div class=\"wx-toast\">"
                        + "<div class=\"sk-spinner sk-spinner-three-bounce\">"
                            + "<div class=\"sk-bounce1\"></div>"
                            + "<div class=\"sk-bounce2\"></div>"
                            + "<div class=\"sk-bounce3\"></div>"
                        + "</div>"
                        + "<p class=\"wx-toast_content\">数据加载中</p>"
                    + "</div>"
                + "</div>",
        WebToast: "<div id=\"webToast\">"
                    + "<div class=\"web_transparent\"></div>"
                    + "<div class=\"web-toast\">"
                        + "<div class=\"sk-spinner sk-spinner-three-bounce\">"
                            + "<div class=\"sk-bounce1\"></div>"
                            + "<div class=\"sk-bounce2\"></div>"
                            + "<div class=\"sk-bounce3\"></div>"
                        + "</div>"
                        + "<p class=\"web-toast_content\">数据加载中</p>"
                    + "</div>"
                + "</div>",
    },
    WebToast: function () {
        var me = Init;
        try {
            $("body").append(me.Utility.WebToast);
            var w = $(window).width();
            var aW = $(".web-toast").width();
            var left = (w - aW) / 2;
            $(".web-toast").css("left", left + "px");
        }
        catch (e) {; }
    },
    WxToast: function () {
        var me = Init;
        try {
            $("body").append(me.Utility.WxToast);
            var w = $(window).width();
            var aW = $(".wx-toast").width();
            var left = (w - aW) / 2;
            $(".wx-toast").css("left", left + "px");
        }
        catch (e) {; }
    }
}
