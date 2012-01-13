package domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:04 PM
 */
public class SimpleMover implements Mover {

    @Override
    public boolean moveLeft(final Area area) {
        checkNotNull(area);
        Figure figure = area.getActive();
        Point newPosition = figure.getPosition().minus(new Point(1, 0));
        return getMovePossibilityAndMove(area, figure, newPosition);
    }


    @Override
    public boolean moveRight(Area area) {
        checkNotNull(area);
        Figure figure = area.getActive();
        Point newPosition = figure.getPosition().plus(new Point(1, 0));
        return getMovePossibilityAndMove(area, figure, newPosition);
    }

    @Override
    public boolean fall(Area area) {
        checkNotNull(area);
        Figure figure = area.getActive();
        checkNotNull(figure);
        Point newPosition = figure.getPosition().minus(new Point(0, 1));
        return getMovePossibilityAndMove(area, figure, newPosition);
    }

    @Override
    public void freeze(Area area) {
        for (Point p : area.getActive().getPoints()) {
            area.add(new Figure(FigureType.P, p, FigureOrient.UP, area.getActive().getColor()));
        }
    }

    @Override
    public boolean rotate(Area area) {
        Figure figure = area.getActive();
        FigureOrient newOrient = figure.getOrient().getNext();
        return getMovePossibilityAndMove(area, figure, newOrient);
    }

    private boolean getMovePossibilityAndMove(final Area area, Figure figure, Point newPosition) {
        return getMovePossibilityAndMove(area, figure, newPosition, figure.getOrient());
    }

    private boolean getMovePossibilityAndMove(final Area area, Figure figure, FigureOrient newOrient) {
        return getMovePossibilityAndMove(area, figure, figure.getPosition(), newOrient);
    }


    private boolean getMovePossibilityAndMove(final Area area, Figure figure, Point newPosition, FigureOrient newOrient) {
        Figure newFigure = new Figure(figure.getType(), newPosition, newOrient);

        if (Iterables.all(newFigure.getPoints(), new Predicate<Point>() {
            @Override
            public boolean apply(@Nullable Point point) {
                return !area.contains(point);
            }
        })) {
            figure.setPosition(newPosition);
            figure.setOrient(newOrient);
            return true;
        }
        return false;
    }

}
