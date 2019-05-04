# Chat-System-Using-MutliCast-Sockets-JAVA

Java Language was used for creation of this program.

Netbeans was used for running this project.
      
      It can be downloaded by using the following link:
      https://netbeans.org/downloads/8.0.2
     
This project is terminal/console based except graphical User Interface
Question Description:

             The chat system
        To show the usage of the three protocols described above a chat system
        is build. It consists of three main parts: the chat server, the chat client
        and an administrative client.
        
        ## 3.1 Requirements
        The system has to meet the following requirements:
        • A client should be able to register to the server.
        • Functionality to send messages that it receives from one client to
        all connected clients.
        • A possibility for the clients to disconnect.
        • A possibility for the administrator to see the last ten messages using
        a normal browser.
        
        3.2 Analysis
        The requirements are leading to the use cases shown in figure 1. The
        first three use cases are related to the normal user. The last one is for
        administrative use. Following the use cases are described in detail.
        Figure 1: Use cases for the chat system
        • A user wants to connect to the server. After finding, the server
        inserts the client in a table, to be able to send messages to each
        client that has registered. After this the server sends a message to
        all clients that the user has registered.
        
        3.3 Design
        • The use case chat contains sending and receiving of messages from
        the server. A client can send messages to the server. The server
        sends those messages to all clients. Each client has to wait for
        incoming messages from the server, to display them to the user.
        • The clients can disconnect from the server. The server has to remove
        them from the list of clients. After this a message is sent to
        all remaining clients, that the user has left the system.
        • To administrate the server a browser is used. The server has to
        save the last 10 messages to send them to a normal web browser
        using the http protocol.
        Figure 2 shows an activity diagram. A whole life cycle of a client is
        Figure 2: Activity diagram of a whole client life cycle
        shown. After connecting the client can send and receive messages and
        then when he has finished he can disconnect.
        
        3.3 Design
        The system is divided into two main parts: one for the server and another
        one for the client. The core of each are classes called Client and Server.
        Both have to provide main functionality of a chat system. For the parts
        dependent on the type of connection interfaces or abstract classes have
        to be used, so the communication may easily be switched from the socket
        communication to RMI. In this apprach multicast will be used to find
        the server. The client emmits a multicast message. An example of a
        datagramm that is used to connect can be seen in figure 3. The name
        of the client that is connecting is displayed inverted. In the upper part 3.3 Design
        Figure 3: A multicast packet
        of the figure source and the destination IP addresses can be seen. This
        also shows that mulitcast is realised by using UDP. The only thing that
        makes the packet a multicast is the destination IP address. The only
        thing client and server have to know is the mulit cast group. This could
        also be realised by connecting to a socket using TCP, but then the client
        has to know the exact IP address of the server. The only disadvantage is
        that this system can only be used in an intra net because multicasts are
        not routed. So it is not possible for the client to connect to a server that
        is somewhere in the internet. After sending the multicast the client will
        create a server socket and wait for the server to connect to it. Here the
        roles of server and client are exchanged, but after the server has connected
        to the client (he knows the address from the arrived multicast package)
        both, client and server can get in- and output streams from the sockets
        and use the streams for sending and receiving. After the connection
        is established there is no difference between client and server. They
        both can use the streams equaly. For sending and receiving messages
        TCP sockets will be used because then server and clients can rely on
        the connection oriented protocol that is provided by TCP. If UDP or
        multicast would have been used here the programms would have to make
        sure that the messages are received by the other side. So a protocoll
        would have to be implemented, that is already provided by TCP.
        
        3.3.1 The Server Design
        To enable the clients to connect while other clients are already using the
        system, there has to be a part of the server, that is running in parallel,
        to accept connections. This will have to be implemented as a thread
        because once a server listens on a specified port, it will not return, until
        someone connects to this port. To submit the messages to all clients,
        
        3.3 Design
        references to all have to be stored inside the server. So it is possible
        to go through a list and send the message to each element of this list.
        Another approach is to start a thread for each connection. There is an
        advantage in using this, because if one of the client is stopped without
        disconnecting from the server the server would have to wait for a timeout.
        But since the messages have to be passed fast to the receivers for each
        Figure 4: Class diagram of the server
        client a thread is started. To save memory and the time for creating the
        threads some clients could be grouped in one thread. Then again if one
        of them is unproperly disconnected the others will have to wait before
        they get the message. But for a server where the clients are changing
        very much this could be useful. The class diagram figure 4 shows the
        Server that uses three types of threads to provide it’s functionality. The
        Registrar is used as a sort of portal to the server. The Clients have to
        connect to an object of this class. After this the Registrar will create a
        UserThread that is passed to the server to be stored in a list of clients.
        All this can be done independent of the type of connection. To enable
        communication using RMI there only has to be implemented the abstract
        methods in the classes Registrar and UserThead. The third part of the
        server is the thread for administrative purposes. This also has to be
        realized as a parallel thread to ensure communication while the server is
        administrated.
        
        3.3.2 The Client Design
        The client is realized easier compared to the server because it doesn’t
        have to do all the tasks the same time. The main part of the client
        is in the class Client. This class combines model, view and control
        in one class. That’s not a very good design, but since the application
        is very small an easier way can be chosen. The communication part is
        realized in the abstract class ServerConnection that provides the four
        methods for communication: register, send, receive and disconnect. A
        real ServerConnection has to implement those methods to meet the
        requirements of the communication type that is chosen. By using this
        
        3.4 Implementation
        Figure 5: Class diagram of the client
        abstract class it can be made sure, that the client is independent of the
        type of communication. So this part can be exchanged independent of
        the client. In figure 6 a whole sequence of the client connecting to the
        Figure 6: Sequence diagram of a client connection to the Server
        Server and receiving the reception message can be seen.
        3.4 Implementation
        Using the results of the previous sections it is no difficult task to implement
        the chat system.
        
        3.4.1 Client and Server
        The implementation of the server is the most time consuming task. Because
        three threads are running in parallel it has to be made sure that
        10
        3.4 Implementation
        they don’t interfere. It is very important not to mix the functionality
        of the chat server with the communication to stay independent of
        the type of communication. For sending and receiving messages socket
        communication is realized in this assignment. The server doesn’t use
        ServerSocket because it is easier if the clients start a ServerSocket
        after sending the multicast message to signal the server that it wants to
        connect. The server then connects to this ServerSocket. After establishing
        the connection there is no difference between both sides. Either
        side can send and receive. The client sends a newly generated messages.
        The server passes this message to all clients of which it has a reference
        saved in a Vector. The clients receive this message and display them
        using a JTextArea.
        
        3.4.2 The Administrator Client
        The client for administration doesn’t have to be implemented. For this
        task a normal web browser can be used. The server has to listen on
        a specified port, to send the last ten messages of the system to this
        client. Those messages have to be kept up to date by rotating them in
        an array of Strings. For the browser the data has to be sent by using
        http (hyper text transfer protocol) so it can display the data correctly.
        The server doesn’t use port 80 because this port is already used by a
        Figure 7: Output of the administration client
        apache web server running on the same machine. Instead port 9090
        is used. To get the last ten messages the address of the server has to
        be typed in the address field of a web browser. The output is shown
        in figure 7. If it is used locally the address has to be the following:
        http://localhost:9090.
        
#How to Run code

 You can run this program on Netbeans IDE in virtually created Ubuntu.

            You can download Netbeans from the given link:
            https://netbeans.org/downloads/8.0.2/
            
Kindly run the whole project in NetBeans IDE than firstly run the server side code. After that, run client side code and start conversing between each other.
