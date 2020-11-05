/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontap;

import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;
import java.util.*;
import java.util.StringTokenizer;

/**
 *
 * @author tattr
 */
public class Chuoi {
    public void tinh(String s){
        System.setProperty("file.encoding","UTF-8");
        
        StringTokenizer data = new StringTokenizer(s, " ");
        LinkedHashMap<String, String> map  = new LinkedHashMap<String, String>();
        
        while (data.hasMoreTokens()){
            String temp = data.nextToken();
            if (map.get(temp.toLowerCase())==null)
                map.put(temp.toLowerCase(),temp);
        }
        
        for (String i : map.keySet())
            System.out.print(map.get(i)+" ");
    }
}
//Dai hoc Sai Gon la truong dai hoc lau doi nhat sai gon