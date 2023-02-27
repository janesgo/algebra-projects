/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.networking;

import hr.project.model.Asteroid;
import hr.project.model.AsteroidFactory;
import hr.project.model.AstronautFactory;
import hr.project.model.GameState;
import hr.project.model.Laser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;

/**
 *
 * @author Goran
 */
public class Server implements Runnable {

    private static int PLAYER_NR = 1;
    private static final int PORT = 9999;
    private static final int CLIENT_PORT = 8889;
    private static final List<Socket> connections = new ArrayList<>();
    private static int WIDTH = 800;

    private static final int LASER_ACCELERATION = 5;
    private static int scoreFirst;
    private static int scoreSecond;
    private AnimationTimer timer;
    private DatagramPacket packet;
    private DatagramSocket socket;
    private GameState gameState = new GameState();
    private byte[] data;

    private boolean leftPressed, rightPressed, upPressed, downPressed;

    @Override
    public void run() {
        try (ServerSocket socket = new ServerSocket(PORT)) {
            System.out.println(String.format("Starting server at %s:%d", socket.getInetAddress().getHostAddress(), PORT));
            startDiscovery(socket);
            listen();
            initCharacters();
            initTimer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startDiscovery(ServerSocket socket) throws IOException {
        DiscoveryThread discoveryThread = DiscoveryThread.getInstance();
        discoveryThread.setDaemon(true);
        discoveryThread.start();

        System.out.println("Listening for connections...");
        while (connections.size() < 1) {
            Socket connection = socket.accept();
            connections.add(connection);
            System.out.println(String.format("Player %d (%s:%d) connected", PLAYER_NR++, connection.getInetAddress().getHostName(), connection.getPort()));
            PlayerThread playerThread = new PlayerThread(connection);
            playerThread.setDaemon(true);
            playerThread.start();
            System.out.println("All players connected. Starting game...");
        }
    }

    private void listen() {
        Thread serverRequest = new Thread(new ServerRequest());
        serverRequest.setDaemon(true);
        serverRequest.start();
    }

    private void initCharacters() {
        gameState.getAstronauts().add(AstronautFactory.createAstronaut(1).get());
        gameState.getAstronauts().add(AstronautFactory.createAstronaut(2).get());
        connections.forEach(c -> {
            data = getGameStateBytes();
            packet = new DatagramPacket(data, data.length, c.getInetAddress(), PORT);
            try {
                socket.send(packet);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void initTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameState.getAsteroids().forEach(a -> {
                    a.move();
                    connections.forEach(c -> {
                        data = getGameStateBytes();
                        packet = new DatagramPacket(data, data.length, c.getInetAddress(), PORT);
                        try {
                            socket.send(packet);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });

                gameState.getLasers().forEach(l -> {
                    l.move();
                    connections.forEach(c -> {
                        data = getGameStateBytes();
                        packet = new DatagramPacket(data, data.length, c.getInetAddress(), PORT);
                        try {
                            socket.send(packet);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });

                removeInvisible();

                if (Math.random()
                        < 0.015) {
                    try {
                        addAsteroid();
                        connections.forEach(c -> {
                            data = getGameStateBytes();
                            packet = new DatagramPacket(data, data.length, c.getInetAddress(), PORT);
                            try {
                                socket.send(packet);
                            } catch (IOException ex) {
                                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

    private byte[] getGameStateBytes() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(gameState);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new byte[]{};
    }

    private void addAsteroid() throws Exception {
        Optional<Asteroid> asteroid = AsteroidFactory.createAsteroid();

        if (!asteroid.isPresent()) {
            throw new Exception("Error adding asteroid");
        }

        addAsteroidCollisionListener(asteroid);

        if (gameState.getAsteroids().isEmpty() || !gameState.getAsteroids().stream().anyMatch(a -> asteroid.get().collide(a))) {
            gameState.getAsteroids().add(asteroid.get());
        }
    }

    private void addAsteroidCollisionListener(Optional<Asteroid> asteroid) {
        asteroid.get().getShape().boundsInParentProperty().addListener(
                new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {

            }
        });
    }

    private void removeInvisible() {
        gameState.getAsteroids().removeAll(gameState.getAsteroids().stream()
                .filter(a -> a.isInvisible())
                .collect(Collectors.toList()));
        gameState.getLasers().removeAll(gameState.getLasers().stream()
                .filter(a -> a.isInvisible())
                .collect(Collectors.toList()));
    }

    private void addLaserCollisionListener(Laser laser) {
        laser.getShape().boundsInParentProperty().addListener(
                new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                gameState.getAsteroids().forEach(a -> {
                    if (a.collide(laser)) {
                        a.setInvisibility(true);
                        laser.setInvisibility(true);
                        //setScore(a.getPoints());
                    }
                });
            }
        });
    }
}
