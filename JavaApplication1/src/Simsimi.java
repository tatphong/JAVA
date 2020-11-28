
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tattr
 */
public class Simsimi {
    URL obj;
    HttpURLConnection con;

    public Simsimi() throws MalformedURLException, IOException {
        this.obj = new URL("https://wsapi.simsimi.com/190410/talk");
        this.con = (HttpURLConnection) obj.openConnection();
        
//        Set property for connection
        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", "Mozilla");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("x-api-key","fRE84LxmAgatImNVsZxZ-.7hl1ehuJy7LBCZGRsC");
        con.setDoOutput(true);
    }
    
    public String format_response(String output){
        String res = "format return null";
        try {
            JSONObject obj = new JSONObject(output);
            return obj.getString("atext");
        } catch (JSONException ex) {
            Logger.getLogger("format error"+Simsimi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String run(String input) throws MalformedURLException{
    try {
            // TODO code application logic here
//            Create output stream to simsimi
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("{\"utext\":\"" + input + "\", \"lang\":\"en\"}");
            wr.flush();
            wr.close();
            
//            get response from simsimi
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader iny = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();
            
            while ((output = iny.readLine()) != null) {
                response.append(output);
            }
            iny.close();
            
            return format_response(response.toString());
        } catch (IOException ex) {
            Logger.getLogger(Simsimi.class.getName()).log(Level.SEVERE, null, ex);
            return "DataStream, IOException";
        }
    }
}
