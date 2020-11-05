/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class Server_UDP {

    /**
     * @param args the command line arguments
     */
    public static String rev(String s){
        StringBuilder str = new StringBuilder();
        str = str.append(s);
        String rev_str;
        rev_str = str.reverse().toString();
        return rev_str;
    }
    
    public static void main(String[] args) {
            // TODO code application logic here
            int buffersize = 512;
            int port = 1234;
            DatagramSocket socket;
            DatagramPacket dpreceive, dpsend;
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
                temp = rev(temp);
                dpsend = new DatagramPacket(temp.getBytes(), temp.getBytes().length, dpreceive.getAddress(), dpreceive.getPort());
                System.out.println("Server send back: "+ temp + " to client");
                socket.send(dpsend);
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
    }
    
}
