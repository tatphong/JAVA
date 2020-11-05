/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltmang_basic;

import java.util.*;

/**
 *
 * @author tattr
 */
public class LTMang_basic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Domain2IP a = new Domain2IP();
//        System.out.print("Nhập domain: ");
//        a.exec(sc.nextLine());
//        a.domain2ip();
        a.ip_connect();
    }
    
}
