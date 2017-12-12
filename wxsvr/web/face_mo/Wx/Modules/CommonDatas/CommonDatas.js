/// <reference path="../../../../../Libs/sdk/jQuery-2.1.3.min.js" />
/// <reference path="../../../../../Libs/sdk/hhac.js" />
/// <reference path="../../../../../Libs/sdk/hhls.js" />
/// <reference path="../../../../../Libs/sdk/hhls_wxConfirm.js" />
/// <reference path="../../../../../Libs/sdk/hhsWx.js" />
/// <reference path="../../../../../Libs/sdk/hhwxMsg.js" />
/// <reference path="../../../../../Libs/sdk/hhwxVoiceContent.js" />
/// <reference path="../../../../../Libs/sdk/baiduTpls.js" /> 
/// <reference path="../../../Commons/Init.js" />


var CommonDatas = {
    Datas: {
        TaskDetailKey: "ICommunity.TaskDetail", 
        OpenID: "",//仅仅是地址栏的,不一定是用户的，在UserInfo里面
        WxInfo: null,
        UserInfo: null
    },
    Path: {
        CheckLogin: "Apps/ICommunity/Wx/Account/UserInfo.txt",
        CheckLoginByCodePwd: "Apps/ICommunity/Wx/Login/CheckLoginByCodePwd.txt",
        BindWx: "Apps/ICommunity/Wx/Login/BindWx.txt",
        BindCheck: "Apps/ICommunity/Wx/Login/BindCheck.txt",
    },
    CheckUserInfo: function (aCallback) {
        var me = CommonDatas;
        try {
            if (me.Datas.OpenID != "") {
                Ac.acGetTable(me.Path.CheckLogin, { OpenID: me.Datas.OpenID }, function (aRes) {
                    me.Datas.UserInfo = null;
                    try {
                        if (aRes.Datas.length > 0) {
                            me.Datas.UserInfo = aRes.Datas[0];
                        }
                    }
                    catch (ee) {; }
                    hhls.callBack(aCallback, me.Datas.UserInfo);
                });
            }
            else {
                me.Datas.UserInfo = null;
                hhls.callBack(aCallback, me.Datas.UserInfo);
            }
        }
        catch (e) {; }
    },
    Bind: function (aCode, aPwd, aCallback) {
        var me = CommonDatas;
        try {

            Ac.acGetTable(me.Path.CheckLoginByCodePwd, { Code: aCode, Pwd: aPwd }, function (aRes) {
                try {
                    if (aRes.Datas.length > 0) {
                        var aUserID = aRes.Datas[0].F_ID;
                        Ac.acGetTable(me.Path.BindCheck, { OpenID: me.Datas.OpenID }, function (aRes) {
                            if (aRes.Datas.length == 0 && aRes.State == 1) {
                                Ac.acExecuteSql(me.Path.BindWx, { OpenID: me.Datas.OpenID, UserID: aUserID }, function (aRes) {
                                    if (aRes.State == 1) {
                                        hhls.callBack(aCallback, "OK");
                                    }
                                    else {
                                        hhls.callBack(aCallback, "Error");
                                    }
                                }); 
                            }
                            else {
                                hhls.callBack(aCallback, "DoubleBindError");
                            }
                        }); 
                    }
                    else {
                        hhls.callBack(aCallback, "NoneError");
                    }
                }
                catch (ee) {; }
            });
        }
        catch (e) {; }
    },
    UnBind: function (aCallback) {
        var me = CommonDatas;
        try {
            Ac.acExecuteSql(me.Path.UnBind, { OpenID: me.Datas.OpenID, UserID: me.Datas.UserInfo.F_ID }, function (aRes) {
                if (aRes.State == 1) {
                    hhls.callBack(aCallback, "OK");
                }
                else {
                    hhls.callBack(aCallback, "Error");
                }
            });
        }
        catch (e) {; }
    },
    GetUserWxInfo: function (aCallback) {
        var me = CommonDatas;
        try {
            Ac.acWxGetUsersInfo([me.Datas.OpenID], function (aResWxInfo) {
                var aWxUserInfoList = hhls.getJsonObj(aResWxInfo.Datas).user_info_list;
                if (aWxUserInfoList.length > 0) {
                    me.Datas.WxInfo = aWxUserInfoList[0];
                }
                hhls.callBack(aCallback);
            });
        }
        catch (e) {; }
    },
    LoadWxImg: function () {
        var me = CommonDatas;
        try { 
            var aImgs = $(".WxImg");
            $.each(aImgs, function (aInd, aItem) {
                try {
                    var aImg = $(aItem);
                    var aKey = aImg.attr("Key");
                    if (aKey.length > 0) {
                        var aUrl = Init.Datas.MediaPath + Ac.Info.WxSrc + "/" + aKey + ".jpg";
                        aImg.attr("src", aUrl);
                    }
                }
                catch (ee) {; }
            });
        }
        catch (e) {; }
    }
};