/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Goran
 */
public class GameState implements Externalizable {

    private List<Astronaut> astronauts = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private List<Laser> lasers = new ArrayList<>();

    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(astronauts);
        out.writeObject(asteroids);
        out.writeObject(lasers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        astronauts = (List<Astronaut>) in.readObject();
        asteroids = (List<Asteroid>) in.readObject();
        lasers = (List<Laser>) in.readObject();
    }

}
