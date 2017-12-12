package cn.edu.ruc.iir.wx.daoImpl;

import org.apache.log4j.Logger;

public class TWxMediaDownload {
    private static Logger log = Logger.getLogger(TWxMediaDownload.class
            .getName());
    public String Path = "";
    public String AccessToken = "";
    public String Sid = "";
    public String Cate = "";

    public TWxMediaDownload() {
    }

    public TWxMediaDownload(String path, String accessToken, String sid,
                            String cate) {
        super();
        Path = path;
        AccessToken = accessToken;
        Sid = sid;
        Cate = cate;
    }

    public void Download() {
        DowloadProc();
    }

    private void DowloadProc() {
        try {
            String aUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="
                    + AccessToken + "&media_id=" + Sid;
            String aFn = Path + "\\" + Sid
                    + (Cate.equals("i") ? ".jpg" : ".amr").toString();
            boolean aFlag = TiIo.downloadFile(aUrl, aFn, false, false);
            if (aFlag && Cate.equals("v")) {
                String aMp3Fn = aFn.replace(".amr", ".mp3");
                // ConvertMp3Cmd(aFn, aMp3Fn);
            }
        } catch (Exception er) {
        }
    }


}
