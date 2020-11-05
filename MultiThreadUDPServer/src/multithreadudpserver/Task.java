/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadudpserver;

import java.util.StringTokenizer;

/**
 *
 * @author tattr
 */
public class Task implements Runnable{
    String s;
    String res;
    
    public Task(String s, String res){
        this.s = s;
        this.res = res;
    }
    
    public void calc(String s){
        StringTokenizer data = new StringTokenizer(s, "+-*/", true);
        double calc_res=0;
        int a=Integer.parseInt(data.nextToken());
        String f=data.nextToken();
        int b=Integer.parseInt(data.nextToken());
        
//        System.out.println(a+" "+b);
        if (f.equals("+"))
            calc_res=a+b;
        else if (f.equals("-"))
            calc_res=a-b;
        else if (f.equals("*"))
            calc_res=a*b;
        else
            calc_res=(float) ((a*1.0)/(b*1.0));
        res = calc_res+"";
    }
    
    public void prime(String s){
        int i=2, n=Integer.parseInt(s);
        int[] prime_res= new int[100000000];
        
        while (n>1){
//            if (kt_snt(i)){
                if (n%i==0){
                    n/=i;
                    prime_res[i]++;
                }
                else i++;
//            }
//            else i++;
        }
        boolean begin=true;
        for (int j=2;j<=i;j++){
            if (prime_res[j]>0){
                if (begin==false) res += " x ";
                res += j+"^"+prime_res[j];
                begin=false;
            }
        }
    }
    
    public String getResult(){
        return res;
    }
    
    public void run(){
        System.out.println(s.split(" ")[1]);
        if (s.split(" ")[0].equals("calc")) calc(s.split(" ")[1]);
        else prime(s.split(" ")[1]);
    }
}
