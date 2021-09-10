package cn.codepai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取当前工程的公网IP
 */
public class IPAddrUtil {
    public static String getV4IP(){
        try {
            // 从太平洋官网获取当前机器ip
            String path = "http://whois.pconline.com.cn/ipJson.jsp";// 要获得html页面内容的地址

            URL url = new URL(path);// 创建url对象

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接

            conn.setRequestProperty("contentType", "GBK"); // 设置url中文参数编码

            conn.setConnectTimeout(5 * 1000);// 请求的时间

            conn.setRequestMethod("GET");// 请求方式

            InputStream inStream = conn.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    inStream, "GBK"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            // 读取获取到内容的最后一行,写入
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String str = buffer.toString();
            // 从html中截取IP
            String ip = str.substring(str.indexOf("\"ip\":\"") + 6,
                    str.lastIndexOf("\",\"pro\""));

            System.out.println("获取到IP地址为："+ip);
            // 返回公网IP值
            return ip;

        } catch (Exception e) {
            System.out.println("获取公网IP连接超时");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getV4IP());
    }
}
