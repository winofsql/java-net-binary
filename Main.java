import java.net.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        byte[] buffer = new byte[4096];
        int readByte = 0;

        try {
            // ターゲット( png 画像 )
            URL url = new URL("https://winofsql.jp/image/a/xampp_tomcat7.png");
            // 接続オブジェクト
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            // GET メソッド 
            http.setRequestMethod("GET");
            // 接続 
            http.connect();
            
            // 出力用
            DataOutputStream dataOutStream =
                new DataOutputStream( new BufferedOutputStream( new FileOutputStream("sample.png") ) );			

            // バイナリデータ読み込み用
            BufferedInputStream bis = new BufferedInputStream( http.getInputStream() );   
            // 読み込む為の準備   
            DataInputStream dis = new DataInputStream(bis);   

            while ( -1 != (readByte = dis.read(buffer) ) ) {   
                // 出力
                dataOutStream.write(buffer, 0, readByte);
            }

            // 閉じる   
            dis.close();
            bis.close();
            dataOutStream.close();

            // 接続解除
            http.disconnect();
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
}
