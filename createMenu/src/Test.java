import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

        public static void createMenu(String params,String accessToken) {

                StringBuffer bufferRes = new StringBuffer();

                try {
                        URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ accessToken);
                        					   
                        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
                        // 连接超时
                        conn.setConnectTimeout(25000);
                        // 读取超时 --服务器响应比较慢,增大时间
                        conn.setReadTimeout(25000);
                        HttpURLConnection.setFollowRedirects(true);
                        // 请求方式
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
                        conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
                        conn.connect();

                        // 获取URLConnection对象对应的输出流
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        // 发送请求参数
                        //out.write(URLEncoder.encode(params,"UTF-8"));
                        out.write(params);
                        out.flush();
                        out.close();
                        InputStream in = conn.getInputStream();
                        BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));

                        String valueString = null;
                        while ((valueString=read.readLine())!=null){
                                bufferRes.append(valueString);
                        }
                        System.out.println(bufferRes.toString());
                        in.close();
                        if (conn != null) {
                                // 关闭连接
                                conn.disconnect();
                        }

                } catch (Exception e) {e.printStackTrace();}
        }

        /**

         * @param args

         */

        public static void main(String[] args) {

                String s = "{\"button\":[{\"name\":\"我的账户\",\"sub_button\":[{\"type\":\"click\",\"name\":\"账户绑定\",\"key\":\"M1001\"},{\"type\":\"click\",\"name\":\"我的资产\",\"key\":\"M1002\"}]},{\"type\":\"click\",\"name\":\"我的资产\",\"key\":\"M2001\"},{\"type\":\"click\",\"name\":\"其它\",\"key\":\"M3001\"}]}";
                String accessToken = "4IPESPYyv1vNrFZ9PPN8NYJByrC11n5cno4i5-MBIFF6XtuxP8eXU3f9YcTDeiKdcIm8kGxEbOxUNJZAuxK5vxIT3C0KfrM9SP3qB9yGWSs";// 你自己的token
                createMenu(s,accessToken);

        }

}