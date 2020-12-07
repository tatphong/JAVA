/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author tattr
 */
public class Server_UDP {

    /**
     * @param args the command line arguments
     */
    KeyPair keypair;
    Cipher cipher;
    Cipher des_cipher;
    PublicKey client_public_key;
    
    public static void main(String[] args) {
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
            byte[] server_key_byte = new byte[2048];
            server_key_byte = dpreceive.getData();
            System.out.println(server_key_byte);
            X509EncodedKeySpec ks = new X509EncodedKeySpec(server_key_byte);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            client_public_key = kf.generatePublic(ks);
            
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, client_public_key);
            
            while (true){
                socket.receive(dpreceive);
                String temp = new String(dpreceive.getData(), 0, dpreceive.getLength());
                System.out.println("Server receive: "+ temp +" from "+dpreceive.getAddress().getHostAddress()+ " at port "+socket.getLocalPort());
//                if (temp.equals("bye")){
//                    System.out.println("Server socket closed");
//                    socket.close();
//                    break;
//                }
                temp = temp;
                dpsend = new DatagramPacket(temp.getBytes(), temp.getBytes().length, dpreceive.getAddress(), dpreceive.getPort());
                System.out.println("Server send back: "+ temp + " to client");
                socket.send(dpsend);
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
