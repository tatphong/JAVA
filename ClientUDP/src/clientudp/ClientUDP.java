/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author tattr
 */
public class ClientUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int destPort = 1234;
	String hostname = "localhost";
        DatagramSocket socket;
        DatagramPacket dpsend, dpreceive;
        InetAddress add; Scanner stdIn;
        try {
            add = InetAddress.getByName(hostname);	//UnknownHostException
            socket = new DatagramSocket();			//SocketException
            stdIn = new Scanner(System.in);
            while(true) {
                System.out.print("Client input: ");
                String tmp = stdIn.nextLine();
                byte[] data = tmp.getBytes();
                dpsend = new DatagramPacket(data, data.length, add, destPort);
                System.out.println("Client sent " + tmp + " to " + add.getHostAddress() + 
                                " from port " + socket.getLocalPort());
                socket.send(dpsend);				//IOExeption
                if(tmp.equals("bye")) {
                    System.out.println("Client socket closed");
                    stdIn.close(); 
                    socket.close(); 	
                    break;
                }
                // Get response from server
                dpreceive = new DatagramPacket(new byte[512], 512);
                socket.receive(dpreceive);
                tmp = new String(dpreceive.getData(), 0, dpreceive.getLength());
                System.out.println("Client get: " + tmp);
            }
        } catch (IOException e) { System.err.println(e);}
    }
    
}
