/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcurl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class TestCurl {

    /**
     * @param args the command line arguments
     */
    //"x-api-key","RAYliHV ~ H3 ~ hvTNYB-LCjbaSc6TgnBglWPnNLLa3"
    public static void main(String[] args) throws MalformedURLException, IOException {
        try {
            // TODO code application logic here
            URL obj = new URL("https://wsapi.simsimi.com/190410/talk");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", "Mozilla");
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("x-api-key","fRE84LxmAgatImNVsZxZ-.7hl1ehuJy7LBCZGRsC");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("{\"utext\":\"hi\", \"lang\":\"en\"}");
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader iny = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();
            
            while ((output = iny.readLine()) != null) {
                response.append(output);
            }
            iny.close();
            
            //printing result from response
            System.out.println(response.toString());
        } catch (ProtocolException ex) {
            Logger.getLogger(TestCurl.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
    
}
