package cn.edu.ruc.iir.wx.service;


import cn.edu.ruc.iir.wx.util.DateUtil;
import cn.edu.ruc.iir.wx.util.wx.msg.MessageUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.wx.service
 * @ClassName: WxService
 * @Description: Wx Service
 * @author: taoyouxian
 * @date: Create in 2017-12-11 16:58
 **/
public class WxService {

    private static Logger log = Logger.getLogger(WxService.class.getName());

    public String processRequest(String request) {
        String respMessage = "";
        String accessToken = null;

        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXML(request);
            // 开发者微信号(即公众号)
            String ToUserName = requestMap.get("ToUserName");
            // 发送方账号 (open_id)
            String FromUserName = requestMap.get("FromUserName");
            // 消息创建时间
            String CreateTime = DateUtil.formatTime(Long.parseLong(requestMap
                    .get("CreateTime")) * 1000L);
            // 消息类型
            String MsgType = requestMap.get("MsgType");
            // 从数据库中读取AccessToken
            accessToken = null;

        } catch (Exception e) {

        }
        return respMessage;

    }
}
