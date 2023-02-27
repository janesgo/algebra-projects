/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.networking;

import hr.project.model.Asteroid;
import hr.project.model.Astronaut;
import hr.project.model.Laser;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author Goran
 */
public class Client {

    private static final int DISCOVERY_PORT = 9999;
    private static final int PORT = 8888;
    private static final String BROADCAST_ADDR = "255.255.255.255";
    private static final String REQUEST = "PING";
    private static final String REPLY = "PONG";

    private int id;

    private boolean serverFound = false;
    private InetAddress serverAddr;
    private Socket socket;
    private DatagramSocket udpSocket;
    private Pane pane;
    private Scene scene;
    private boolean end = false;

    private static final int LASER_ACCELERATION = 5;
    private static int score;
    private AnimationTimer timer;
    private List<Astronaut> astronauts = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private List<Laser> lasers = new ArrayList<>();

    private byte[] commands;
    DatagramPacket packet;

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public List<Laser> getLasers() {
        return lasers;
    }

    public void setLasers(List<Laser> lasers) {
        this.lasers = lasers;
    }

    public void init(Pane pnGame, Scene scene) {
        pane = pnGame;
        this.scene = scene;

        try (DatagramSocket discovery = new DatagramSocket()) {
            Thread findServer = new Thread(() -> {
                byte[] buffer = new byte[4];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                try {
                    discovery.receive(incoming);
                    if (new String(incoming.getData(), "UTF-8").equals(REPLY)) {
                        serverAddr = incoming.getAddress();
                        serverFound = true;
                        System.out.println(String.format("Server found at address %s:%s", incoming.getAddress().getHostAddress(), incoming.getPort()));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            findServer.setDaemon(true);
            findServer.start();

            while (!serverFound) {
                System.out.println("Searching server...");
                byte[] data = REQUEST.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(BROADCAST_ADDR), DISCOVERY_PORT);
                discovery.send(packet);
                Thread.sleep(500);
            }
            socket = new Socket(serverAddr, DISCOVERY_PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        initEvents();
        initSocket();
    }

    public void end() {
        end = true;
    }

    private void initEvents() {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    commands = new byte[]{1, (byte) id, 3};
                    packet = new DatagramPacket(commands, commands.length, serverAddr, PORT);
                    try {
                        udpSocket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case RIGHT:
                    commands = new byte[]{1, (byte) id, 4};
                    packet = new DatagramPacket(commands, commands.length, serverAddr, PORT);
                    try {
                        udpSocket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case UP:
                    commands = new byte[]{1, (byte) id, 1};
                    packet = new DatagramPacket(commands, commands.length, serverAddr, PORT);
                    try {
                        udpSocket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case DOWN:
                    commands = new byte[]{1, (byte) id, 2};
                    packet = new DatagramPacket(commands, commands.length, serverAddr, PORT);
                    try {
                        udpSocket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case SPACE:
                    break;
            }
        });
    }

    private void initSocket() {
        try {
            udpSocket = new DatagramSocket(8889);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
