/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author tattr
 */
public class PortScanner {

    PortScanner() {
    }
    /**
     * @param args the command line arguments
     */
    private String get_dns_via_ip(String ip){
        String res = "";
        try {
            InetAddress add = InetAddress.getByName(ip);
            res = add.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(PortScanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PortScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(res);
        return res;
    }
    
    public String check_port(String ip, int begin, int end) {
        String res = "";
        String dns = get_dns_via_ip(ip);
        
        //check port is valid
        if(begin <= 1||end >= 65535 || end< begin)
            return "Invalid port number";

        for (int port = begin; port <= end; port++) {
            try {
                Socket socket = new Socket(dns ,port);
                if(socket.isConnected()){
                    System.out.println("Port " + port + " is open");
                    socket.close();
                    res += port + ", ";
                }
            } catch (Exception ex) {
                 System.out.println(port+" close");
            }
        }

        if (res == "") return "No port is opened";
        res = res.substring(0, res.length()-2);
        return "Port opened: " + res;
    }
}
