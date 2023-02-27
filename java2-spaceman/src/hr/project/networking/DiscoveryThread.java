/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goran
 */
public class DiscoveryThread extends Thread {

    private static final int PORT = 9999;
    private static final String REQUEST = "PING";
    private static final String REPLY = "PONG";
    private static final DiscoveryThread INSTANCE = new DiscoveryThread();

    private DiscoveryThread() {
    }

    public static DiscoveryThread getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Starting discovery service...");
            socket.setBroadcast(true);
            while (true) {
                byte[] buffer = new byte[4];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                socket.receive(incoming);
                if (new String(incoming.getData(), "UTF-8").equals(REQUEST)) {
                    System.out.println("Server address requested");
                    buffer = REPLY.getBytes();
                    DatagramPacket outgoing = new DatagramPacket(buffer, buffer.length, incoming.getAddress(), incoming.getPort());
                    socket.send(outgoing);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
