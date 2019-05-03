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
public class Reciever implements Runnable {

    Socket soc;

    Reciever(Socket ss) {
        this.soc = ss;
    }

    public void run() {  //recieving messages parallel
        try {
            DataInputStream din = new DataInputStream(soc.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            while (!str.equals("stop")) {
                str = din.readUTF();
                System.out.println("Recieved Data:: ");
                System.out.println(str);
            }
            din.close();
        } catch (IOException ex) {
            //System.out.println("Exception Occurred in Reciever "+  ex);
        }
    }

}
