package domainimpl;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import domain.*;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:04 PM
 */
public class SimpleMover implements Mover {

    @Override
    public boolean moveEast(final Area area) {
        checkNotNull(area);
        Figure figure = area.getActive();
        Point newPosition = figure.getPosition().minus(new Point(1, 0));
        return getMovePossibilityAndMove(area, figure, newPosition);
    }


    @Override
    public boolean moveWest(Area area) {
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
            area.add(new Figure(FigureType.P, p, area.getActive().getColor()));
        }
    }

    @Override
    public boolean rotate(Area area, Axis axis, Direction direction) {
        Figure figure = area.getActive();
        Projection newProjection = figure.getProjection().rotate(axis, direction);
        return getMovePossibilityAndMove(area, figure, newProjection);
    }

    private boolean getMovePossibilityAndMove(final Area area, Figure figure, Point newPosition) {
        return getMovePossibilityAndMove(area, figure, newPosition, figure.getProjection());
    }

    private boolean getMovePossibilityAndMove(final Area area, Figure figure, Projection newProjection) {
        return getMovePossibilityAndMove(area, figure, figure.getPosition(), newProjection);
    }


    private boolean getMovePossibilityAndMove(final Area area, Figure figure, Point newPosition, Projection newOrient) {
        Figure newFigure = new Figure(figure.getType(), newPosition, newOrient);

        if (Iterables.all(newFigure.getPoints(), new Predicate<Point>() {
            @Override
            public boolean apply(@Nullable Point point) {
                return !area.contains(point);
            }
        })) {
            figure.setPosition(newPosition);
            figure.setProjection(newOrient);
            return true;
        }
        return false;
    }

    public Figure getDropFigureForecast(final Area area){
        Figure active = area.getActive();
        Figure forecast = active.clone();
        Point newPosition;
        do {
             newPosition = forecast.getPosition().minus(new Point(0, 1));
        } while (getMovePossibilityAndMove(area, forecast, newPosition));
        return forecast;
    }

    @Override
    public boolean move(final Area area, Axis axis, Direction direction) {
        checkNotNull(area);
        Figure figure = area.getActive();
        checkNotNull(figure);
        Point newPosition = figure.getPosition().plus(new Point(ImmutableMap.of(axis, (direction.isForward() ? 1 : -1))));
        return getMovePossibilityAndMove(area, figure, newPosition);
    }

}
