/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm_client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 *
 * @author tattr
 */
public class LTM_Client {
    
    int destPort = 1234;
    String hostname = "172.20.10.14";
    DatagramSocket socket;
    DatagramPacket dpsend, dpreceive;
    InetAddress add;
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
    
    public String connect(){
        try {
            add = InetAddress.getByName(hostname);	//UnknownHostException
            socket = new DatagramSocket();			//SocketException
            return "Connected";
        } catch (UnknownHostException ex) {
            Logger.getLogger("host false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger("socket false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger("send public key false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error connection";
    }
    
    public String run(String input) {
        // TODO code application logic here
        try {
            byte[] data = base64encode(input).getBytes();
            dpsend = new DatagramPacket(data, data.length, add, destPort);
            System.out.println("Client sent " + input + " to " + add.getHostAddress() + 
                            " from port " + socket.getLocalPort());
            socket.send(dpsend);				//IOExeption

            // Get response from server
            dpreceive = new DatagramPacket(new byte[512], 512);
            socket.receive(dpreceive);
            //System.out.println("Encrypted from server: "+new String(dpreceive.getData(), 0, dpreceive.getLength(), "UTF8")); // hiện đoạn đã mã hóa
            input = base64decode(new String(dpreceive.getData(), 0, dpreceive.getLength(), "UTF8"));
            System.out.println("Client get: " + input);
        } catch (IOException e) { System.err.println(e);}
        return input;
    }
    
}
