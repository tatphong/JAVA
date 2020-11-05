/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;

/**
 *
 * @author tattr
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    private Socket socket = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null; 

    public Client(String address, int port) throws UnknownHostException, IOException
    { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String line = ""; 
            while (!line.equals("bye")) 
            { 
                    line = stdIn.readLine();
                    System.out.println("Client sent: " + line);
                    out.write(line);
                    out.newLine();
                    out.flush();
//                    receive response
                    System.out.println("Client received: " + in.readLine()); 
            } 
            in.close(); 
            out.close(); 
            socket.close(); 
    } 

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Client client = new Client("127.0.0.1", 5000); 
    }
    
}
