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
                        // ���ӳ�ʱ
                        conn.setConnectTimeout(25000);
                        // ��ȡ��ʱ --��������Ӧ�Ƚ���,����ʱ��
                        conn.setReadTimeout(25000);
                        HttpURLConnection.setFollowRedirects(true);
                        // ����ʽ
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
                        conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
                        conn.connect();

                        // ��ȡURLConnection�����Ӧ�������
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        // �����������
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
                                // �ر�����
                                conn.disconnect();
                        }

                } catch (Exception e) {e.printStackTrace();}
        }

        /**

         * @param args

         */

        public static void main(String[] args) {

                String s = "{\"button\":[{\"name\":\"�ҵ��˻�\",\"sub_button\":[{\"type\":\"click\",\"name\":\"�˻���\",\"key\":\"M1001\"},{\"type\":\"click\",\"name\":\"�ҵ��ʲ�\",\"key\":\"M1002\"}]},{\"type\":\"click\",\"name\":\"�ҵ��ʲ�\",\"key\":\"M2001\"},{\"type\":\"click\",\"name\":\"����\",\"key\":\"M3001\"}]}";
                String accessToken = "4IPESPYyv1vNrFZ9PPN8NYJByrC11n5cno4i5-MBIFF6XtuxP8eXU3f9YcTDeiKdcIm8kGxEbOxUNJZAuxK5vxIT3C0KfrM9SP3qB9yGWSs";// ���Լ���token
                createMenu(s,accessToken);

        }

}