
package chatsystem;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Lucky
 */
public class UserThread implements Runnable {

    static int index = 0;
    int port;
    int id;
    Socket s;

    UserThread(String i, int num) {
        port = Integer.parseInt(i.substring(0, 4));
        id = num;
    }

    public void run() {
        try {

            s = new Socket("localhost", port);
            Server.ClientArr.add(s);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str2 = "";
            while (!str2.equals("stop")) {
                str2 = din.readUTF();
                if(!str2.equals("stop")){
                String temp = new String("Client " + id + " says: " + str2);

                if (index < 10) {   //updating last 10 messsages
                    Server.Messages[index] = new StringBuffer(temp);
                    index++;
                } else {
                    for (int i = 0; i < 9; i++) {
                        Server.Messages[i] = Server.Messages[i + 1];
                    }
                    Server.Messages[9] = new StringBuffer(temp);
                }
                System.out.println(temp);
                for (int i = 0; i < Server.ClientArr.size(); i++) {   //sending message to all clients
                    dout = new DataOutputStream(Server.ClientArr.get(i).getOutputStream());
                    dout.writeUTF(temp);
                    dout.flush();
                }
                }
            }
            dout.close();
            for (int i = 0; i < Server.ClientArr.size(); i++) {  //removing stopped client from server 
                if (Server.ClientArr.get(i) == s) {
                    Server.ClientArr.remove(i);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception Occured in User Thread with port " + e);
        } catch (IOException e) {

            System.out.println("IOException Occured in User Thread with port " + e);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
