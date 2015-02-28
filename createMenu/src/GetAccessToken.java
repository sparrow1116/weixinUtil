import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetAccessToken {
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String  grant_type  = "client_credential";
		String  appid = "wxa287cb3b1e5c9524";
		String  secret = "a3719a6a8b4cdee75bcf7541b5dd8fd0 ";
		String accessToken = getToken(grant_type,appid,secret);
		System.out.println("the accesstoken is:"+accessToken);
	}

	private static String getToken(String grant_type, String appid,
			String secret) {
		StringBuffer bufferRes = new StringBuffer();

        try {
//        							   https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
                URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type="+ grant_type+"&appid=" +appid + "&secret="+secret);
                					   
                HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
                // ���ӳ�ʱ
                conn.setConnectTimeout(25000);
                // ��ȡ��ʱ --��������Ӧ�Ƚ���,����ʱ��
                conn.setReadTimeout(25000);
                HttpURLConnection.setFollowRedirects(true);
                // ����ʽ
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
                conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
                conn.connect();

//                // ��ȡURLConnection�����Ӧ�������
//                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
//                // �����������
//                //out.write(URLEncoder.encode(params,"UTF-8"));
//                out.write(params);
//                out.flush();
//                out.close();
                InputStream in = conn.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));

                String valueString = null;
                while ((valueString=read.readLine())!=null){
                        bufferRes.append(valueString);
                }   
                in.close();
                if (conn != null) {
                        // �ر�����
                        conn.disconnect();
                }
                return bufferRes.toString();

        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }

	}

}
