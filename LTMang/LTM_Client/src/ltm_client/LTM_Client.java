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
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author tattr
 */
public class LTM_Client {
    
    int destPort = 1234;
    String hostname = "localhost";
    DatagramSocket socket;
    DatagramPacket dpsend, dpreceive;
    InetAddress add;
    KeyPair keypair;
    Cipher cipher;
    Cipher des_cipher;
    PublicKey server_public_key;
    
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
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private byte[] encrypt(String input){
        byte[] s = input.getBytes();
        cipher.update(s);
        try {
            return cipher.doFinal();
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger("Encrypt error: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger("Encrypt error: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String decrypt(String input){
        byte[] s = input.getBytes();
        des_cipher.update(s);
        try {
            return new String(des_cipher.doFinal(), "UTF8");
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger("Decrypt error: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger("Decrypt error: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String connect(){
        key_gen_RSA();
        try {
            add = InetAddress.getByName(hostname);	//UnknownHostException
            socket = new DatagramSocket();			//SocketException
            
            //exchange public key with server
            dpsend = new DatagramPacket(keypair.getPublic().getEncoded(), keypair.getPublic().getEncoded().length, add, destPort);
            socket.send(dpsend);
            dpreceive = new DatagramPacket(new byte[2048], 2048);
            socket.receive(dpreceive);
            //transfer byte back to key
            byte[] server_key_byte = dpreceive.getData();
            System.out.println(server_key_byte);
            X509EncodedKeySpec ks = new X509EncodedKeySpec(server_key_byte);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            server_public_key = kf.generatePublic(ks);
            
            return "Connected";
        } catch (UnknownHostException ex) {
            Logger.getLogger("host false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger("socket false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger("send public key false: "+LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(LTM_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error connection";
    }
    
    public String run(String input) {
        // TODO code application logic here
        try {
            byte[] data = input.getBytes();
            dpsend = new DatagramPacket(data, data.length, add, destPort);
            System.out.println("Client sent " + input + " to " + add.getHostAddress() + 
                            " from port " + socket.getLocalPort());
            socket.send(dpsend);				//IOExeption

            // Get response from server
            dpreceive = new DatagramPacket(new byte[512], 512);
            socket.receive(dpreceive);
            input = new String(dpreceive.getData(), 0, dpreceive.getLength());
            System.out.println("Client get: " + input);
        } catch (IOException e) { System.err.println(e);}
        return input;
    }
    
}
