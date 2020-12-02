package Server;

import AES.AES;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Phat
 */
public class Read extends Thread {

    private Socket socket;
    AES aes = new AES();

    public Read(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            String c = null;
            Event ev = new Event();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            for(Socket item : Server.ListSocket){
//                DataOutputStream tem = new DataOutputStream(item.getOutputStream());
//                tem.writeUTF(re);
//            }
//            dos.writeUTF(re);
//            System.out.println("Server.Read.run()");
            while (true) {
                String receive = dis.readUTF();
                String key = socket.getInetAddress().toString().substring(1);
                String decrypt = aes.decrypt(receive, key);
                if (receive.equals("exit")) {
                    Server.ListSocket.remove(socket);
                    for (Socket item : Server.ListSocket) {
                        System.out.println("ABC" + socket);
                    }
                    System.out.println("Disconnected " + socket);
                    dis.close();
                    socket.close();
                    break;
                }
                String encrypt = aes.encrypt(ev.Query(decrypt), key);
                dos.writeUTF(encrypt);
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (Exception ex) {
                System.out.println("Disconnected");
            }
        }
    }

}
