package cn.edu.ruc.iir.wx.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Tao
 * @Package wx.utils
 * @ClassName: CheckUtil
 * @Description: 验证服务器地址的有效性
 * @date 2016年3月13日 上午11:25:45
 */
public class CheckUtil {

    private static Logger log = Logger.getLogger(CheckUtil.class.getName());

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        ConfigFactory in = ConfigFactory.Instance();
        String token = in.getProperty("token");
        String[] arr = new String[]{token, timestamp, nonce};
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // sha1加密
        String temp = getSha1(content.toString());
        log.info("timestamp ==== " + timestamp);
        log.info("nonce ==== " + nonce);
        log.info("signature ==== " + signature);
        log.info("temp ==== " + temp);
        return temp.equals(signature);
    }

    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        // 注意这里的数组千万不能出错，有过粗心
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
