package cn.edu.ruc.iir.wx;

import cn.edu.ruc.iir.wx.util.CheckUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.wx
 * @ClassName: TestUtil
 * @Description: tset util
 * @author: taoyouxian
 * @date: Create in 2017-12-11 16:28
 **/
public class TestUtil {

    @Test
    public void TestCheckSignature() {
        String signature = "1";
        String timestamp = "1";
        String nonce = "1";
        boolean flag = CheckUtil.checkSignature(signature, timestamp, nonce);
        assertEquals(flag, false);
    }
}
