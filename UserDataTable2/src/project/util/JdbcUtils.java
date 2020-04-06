package project.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Created by ZIYANG on 2020/2/16.
 * Druid连接池的工具类
 */
public class JdbcUtils {
    // 1、定义成员变量 DataSource
    private static javax.sql.DataSource ds;


    static {
        try {
            // 2、加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtils.class.getResourceAsStream("druid.properties"));


            // 3、获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 3、获取连接的方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    // 4、释放资源
    public static void close(Statement statement, Connection connection) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    // 5、获取连接池的方法
    public static javax.sql.DataSource getDataSource() {
        return ds;
    }
}