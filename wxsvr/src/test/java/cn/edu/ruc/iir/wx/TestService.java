
package cn.edu.ruc.iir.wx;

import cn.edu.xajd.dl.util.ConfigFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestService {

    private static Logger log = Logger.getLogger(TestService.class);

    @Test
    public void getUser() {
        ConfigFactory in = ConfigFactory.Instance();
        String prop = in.getProperty("token");
        System.out.println(prop);
        assertEquals(prop, "weixin");
    }
}
