/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Lucky
 */
class AdminThread implements Runnable {

    final static int PORT = 9090;
    final static String INET_ADD = "https://localhost:9090";

    public void run() {
        try {
            while (true) {
                ServerSocket ss = new ServerSocket(PORT);
                Socket soc = ss.accept();
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                System.out.println("Last Messages are:: ");
               // out.println("Last " + Server.Messages.length + " Messages:: ");
                for (int i = 0; i < Server.Messages.length; i++) {
                   
                    if (Server.Messages[i] != null) {
                      //  out.println(Server.Messages[i]);
                        System.out.println(Server.Messages[i]);
                    }
                }
                out.close();
                soc.close();
                ss.close();
            }
        } catch (Exception e) {
            System.out.println("Exception occured at AdminThread " + e);
        }
    }

}
