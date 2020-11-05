/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontap;

import static java.lang.Math.sqrt;

/**
 *
 * @author tattr
 */
public class PhanTich {
    int[] res= new int[100000000];
    
//    public boolean kt_snt(int n){
//        if (n<2) return false;
//        for (int i=2;i<=sqrt(n);i++)
//        {
//            if (n%i==0) return false;
//        }
//        return true;
//    }
    
    public void tinh(int n) {
        int i=2;
        
        while (n>1){
//            if (kt_snt(i)){
                if (n%i==0){
                    n/=i;
                    res[i]++;
                }
                else i++;
//            }
//            else i++;
        }
        boolean begin=true;
        for (int j=2;j<=i;j++){
            if (res[j]>0){
                if (begin==false) System.out.print(" x ");
                System.out.print(j+"^"+res[j]);
                begin=false;
            }
        }
        System.out.println();
    }
}
