/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class Dictionary {

    /**
     * @param args the command line arguments
     */
    private java.net.Socket socket = null;
    private ServerSocket server = null; 
    BufferedWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, String> vietanh = new HashMap<String, String>();
    HashMap<String, String> anhviet = new HashMap<String, String>();
    
    public void docfile(){
        System.setProperty("file.encoding","UTF-8");
        try{
            File f = new File ("src\\dictionary\\dictionary.txt");
            BufferedReader sc = new BufferedReader(new FileReader(f));
            String temp;
            try {
                while ( (temp=sc.readLine()) !=null){
                    anhviet.put(temp.split(";")[0], temp.split(";")[1]);
                    vietanh.put(temp.split(";")[1], temp.split(";")[0]);
                }
            } catch (IOException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File không ton tai!");
        }
    }
    
    public void luufile() throws IOException{
        try {
            FileWriter myWriter = new FileWriter("src\\dictionary\\dictionary.txt");
            for (String i : anhviet.keySet()) {
                myWriter.write(i+";"+anhviet.get(i)+"\n");
            }
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean kt_keyword(String s){
        if (anhviet.get(s)!=null)
            return true;
        return false;
    }
    public boolean kt_keywordVietAnh(String s){
        if (vietanh.get(s)!=null)
            return true;
        return false;
    }
    
    public String translate(String s){
        if (kt_keyword(s))
            return anhviet.get(s);
        else if (kt_keywordVietAnh(s))
            return vietanh.get(s);
        else return "Từ điển chưa có";
    }
    
    public String add(String s) throws Exception{
        String key = s.split(";")[0];
        String word = s.split(";")[1];
        
        if (key.length()==0) return "Lỗi cú pháp";
        
        //xử lý
        if (kt_keyword(key))
            return "Từ đã tồn tại không thể thêm";
        else{
            anhviet.put(key, word);
            vietanh.put(word, key);
            return "Thêm thành công";
        }
    }
    
    public String
         delete(String s) throws Exception{
        String temp="";
        if (kt_keyword(s)){
            temp = anhviet.get(s);
            anhviet.remove(s);
            vietanh.remove(temp);
            return "Xóa thành công";
        }
        else
            return "Từ chưa tồn tại không thể xóa";
    }
    
    public String exec(String s){
        if (s.startsWith("ADD;")){
            try {
                return add(s.split("ADD;")[1]);
            } catch (Exception ex) {
                return "Lỗi cú pháp";
            }
        }
        else if (s.startsWith("DEL;")){
            try {
                return delete(s.split("DEL;")[1]);
            } catch (Exception ex) {
                return "Lỗi cú pháp";
            }
        }
        else{
            return translate(s.toLowerCase());
        }
    }
    
    public Dictionary(int port) 
    { 
        docfile();
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
                    String response=exec(line);
                   

//                    server response
                    System.out.println("Server sent: "+response);
                    
                    out.write(response.split("ADD;")[0]);
                    out.newLine();
                    out.flush();
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
            luufile();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
    } 
  
    public static void main(String[] args) {
        // TODO code application logic here
        Dictionary server = new Dictionary(5000); 
    }
    
    
}
