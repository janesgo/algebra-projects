/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

import hr.project.utils.AssetsUtils;
import java.util.Optional;
import javafx.scene.image.Image;

/**
 *
 * @author Goran
 */
public class AstronautFactory {

    private AstronautFactory() {
    }

    public static Optional<Astronaut> createAstronaut(int player) {
        AstronautType type = AstronautType.from(player);
        return Optional.of(new Astronaut(100, 200, type));
    }

}
