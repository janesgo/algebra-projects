/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.project.model;

import hr.project.utils.AssetsUtils;
import java.util.Optional;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Goran
 */
public class AsteroidFactory {

    private static final Random RND = new Random();

    private static final Double[] B1 = new Double[]{16.0, 0.0, 74.0, 0.0, 100.0, 40.0, 84.0, 75.0, 60.0, 72.0, 29.0, 83.0, 0.0, 52.0};
    private static final Double[] B2 = new Double[]{19.0, 8.0, 65.0, 0.0, 119.0, 90.0, 106.0, 67.0, 51.0, 82.0, 33.0, 97.0, 5.0, 74.0, 0.0, 43.0};
    private static final Double[] B3 = new Double[]{0.0, 21.0, 34.0, 0.0, 73.0, 11.0, 88.0, 41.0, 82.0, 71.0, 16.0, 75.0, 1.0, 55.0};
    private static final Double[] B4 = new Double[]{13.0, 14.0, 65.0, 0.0, 97.0, 35.0, 79.0, 90.0, 28.0, 95.0, 0.0, 58.0};
    private static final Double[] M1 = new Double[]{0.0, 17.0, 11.0, 1.0, 39.0, 2.0, 42.0, 28.0, 21.0, 42.0, 6.0, 36.0};
    private static final Double[] M2 = new Double[]{0.0, 24.0, 6.0, 5.0, 31.0, 1.0, 44.0, 17.0, 29.0, 39.0, 13.0, 38.0};
    private static final Double[] S1 = new Double[]{0.0, 11.0, 7.0, 0.0, 24.0, 1.0, 26.0, 19.0, 14.0, 27.0, 3.0, 22.0};
    private static final Double[] S2 = new Double[]{0.0, 16.0, 4.0, 3.0, 20.0, 0.0, 27.0, 11.0, 19.0, 25.0, 8.0, 24.0};
    private static final Double[] T1 = new Double[]{0.0, 9.0, 7.0, 0.0, 17.0, 4.0, 14.0, 16.0, 5.0, 16.0};
    private static final Double[] T2 = new Double[]{0.0, 7.0, 6.0, 0.0, 15.0, 4.0, 10.0, 14.0};

    private AsteroidFactory() {
    }

    public static Optional<Asteroid> createAsteroid() {
        try {
            Polygon shape = new Polygon();

            AsteroidType asteroidType
                    = AsteroidType.from(RND.nextInt(AsteroidType.values().length) + 1);

            int subType = RND.nextInt(asteroidType.getSubtype()) + 1;
            switch (asteroidType) {
                case BIG:
                    switch (subType) {
                        case 1:
                            shape.getPoints().addAll(B1);
                            break;
                        case 2:
                            shape.getPoints().addAll(B2);
                            break;
                        case 3:
                            shape.getPoints().addAll(B3);
                            break;
                        default:
                            shape.getPoints().addAll(B4);
                            break;
                    }
                    break;
                case MEDIUM:
                    if (subType == 1) {
                        shape.getPoints().addAll(M1);
                    } else {
                        shape.getPoints().addAll(M2);
                    }
                    break;
                case SMALL:
                    if (subType == 1) {
                        shape.getPoints().addAll(S1);
                    } else {
                        shape.getPoints().addAll(S2);
                    }
                    break;
                case TINY:
                    if (subType == 1) {
                        shape.getPoints().addAll(T1);
                    } else {
                        shape.getPoints().addAll(T2);
                    }
                    break;
                default:
                    throw new AssertionError(asteroidType.name());
            }

            return Optional.of(new Asteroid(shape, asteroidType, 800, RND.nextInt(600) + 1, 1));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
