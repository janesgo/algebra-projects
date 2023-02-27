/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.networking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goran
 */
public class ServerRequest implements Runnable {

    private static final int PORT = 8888;

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                byte[] buffer = new byte[128];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                socket.receive(incoming);
                parseData(incoming);
            }
        } catch (IOException ex) {
            Logger.getLogger(DiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parseData(DatagramPacket packet) throws UnsupportedEncodingException {
        String character = "";
        String id;
        String action = "";

        byte[] data = packet.getData();
        if (data[0] == 1) {
            character = "Astronaut";
        }
        switch (data[2]) {
            case 1:
                action = "UP";
                break;
            case 2:
                action = "DOWN";
                break;
            case 3:
                action = "LEFT";
                break;
            case 4:
                action = "RIGHT";
                break;
        }
        System.out.println(character + "" + data[0] + "" + action);
    }

}
