package cn.edu.ruc.iir.wx.daoImpl;

import cn.edu.ruc.iir.wx.common.SysConfig;
import cn.edu.ruc.iir.wx.dao.InfoDaoI;
import cn.edu.ruc.iir.wx.domain.CmdResult;
import cn.edu.ruc.iir.wx.domain.TWx;
import cn.edu.ruc.iir.wx.util.ConfigFactory;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class InfoDaoImpl implements InfoDaoI {

    private static Logger log = Logger.getLogger(InfoDaoImpl.class);

    public CmdResult j = new CmdResult();

    @Override
    public void acExecuteSql(HttpServletRequest request) {

    }

    @Override
    public void acGetTable(HttpServletRequest request) {

    }

    @Override
    public String getResultJson() {
        return JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void acGetPageTable(HttpServletRequest request) {

    }

    @Override
    public void acGetDs(HttpServletRequest request) {

    }

    @Override
    public void acWxJsConfig(HttpServletRequest request) {
        String aUrl = request.getParameter("Url");
        TWx wx = TWx.Instance();
        Object aJson = wx.getJsConfig(aUrl);
        j.setDatas(aJson);
        j.setState(1);
    }

    @Override
    public void acWxDownMedia(HttpServletRequest request) {
        String aWxPs = request.getParameter("Ps");
        Map<String, String> aPs = (Map<String, String>) JSON.parse(aWxPs);
        String aSid = aPs.get("Sid");
        String aCate = aPs.get("Cate");
        ConfigFactory in = ConfigFactory.Instance();
        String aSrc = in.getProperty("mediaPath");
        String aPath = aSrc + "\\WxServer\\" + "upload" + "\\Res";
        String aToken = SysConfig.Token.getAccessToken();
        try {
            new TWxMediaDownload(aPath, aToken, aSid, aCate).Download();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        j.setState(1);
    }
}
