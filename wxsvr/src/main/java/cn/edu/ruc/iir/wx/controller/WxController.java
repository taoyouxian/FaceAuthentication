package cn.edu.ruc.iir.wx.controller;

import cn.edu.ruc.iir.wx.service.WxService;
import cn.edu.ruc.iir.wx.util.CheckUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.wx.controller
 * @ClassName: WxController
 * @Description: Wx Access Controller
 * @author: taoyouxian
 * @date: Create in 2017-12-11 16:02
 **/
@CrossOrigin
@Controller
public class WxController {
    private static Logger log = LoggerFactory.getLogger(WxController.class);


    @Autowired
    private WxService wxService;

    public WxController()
    {

    }

    @RequestMapping({"/access"})
    public void access(HttpServletRequest req, HttpServletResponse resp) {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("echostr value: " + echostr);
            out.print(echostr);
        } else {
            log.info("Config Error.");
        }
    }


    @RequestMapping({"/process"})
    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        InputStream inputStream = req.getInputStream();
        // commons.io.jar 方法
        String postData = IOUtils.toString(inputStream, "UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = wxService.processRequest(postData);
        // 响应消息
        PrintWriter out = resp.getWriter();
        out.print(respMessage);
        out.close();
    }



}
