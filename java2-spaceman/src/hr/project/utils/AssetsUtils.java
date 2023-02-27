/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.utils;

import hr.project.model.AsteroidType;
import hr.project.model.AstronautType;
import java.text.MessageFormat;
import javafx.scene.image.Image;

/**
 *
 * @author Goran
 */
public class AssetsUtils {

    private static final String ASSETS_PATH = "/hr/project/assets/";
    private static final String ASTRONAUT_FILENAME = "astronaut{0}.png";
    private static final String ASTEROID_FILENAME = "asteroid_{0}{1}.png";
    private static final String LASER_FILENAME = "laser_{0}.png";

    private AssetsUtils() {
    }

    public static Image loadAstronautImage(AstronautType type) {
        return new Image((ASSETS_PATH.concat(MessageFormat.format(ASTRONAUT_FILENAME, type.getValue()))));
    }

    public static Image loadAsteroidImage(AsteroidType asteroidType, int subtype) {
        return new Image(ASSETS_PATH.concat(MessageFormat
                .format(ASTEROID_FILENAME, asteroidType.name().toLowerCase(), subtype)));
    }

    public static Image loadLaserImage(AstronautType type) {
        return new Image(ASSETS_PATH.concat(MessageFormat.format(LASER_FILENAME, type.name().toLowerCase())));
    }

}
