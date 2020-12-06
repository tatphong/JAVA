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
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author tattr
 */
public class MyRunner implements Runnable{
    Simsimi sim;
    int id = 0;
    KeyPair keypair;
    Cipher cipher;
    Cipher des_cipher;
    PublicKey client_public_key;
    
    public MyRunner(int id){
        this.id = id;
        try {
            sim = new Simsimi();
        } catch (IOException ex) {
            Logger.getLogger("Init Simsimi error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        key_gen_RSA();
    }
    
    private void key_gen_RSA(){
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            keypair = keyPairGen.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //descryption
            des_cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            des_cipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private byte[] encrypt(String input){
        byte[] s = input.getBytes();
        cipher.update(s);
        try {
            return cipher.doFinal();
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger("Encrypt error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger("Encrypt error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String decrypt(String input){
        byte[] s = input.getBytes();
        des_cipher.update(s);
        try {
            return new String(des_cipher.doFinal(), "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger("Decrypt error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger("Decrypt error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger("Decrypt error: "+MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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
            // độ ở đây là độ k ko phải độ c >>> chuyển thì trừ 273.15
        } catch (IOException ex) {
            res = "Sorry, this location does not exists in API";
            Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String controller(String input){
        String res = "";
        if (input.toLowerCase().equals("help"))
        {
            res = "<html>weather {city}<br/> iplocation {ip address}<br/> check port {ip} from {x} to {y}</html>";
        }
        else if (input.toLowerCase().startsWith("weather "))
        {
            String location = input.split("weather ")[1];
            res = getWeather(location);
        } 
        else if (input.toLowerCase().startsWith("iplocation "))
        {
            String ip = input.split("iplocation ")[1];
            res = get_dns_via_ip(ip);
        }  
        else if (input.toLowerCase().startsWith("check port "))
        {
            String ip = input.split("check port ")[1];
            String begin = (input.split("from ")[1]).split(" to")[0];
            String end = input.split("to ")[1];
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
            
            //exchange public key with server
            dpreceive = new DatagramPacket(new byte[2048], 2048);
            socket.receive(dpreceive);
            dpsend = new DatagramPacket(keypair.getPublic().getEncoded(), keypair.getPublic().getEncoded().length, dpreceive.getAddress(), dpreceive.getPort());
            socket.send(dpsend);
            //transfer byte back to key
            byte[] server_key_byte = dpreceive.getData();
            System.out.println(server_key_byte);
            X509EncodedKeySpec ks = new X509EncodedKeySpec(server_key_byte);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            client_public_key = kf.generatePublic(ks);
            
            while (true){
                socket.receive(dpreceive);
                String temp = decrypt(new String(dpreceive.getData(), 0, dpreceive.getLength()));
                System.out.println("Server receive: "+ temp +" from "+dpreceive.getAddress().getHostAddress()+ " at port "+socket.getLocalPort());
//                if (temp.equals("bye")){
//                    System.out.println("Server socket closed");
//                    socket.close();
//                    break;
//                }
                temp = controller(temp);
                dpsend = new DatagramPacket(temp.getBytes(), temp.getBytes().length, dpreceive.getAddress(), dpreceive.getPort());
                System.out.println("Server send back: "+ temp + " to client");
                socket.send(dpsend);
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(MyRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
