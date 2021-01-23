package cn.tedu.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource ds;

    static {
        //创建属性配置对象
        Properties p = new Properties();
        //创建配置文件的输入流对象,通过类加载器得到resources目录下文件的输入流对象
        InputStream ips = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //把文件加载到属性对象中 异常抛出
            p.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = p.getProperty("db.driver");
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        //读取连接池相关数据
        String initSize = p.getProperty("db.initialSize");
        String maxSize = p.getProperty("db.maxActive");
        //创建连接池对象
        ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(Integer.parseInt(initSize));
        ds.setMaxActive(Integer.parseInt(maxSize));

    }

    public static Connection getConn() throws Exception {

        Connection conn = ds.getConnection();
        return conn;
    }

}
