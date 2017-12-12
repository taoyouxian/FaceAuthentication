
package cn.edu.ruc.iir.wx.daoImpl;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TiIo {
    private static Logger log = Logger.getLogger(TiIo.class.getName());

    public static boolean downloadFile(String aUrl, String aTargetFn, boolean isVideo, boolean isThumbPic) {
        boolean flagDown = false;

        String requestUrl = null;
        if (isVideo) {
            // 视频
            requestUrl = aUrl.replace("https", "http");
        } else {
            requestUrl = aUrl;
        }

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            String filePath = "";

            filePath = aTargetFn.substring(0, aTargetFn.lastIndexOf("\\"));
            // 创建文件目录
            File file = new File(filePath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(conn.getInputStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(aTargetFn));
            byte[] buf = new byte[8096];
            int size = 0;

            while ((size = bufferedInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, size);
            }
            fileOutputStream.close();
            bufferedInputStream.close();

            conn.disconnect();

            flagDown = true;

        } catch (ConnectException e) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("Download Media failed!");
        }

        return flagDown;
    }

}
