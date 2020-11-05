/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontap;

/**
 *
 * @author tattr
 */
public class GCD {
    int s=0;
    public void tinh(int n){
        if (n<3) {
            System.out.println("N cần lớn hơn = 3");
            return;
        }
        
        for (int i=1;i<=n;i++){
            if (n%i==0) s+=i;
        }
        System.out.println("Tong uoc so: "+this.s);
    }
}
