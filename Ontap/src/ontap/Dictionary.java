/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontap;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @author tattr
 */
public class Dictionary {
    HashMap<String, String> map = new HashMap<String, String>();
    public void docfile(){
        System.setProperty("file.encoding","UTF-8");
        try{
            File f = new File ("src\\ontap\\dictionary.txt");
            Scanner sc = new Scanner (f);
            while (sc.hasNextLine()){
                String temp = sc.next();
                map.put(temp.split(";")[0], temp.split(";")[1]);
                map.put(temp.split(";")[1], temp.split(";")[0]);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File 0 ton tai!");
        }
    }
    
    public void tinh(String n){
        docfile();
        byte[] bytes_n = n.getBytes(StandardCharsets.UTF_8);
        String cleaned_n = new String (bytes_n, StandardCharsets.UTF_8);
        System.out.println(cleaned_n);
        if (map.get(cleaned_n)!=null)
            System.out.println(map.get(cleaned_n));
        else System.out.println("Từ điển chưa có");
    }
}
