/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lucky
 */
public class Sender implements Runnable {

    Socket soc;

    Sender(Socket ss) {
        this.soc = ss;
    }

    public void run() {
        try {

            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str2 = "";
            while (!str2.equals("stop")) {
                System.out.println("kindly Enter Client Data:: ");
                str2 = br.readLine();
                dout.writeUTF(str2);
                dout.flush();
            }
            dout.close();
        } catch (IOException ex) {

           // System.out.println("Exception Occurred in Sender " + ex);
        }
    }

}
