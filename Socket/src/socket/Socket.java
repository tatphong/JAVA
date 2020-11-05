/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import com.sun.corba.se.spi.activation.Server;
import java.io.*;
import java.net.*;

/**
 *
 * @author tattr
 */
public class Socket {

    /**
     * @param args the command line arguments
     */
    //Đấy là server
    private java.net.Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  
    public Socket(int port) 
    { 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = ""; 
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readLine();
                    System.out.println("Server received: " + line); 
                    
//                    send back
                    String line2 = "response";
                    System.out.println("Server sent: response");
                    out.write(line2);
                    out.newLine();
                    out.flush();
                } 
                catch(IOException i) 
                { 
                    System.err.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
            
            in.close(); 
            out.close();
            socket.close(); 
            server.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String[] args) {
        // TODO code application logic here
        Socket server = new Socket(5000); 
    }
    
}
