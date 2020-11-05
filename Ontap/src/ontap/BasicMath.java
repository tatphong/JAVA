/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontap;

import java.util.StringTokenizer;

/**
 *
 * @author tattr
 */
public class BasicMath {
    int a=0,b=0;
    float res=0;
//    public void getAB(String s, String f){
//        a=Integer.parseInt(s.split(f)[0]);
//        b=Integer.parseInt(s.split(f)[1]);
//    }
    public void tinh(String s){
        StringTokenizer data = new StringTokenizer(s, "+-*/", true);

        a=Integer.parseInt(data.nextToken());
        String f=data.nextToken();
        b=Integer.parseInt(data.nextToken());
        
//        System.out.println(a+" "+b);
        if (f.equals("+"))
            res=a+b;
        else if (f.equals("-"))
            res=a-b;
        else if (f.equals("*"))
            res=a*b;
        else
            res=(float) ((a*1.0)/(b*1.0));
        System.out.println(res);
    }
}
