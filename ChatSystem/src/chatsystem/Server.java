//L164348 - This code is for Chat with server from all clients to all clients
//AP_Sec_A

package chatsystem;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 *
 * @author Lucky
 */
public class Server {

    final static String INET_ADDR = "224.0.0.1";
    final static int PORT = 8888;
    static Vector<Socket> ClientArr = new Vector<Socket>();
    static StringBuffer Messages[] = new StringBuffer[10];

    Server() throws UnknownHostException {
        try {
            InetAddress Address = InetAddress.getByName(INET_ADDR);
            MulticastSocket MulticastServer = new MulticastSocket(PORT);
            MulticastServer.joinGroup(Address);
            Thread T1 = new Thread(new Registrar(MulticastServer));   ///this registrar thread will wait for a message(port) of client to add in vector array 
            T1.setDaemon(true);
            T1.start();
            Thread T2 = new Thread(new AdminThread());  //used to get last 10 messages
            T2.setDaemon(true);
            T2.start();
            
            T1.join();
            T2.join();
        } catch (Exception ex) {
            System.out.println("Exception Occured in multicasted Server " + ex);
        }

    }

    public static void main(String[] args) throws UnknownHostException {
        // TODO code application logic here
        System.out.println("Server Started");
        Server S = new Server();
    }

}
