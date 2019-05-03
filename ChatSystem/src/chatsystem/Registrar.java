/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 *
 * @author Lucky
 */
class Registrar implements Runnable {

    MulticastSocket obj;
    static int num = 1;

    public void run() {  //this class will register every client to server
        try {
            byte Buffer[] = new byte[256];
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(Buffer, Buffer.length);
                obj.receive(msgPacket);
                String msg = new String(Buffer, 0, Buffer.length);
                System.out.println("Server/Registrar Thread received msg: " + msg);
                Thread T3 = new Thread(new UserThread(msg, num));  //serer is talking with client in parallel
                T3.setDaemon(true);
                T3.start();
                num++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception Occured in Registrar Thread " + e);
        } catch (IOException e) {
            System.out.println("Registrar Thread Exception has occured" + e);
        }
    }

    Registrar(MulticastSocket i) {
        obj = i;
    }

}
