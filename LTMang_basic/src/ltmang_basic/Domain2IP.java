/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltmang_basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class Domain2IP {
    public void exec(String n){
        try{
            InetAddress add = InetAddress.getByName(n);
            System.out.println(n+" : "+add.getHostAddress());
        }catch (UnknownHostException ex) {
            System.err.println("không thể tìm thấy tên miền: "+n);;
        }
}
    
    public void domain2ip(){
        String domain = "";
        try{
            File f = new File ("src\\ltmang_basic\\domain.txt");
            Scanner sc = new Scanner (f);
            while (sc.hasNextLine()){
                try{
                    domain = sc.nextLine();
                    InetAddress add = InetAddress.getByName(domain);
                    System.out.println(domain+" : "+add.getHostAddress());
                }catch (UnknownHostException ex) {
                    System.err.println("không thể tìm thấy tên miền: "+domain);;
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("File 0 ton tai!");
        }
    }
    
    public void ip_connect(){
        String ip = "";
        try{
            File f = new File ("src\\ltmang_basic\\ip.txt");
            Scanner sc = new Scanner (f);
            while (sc.hasNextLine()){
                try{
                    ip = sc.nextLine();
                    InetAddress add = InetAddress.getByName(ip);
                    if (add.isReachable(3000))
                        System.out.println(ip +" is reachable");
                    else
                        System.out.println(ip +" is unreachable");
                } catch (IOException ex) {
                    Logger.getLogger(Domain2IP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("File 0 ton tai!");
        }
    }
        
}
