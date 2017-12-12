package cn.edu.ruc.iir.wx.controller;

import cn.edu.ruc.iir.wx.common.ActionType;
import cn.edu.ruc.iir.wx.dao.InfoDaoI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@CrossOrigin
@Controller
public class WxBasicAPI {
    private static Logger log = LoggerFactory.getLogger(WxBasicAPI.class);

    public WxBasicAPI() {

    }

    @Autowired
    private InfoDaoI dData;

    @CrossOrigin
    @RequestMapping(value = "/wxProcess", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String wxProcess(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException, SQLException {
        String aAction = request.getParameter("Action");
        ActionType aActionType = ActionType.valueOf(aAction);
        String aJson = "";
        try {
            switch (aActionType) {
                case acGetWxJsConfig:
                    dData.acWxJsConfig(request);
                    break;
                case acWxDownMedia:
                    dData.acWxDownMedia(request);
                    break;
                // not complete
                case acExecuteSql:
                    dData.acExecuteSql(request);
                    break;
                case acGetTable:
                    dData.acGetTable(request);
                    break;
                case acGetDs:
                    dData.acGetDs(request);
                    break;
                case acGetPageTable:
                    dData.acGetPageTable(request);
                    break;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        aJson = dData.getResultJson();
        return aJson;
    }

}
