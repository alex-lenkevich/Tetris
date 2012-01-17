package domain;

import com.google.inject.ImplementedBy;
import domainimpl.SimpleMover;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:04 PM
 */
@ImplementedBy(SimpleMover.class)
public interface Mover {

    boolean moveEast(Area area);

    boolean moveWest(Area area);

    boolean rotate(Area area, Axis axi, Direction direction);

    boolean fall(Area area);

    void freeze(Area area);

    Figure getDropFigureForecast(final Area area);

    boolean move(final Area area, Axis axis, Direction direction);
}
