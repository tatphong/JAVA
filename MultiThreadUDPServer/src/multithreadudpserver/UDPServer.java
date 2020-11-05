/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadudpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class UDPServer implements Runnable{
    int buffersize = 512;
    int port = 1234;
    DatagramSocket socket;
    DatagramPacket dpreceive, dpsend;
    public void run(){
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
                Task t = new Task(temp, "");
                
                String res = t.getResult();
                dpsend = new DatagramPacket(res.getBytes(), res.getBytes().length, dpreceive.getAddress(), dpreceive.getPort());
                System.out.println("Server send back: "+ res + " to client");
                socket.send(dpsend);
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(MultiThreadUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
