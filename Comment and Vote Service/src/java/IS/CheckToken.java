package IS;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author M. Fauzan Naufan
 */
public class CheckToken {
    public String checkToken(String token) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("http://localhost:8082/Identity_Service/TokenValidator");
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Cookie", "testCookie1="+token);   
            conn.connect();
            InputStream is = conn.getInputStream();
            sb = new StringBuilder();
            InputStreamReader isr = new InputStreamReader(is);
            int numCharsRead;
            char[] charArray = new char[1024];
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return sb.toString().substring(0, sb.length()-2);
    }
}
