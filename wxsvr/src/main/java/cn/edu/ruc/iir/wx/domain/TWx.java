
package cn.edu.ruc.iir.wx.domain;

import cn.edu.ruc.iir.wx.common.SysConfig;
import cn.edu.ruc.iir.wx.util.ConfigFactory;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TWx {
    private static Logger log = Logger.getLogger(TWx.class.getName());

    private String srcID;
    private String appID;
    private String appsecret;
    private String token;
    private String accessToken;
    private String ticket;

    private static TWx instance = null;

    public static TWx Instance() {
        if (instance == null) {
            instance = new TWx();
        }
        return instance;
    }

    private TWx() {
        ConfigFactory in = ConfigFactory.Instance();
        instance.srcID = in.getProperty("srcID");
        instance.appID = in.getProperty("appID");
        instance.appsecret = in.getProperty("appsecret");
        instance.ticket = SysConfig.Jsapi_Ticket.getTicket();
    }

    public Map<String, String> getJsConfig(String aUrl) {
        Map<String, String> aConfig = new HashMap<String, String>();
        try {
            String timestamp = Long.toString(System.currentTimeMillis() / 1000);
            String noncestr = UUID.randomUUID().toString();
            int aPos = aUrl.indexOf('#');
            String aReqUrl = aPos > 0 ? aUrl.substring(0, aPos) : aUrl;
            String aSignatureSrc = "jsapi_ticket=" + instance.ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp
                    + "&url=" + aReqUrl;

            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(aSignatureSrc.getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest());
            aConfig.put("debug", "true");
            aConfig.put("timestamp", timestamp);
            aConfig.put("nonceStr", noncestr);
            aConfig.put("signature", signature);
            aConfig.put("appId", instance.appID);
        } catch (Exception er) {
            log.info(er.getMessage());
        }
        log.info(aConfig);
        return aConfig;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
