/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iqair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.*;
import org.json.*;
import org.jsoup.nodes.Document;

/**
 *
 * @author tattr
 */
public class Iqair {
    static DatagramSocket socket;
    static DatagramPacket dpreceive, dpsend;
    static String api_key = "02f2e16c-9644-4f0f-b59f-b2926b838a03";
    static String url = "https://api.airvisual.com/v2/";
    static String user_agent = "Chrome";
    /**
     * @param args the command line arguments
     */
    /*
    public static void get_data(){
        try {
            // TODO code application logic here
            URL obj = new URL("https://api.airvisual.com/v2/countries");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            con.setRequestMethod("GET");
//            con.setRequestProperty("","application/json");
            con.setRequestProperty("key","02f2e16c-9644-4f0f-b59f-b2926b838a03");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes("{\"utext\":\"hello\", \"lang\":\"en\"}");
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
            Logger.getLogger(Iqair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Iqair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Iqair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public static String format_json(String s, String type) throws JSONException{
        String res=type+": ";
        System.out.println(type);
        JSONObject obj = new JSONObject(s);
        JSONArray data = obj.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            String item = data.getJSONObject(i).getString(type);
            res+= item + ", ";
        }
        return res.substring(0,res.length()-2);
    }
    
    public static String get_country() throws IOException, JSONException{
        String res;
        Document doc = Jsoup.connect(url+"countries?key="+api_key).userAgent(user_agent).ignoreContentType(true).get();  
        res = doc.body().text();
        return format_json(res, "country");
    }
    
    public static String get_state(String country) throws IOException, JSONException{
        String res;
        Document doc = Jsoup.connect(url+"states?country="+country+"&key="+api_key).userAgent(user_agent).ignoreContentType(true).get();  
        res = doc.body().text();
        return format_json(res, "state");
    }
    
    public static String get_city(String country, String state) throws IOException, JSONException{
        String res;
        Document doc = Jsoup.connect(url+"cities?state="+state+"&country="+country+"&key="+api_key).userAgent(user_agent).ignoreContentType(true).get();  
        res = doc.body().text();
        return format_json(res, "city");
    }
    
    public static String get_air(String country, String state, String city) throws IOException, JSONException{
        String res;
        Document doc = Jsoup.connect(url+"city?city="+city+"&state="+state+"&country="+country+"&key="+api_key).userAgent(user_agent).ignoreContentType(true).get();  
        res = doc.body().text();
        JSONObject obj = new JSONObject(res);
        return "Pollution Aqius: " + obj.getJSONObject("data").getJSONObject("current").getJSONObject("pollution").getString("aqius");
    }
    
    public static String get_iqair(String s) throws JSONException{
        String inp[] = s.split(";");
        if (s.toLowerCase().equals("hello"))
            try {
                return get_country();
            } catch (IOException ex) {
                return "Cannot connect to Iqair server. Internet problem!";
            }
        else if (inp.length==3)
            try {
                return get_air(inp[0],inp[1],inp[2]);
            } catch (IOException ex) {
                return "City not found";
            }
        else if (inp.length==2)
            try {
                return get_city(inp[0], inp[1]);
            } catch (IOException ex) {
                return "State not found";
            }
        else try {
            return get_state(s);
            } catch (IOException ex) {
                return "Country not found";
            }
    }
    
    public static void main(String[] args) throws JSONException {
            // TODO code application logic here
            int buffersize = 512;
            int port = 1234;
            
        try {
            socket = new DatagramSocket(1234);
            dpreceive = new DatagramPacket(new byte[buffersize], buffersize);
            while (true){
                socket.receive(dpreceive);
                String temp = new String(dpreceive.getData(), 0, dpreceive.getLength());
                System.out.println("Server receive: "+temp+" from "+dpreceive.getAddress().getHostAddress()+ " at port "+socket.getLocalPort());
                if (temp.equals("bye")){
                    System.out.println("Server socket closed");
                    socket.close();
                    break;
                }
//                temp = rev(temp);
                String res = get_iqair(temp);
                
                dpsend = new DatagramPacket(res.getBytes(), res.getBytes().length, dpreceive.getAddress(), dpreceive.getPort());
                System.out.println("Server send back: " + res + " to client");
                socket.send(dpsend);
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Iqair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Iqair.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
    }
    
}
