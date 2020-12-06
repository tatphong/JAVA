/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tattr
 */
public class Test_Multithread {

    static int id = 1;
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable worker;
        worker = new MyRunner(id);
        id++;
        executor.execute(worker);
        
    }
    
}
