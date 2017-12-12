package cn.edu.ruc.iir.wx.serviceImpl;

import cn.edu.ruc.iir.wx.common.SysConfig;
import cn.edu.ruc.iir.wx.domain.wx.AccessToken;
import cn.edu.ruc.iir.wx.service.InitServiceI;
import cn.edu.ruc.iir.wx.util.ConfigFactory;
import cn.edu.ruc.iir.wx.util.FileUtil;
import cn.edu.ruc.iir.wx.util.wx.WeixinUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service("demoInitService")
public class InitServiceImpl implements InitServiceI {

    private static Logger log = LoggerFactory.getLogger(InitServiceImpl.class);

    @SuppressWarnings("unchecked")
    synchronized public void init() throws IOException {
        ConfigFactory config = ConfigFactory.Instance();
        String path = config.getProperty("tokenPath");
        SysConfig.TokenPath = path;
        String aJson = FileUtil.readFile(SysConfig.TokenPath);
        // 启动判断是否过期
        if (aJson == "" || aJson == null) {
            initWxInfo(config.getProperty("tokenPath"), config.getProperty("tokenPath"));
        } else {
            SysConfig.Token = (AccessToken) JSON.parse(aJson);
        }
    }

    private void initWxInfo(String appID, String appsecret) {
        SysConfig.Token = WeixinUtil.getAccessToken(appID, appsecret);
        SysConfig.Jsapi_Ticket = WeixinUtil.getTicket(SysConfig.Token.getAccessToken());
        try {
            if (null != SysConfig.Token) {
                String aJson = JSON.toJSONString(SysConfig.Token);

                FileUtil.writeFile(aJson, SysConfig.TokenPath);
                log.info("获取access_token成功，有效时长{}秒  toke:{}" + SysConfig.Token.getExpiresIn()
                        + SysConfig.Token.getAccessToken());
                // 休眠expires_in-200秒；
//                Thread.sleep((SysConfig.Token.getExpiresIn() - 200) * 1000);
            } else {
                // 如果access_token为null，60秒后再获取
//                Thread.sleep(60 * 1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * run after web closed
     */
    @PreDestroy
    public void applicationEnd() {

    }
}
