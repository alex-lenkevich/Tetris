package domain;

import com.google.inject.ImplementedBy;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:04 PM
 */
@ImplementedBy(SimpleMover.class)
public interface Mover {
    boolean moveLeft(Area area);

    boolean moveRight(Area area);

    boolean fall(Area area);

    void freeze(Area area);

    boolean rotate(Area area);

    Figure getDropFigureForecast(final Area area);

}
