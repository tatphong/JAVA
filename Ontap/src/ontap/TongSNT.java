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
public class TongSNT {
    int s = 0;
    
    public boolean kt_snt(int n){
        if (n<2) return false;
        for (int i=2;i<=sqrt(n);i++)
        {
            if (n%i==0) return false;
        }
        return true;
    }
    
    public void tinh(int n){
        if (n<3) {
            System.out.println("N cần lớn hơn = 3");
            return;
        }
        
        for (int i=1;i<n;i++){
            if (kt_snt(i)) s+=i;
        }
        System.out.println("Tong SNT: "+this.s);
    }
}
