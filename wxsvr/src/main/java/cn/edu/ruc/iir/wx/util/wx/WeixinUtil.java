package cn.edu.ruc.iir.wx.util.wx;

import cn.edu.ruc.iir.wx.domain.wx.AccessToken;
import cn.edu.ruc.iir.wx.domain.wx.Jsapi_Ticket;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WeixinUtil {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /**
     * @param url
     * @return JSONObject
     * @Title: doGetStr
     * @Description: get请求
     */
    public static JSONObject doGetStr(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);// 初始化
        JSONObject jsonObject = null;// 接受结果
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);// 接收执行的结果
            HttpEntity entity = httpResponse.getEntity();// 从消息体里拿结果
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");// 将结果转成字符串类型
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @param url
     * @param outStr
     * @return JSONObject
     * @Title: doPostStr
     * @Description: POST请求
     */
    public static JSONObject doPostStr(String url, String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            token.setAccessToken(jsonObject.getString("access_token"));
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    public static Jsapi_Ticket getTicket(String aToken) {
        Jsapi_Ticket ticket = new Jsapi_Ticket();
        String url = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", aToken);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            ticket.setErrcode(jsonObject.getInt("errcode"));
            ticket.setErrmsg(jsonObject.getString("errmsg"));
            ticket.setTicket(jsonObject.getString("ticket"));
            ticket.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return ticket;
    }


}