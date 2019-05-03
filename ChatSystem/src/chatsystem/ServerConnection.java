/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

/**
 *
 * @author Lucky
 */
abstract class ServerConnection {

    abstract void register();

    abstract void send(String s);

    abstract String Recieve();

    abstract boolean DisConnect();

}
