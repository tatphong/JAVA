/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author tattr
 */
public class MyRunner implements Runnable{
    Simsimi sim;
    int id = 0;
    public static final String DEFAULT_ENCODING = "UTF-8"; 
    static BASE64Encoder enc = new BASE64Encoder();
    static BASE64Decoder dec = new BASE64Decoder();

    public static String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }//base64encode

    public static String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }//base64decode
    
    public MyRunner(int id){
        this.id = id;
        try {
            sim = new Simsimi();
        } catch (IOException ex) {
            Logger.getLogger("Init Simsimi error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getWeather(String location)  {
        String res = "";
        try {    
            String key = "4ca633b9d470650ea1ac927fdfe79ad3";
            String namecity=location;
            Document doc = org.jsoup.Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q="+namecity+"&appid="+key)
                    .userAgent("Mozilla").ignoreContentType(true).get();
            String pre = doc.body().text();
            java.lang.reflect.Type classOft = new TypeToken<Example>(){}.getType();
            Gson gson = new Gson();
            Example ex = gson.fromJson(pre, classOft);
            res = ex.thoitiet();
        } catch (IOException ex) {
            res = "Sorry, this location does not exists in API";
            //Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String get_dns_via_ip(String ip){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://ip-api.com/json/"+ip)
                    .userAgent("Mozilla").ignoreContentType(true).get();
        } catch (IOException ex) {
            Logger.getLogger("connect to ip-api error: "+chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pre = doc.body().text();
        java.lang.reflect.Type classOft = new TypeToken<ip>(){}.getType();
        Gson gson = new Gson();
        ip dns = gson.fromJson(pre, classOft);
        String res = "Sorry, this ip does not exists!";
        res = dns.getLocation();
        return res;
    }
    
    public boolean cleaned_IP(String ip){
        StringTokenizer data = new StringTokenizer(ip, ".");
        if (data.countTokens()!=4)                                      //kiểm tra ip có đúng 4 cụm, nhiều hơn hay thiếu đều sai
            return false;
        while (data.hasMoreTokens()){
            String current_token = data.nextToken();
            if (!StringUtil.isNumeric(current_token))                   // kiểm tra coi phải số hay không
                return false;
            int temp = Integer.parseInt(current_token);
            if (temp < 0 || temp > 254)                                 // ip hợp lệ phải nằm trong khoảng từ 0 tới 254
                return false;
        }
        return true;
    }
    
    public boolean cleaned_input(String input, int numWord, boolean exceedNumWord){ // exceedNumWord true nghĩa là cho phép nhìu hơn số từ quy định
        StringTokenizer data = new StringTokenizer(input, " ");
        if (exceedNumWord){
            if (data.countTokens()>=numWord)
                return true;
        }
        else{
            if (data.countTokens() == numWord)
                return true;
        }
        return false;
    }
    
    public String controller(String input){
        String res = "";
        if (input.toLowerCase().equals("help"))
        {
            res = "weather {city}<br/> iplocation {ip address}<br/> check port {ip} from {x} to {y}";
        }
        else if (input.toLowerCase().startsWith("weather "))
        {
            if (!cleaned_input(input, 2, true))
                return "Missing location param in weather syntax";
            String location = input.split("weather ")[1];
            res = getWeather(location);
        } 
        else if (input.toLowerCase().startsWith("iplocation "))
        {
            if (!cleaned_input(input, 2, false))
                return "Iplocation syntax error";
            String ip = input.split("iplocation ")[1];
            if (!cleaned_IP(ip))
                return "Invalid Ip address";
            res = get_dns_via_ip(ip);
        }  
        else if (input.toLowerCase().startsWith("check port "))
        {
            if (!cleaned_input(input, 7, false))
                return "CheckPort syntax error";
            String ip = input.split("check port ")[1].split(" from")[0];
            if (!cleaned_IP(ip))
                return "Invalid Ip address";
            String begin = (input.split("from ")[1]).split(" to")[0];
            String end = input.split("to ")[1];
            if (!StringUtil.isNumeric(begin) || !StringUtil.isNumeric(end))
                return "Port must be numeric";
            res = new PortScanner().check_port(ip, Integer.parseInt(begin), Integer.parseInt(end));
        }
        else
        {
            try {    
                res = sim.run(input);
            } catch (MalformedURLException ex) {
                Logger.getLogger("controller - simsimi error"+chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(res);
        return res;
    }
    
    @Override
    public void run() {
        int buffersize = 512;
        int port = 1234;
        DatagramSocket socket;
        DatagramPacket dpreceive, dpsend;
        try {
            socket = new DatagramSocket(1234);
            dpreceive = new DatagramPacket(new byte[buffersize], buffersize);

            while (true){
                socket.receive(dpreceive);
                String temp = base64decode(new String(dpreceive.getData(), 0, dpreceive.getLength(), "UTF8"));
                System.out.println("Server receive: "+ temp +" from "+dpreceive.getAddress().getHostAddress()+ " at port "+socket.getLocalPort());
//                if (temp.equals("bye")){
//                    System.out.println("Server socket closed");
//                    socket.close();
//                    break;
//                }
                temp = controller(temp);
                System.out.println("Server send back: "+ temp + " to client");
                byte[] data = base64encode(temp).getBytes();
                dpsend = new DatagramPacket(data, data.length, dpreceive.getAddress(), dpreceive.getPort());
                socket.send(dpsend);
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
