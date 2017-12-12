package cn.edu.ruc.iir.wx.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version V1.0
 * @Package: cn.edu.xajd.dl.util
 * @ClassName: ConfigFactory
 * @Description: read data.properties
 * @author: Tao
 * @date: Create in 2017-08-12 17:35
 **/
public class ConfigFactory {

    private static ConfigFactory instance = null;
    private static Properties prop = null;

    public static ConfigFactory Instance() {
        if (instance == null) {
            instance = new ConfigFactory();
        }
        return instance;
    }

    private ConfigFactory() {
        prop = new Properties();
        InputStream in = this.getClass().getResourceAsStream("/wx.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadProperties(String propFilePath) {
        FileInputStream in = null;
        try {
            new FileInputStream(propFilePath);
            this.prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addProperty(String key, String value) {
        this.prop.setProperty(key, value);
    }

    public String getProperty(String key) {
        return this.prop.getProperty(key);
    }

    public String getPort() {
        return this.prop.getProperty("port");
    }
}
