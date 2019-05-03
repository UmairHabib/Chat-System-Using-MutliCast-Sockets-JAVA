/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import static com.oracle.jrockit.jfr.DataType.INTEGER;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Lucky
 */
public class Client {

    final static String INET_ADDR = "224.0.0.1";
    final static int PORT = 8888;

    Client(String s) throws UnknownHostException, InterruptedException {
        InetAddress Address = InetAddress.getByName(INET_ADDR);
        try {
            DatagramSocket ClientSocket = new DatagramSocket();
            DatagramPacket msgPacket = new DatagramPacket(s.getBytes(), s.getBytes().length, Address, PORT);
            ClientSocket.send(msgPacket);
            ServerSocket ss = new ServerSocket(Integer.parseInt(s));
            Socket soc = ss.accept();
            Thread T1 = new Thread(new Sender(soc)); //used to send and recieve messages parallel
            T1.start();
            Thread T2 = new Thread(new Reciever(soc));
            T2.start();
            T1.join();
            T2.join();
            soc.close();
            ss.close();
            ClientSocket.close();
        } catch (BindException e) {
            System.out.println("This Port is already entered ...Kindly retry with another one");
        } catch (IOException ex) {
            System.out.println("Exception Occurred in Client " + ex);
        }
    }
 public static int getPort()
    {
        int port=1024;
        boolean status=false;
        while(!status)
        {
              port=ThreadLocalRandom.current().nextInt(1024,1679);
            try(ServerSocket s =new ServerSocket(port))
            {
                status=true;
                s.close();
            }
            catch(IOException s)
            {
                status = false;            
            }
        }
        return port;
    }
    public static void main(String[] arr) throws UnknownHostException, InterruptedException {
        try {
            int x=getPort();
          
            Client c = new Client(Integer.toString(x));
        } catch (Exception e) {
            System.out.println("Exception Occurred in Client Main");

        }
    }
}
