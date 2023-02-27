/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.networking;

import java.net.Socket;

/**
 *
 * @author Goran
 */
public class PlayerThread extends Thread {

    private final Socket socket;

    public PlayerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
    }
}
