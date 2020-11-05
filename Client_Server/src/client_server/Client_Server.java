/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import java.math.*;

/**
 *
 * @author tattr
 */
public class Client_Server {

    /**
     * @param args the command line arguments
     */
    //Đấy là server
    private java.net.Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    //bai 4
    Random generator = new Random();
    int rand = generator.nextInt(100)+1;
    int countr = 0;
    long begin = Calendar.getInstance().getTimeInMillis();
    
    public String rev(String s){
        StringBuilder str = new StringBuilder();
        str = str.append(s);
        String rev_str;
        rev_str = str.reverse().toString();
        return rev_str;
    }
    
    public boolean kt_SHH(int n){
        int s=0;
        for (int i=1;i<n;i++){
            if (n%i==0) s+=i;
            if (s>n) return false;
        }
        if (s==n) return true;
        return false;
    }
    public int sohoanhao(String line){
        int res=0, s=0, n=Integer.parseInt(line);
        while (!kt_SHH(n)){//for (; !kt_SHH(n) ; n++){}
            ++n;
        }
        res=n;
        return res;
    }
    
    public void phantichthuaso(String line){
        int[] res= new int[100000000];
        int i=2, n=Integer.parseInt(line);
        
        while (n>1){
                if (n%i==0){
                    n/=i;
                    res[i]++;
                }
                else i++;
        }
        
        //server response
        System.out.println("Server sent: "+res);               
        try {
            boolean begin=true;
            for (int j=2;j<=i;j++){
                if (res[j]>0){
                    if (begin==false) out.write(" x ");
                    out.write(j+"^"+res[j]);
                    begin=false;
                }
            }
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doanso(String line){
        int n = Integer.parseInt(line);
        try {
            if (n==rand){
                long end = Calendar.getInstance().getTimeInMillis();
                out.write("Tong thoi gian da doan: "+(end-begin)*1.0/1000+" giây. "
                         + "Tong so lan doan: "+countr);
                
            }
            else if (n<rand) 
                out.write("lớn hơn "+n);
            else out.write("nhỏ hơn "+n);
            countr++;
            
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean kt_DiemThuocHinhTron(Pair<Integer,Integer> a, Pair<Integer,Integer> b){
        //tính khoảng cách từ A đến B nếu nhỏ hơn R thì thuộc hình tròn
        double r = Math.sqrt(Math.pow(b.getKey()-a.getKey(), 2) + Math.pow(b.getValue()-a.getValue(), 2));
        
        //R mặc định là 100, tâm luôn nằm ở tọa độ (100,100)
        if (r<=100) return true;
        return false;
    }
    public void tinhPi(String line){
        long pi_begin = Calendar.getInstance().getTimeInMillis();
        long n = Integer.parseInt(line), dem=0;
        double pi;
        Random gen = new Random();
        Pair<Integer, Integer> tam = new Pair<>(100,100);
        
        for (int i=0;i<n;i++){
            Pair<Integer, Integer> rand_x = new Pair<>(gen.nextInt(200),gen.nextInt(200));
            if (kt_DiemThuocHinhTron(tam, rand_x)) dem++;
        }
        
        pi=4*dem*1.0/n;
        long pi_end = Calendar.getInstance().getTimeInMillis();
        //server response
        try {
            out.write("Pi: "+pi+"\t"
                    + "Thời gian trễ: "+(pi_end-pi_begin)*1.0/1000);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Client_Server(int port) 
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
            while (!line.equals("bye")) 
            { 
                try
                { 
                    line = in.readLine();
                    System.out.println("Server received: " + line); 
                    
//                    send back
                    String response;
                    //bai 1
//                    response = rev(line);

                    // bai 2
//                    int bai2;
//                    bai2 = sohoanhao(line);
//                    response = ""+bai2;

                    //bai 3
//                    phantichthuaso(line);//cần comment lại phần server response tại main
                    
                    //bai 4
//                    doanso(line);

                    //bai 8
                    tinhPi(line);

                    //server response
//                    System.out.println("Server sent: "+res);
//                    
//                        out.write();
//                        out.newLine();
//                        out.flush();
//                    
                } 
                catch(IOException i) 
                { 
//                    System.err.println(i); 
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
        Client_Server server = new Client_Server(5000); 
    }
    
}
