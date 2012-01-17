package domain;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:19 PM
 */
public class Figure implements Cloneable {

    private FigureType type;
    private Projection projection;
    private Point position;
    private Color color;

    public Figure(FigureType type, Point position) {
        this(type, position, new Projection(), type.getDefaultColor());
    }

    public Figure(FigureType type, Point position, Projection projection) {
        this(type, position, projection, type.getDefaultColor());
    }

    public Figure(FigureType type, Point position, Color color) {
        this(type, position, new Projection(), color);
    }

    public Figure(FigureType type, Point position, Projection projection, Color color) {
        checkNotNull(type);
        checkNotNull(position);
        checkNotNull(projection);
        checkNotNull(color);

        this.projection = projection;
        this.position = position;
        this.type = type;
        this.color = color;
    }

    public boolean contains(Point point, Axis... ignoreAxises) {
        Collection<Axis> axises = Axis.invertAxisList(ignoreAxises);
        for (Point comparePoint : getPoints()) {
            boolean equals = true;
            for (Axis axis : axises) {
                equals &= comparePoint.getValue(axis) == point.getValue(axis);
            }
            if (equals) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return type.toString() + ": " + position.toString() + ":" + projection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        return !(position != null ? !position.equals(figure.position) : figure.position != null) && type == figure.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    public Collection<Point> getPoints() {
        Set<Point> points = type.getPoints();

        Function<Point, Point> convert = new Function<Point, Point>() {
            @Override
            public Point apply(Point point) {
                Point corn = type.getRightBottomCorner();
                Point newPoint = new Point(
                        projection.hWay.getDirection().isForward()
                        ? point.getValue(projection.hWay.getAxis())
                        : corn.getValue(projection.hWay.getAxis()) - point.getValue(projection.hWay.getAxis()),

                       projection.vWay.getDirection().isForward()
                        ? point.getValue(projection.vWay.getAxis())
                        : corn.getValue(projection.vWay.getAxis()) - point.getValue(projection.vWay.getAxis()),

                        projection.viewWay.getDirection().isForward()
                        ? point.getValue(projection.viewWay.getAxis())
                        : corn.getValue(projection.viewWay.getAxis()) - point.getValue(projection.viewWay.getAxis())

                );
                return newPoint.plus(position);
            }
        };

        return ImmutableSet.copyOf(Collections2.transform(points, convert));
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public FigureType getType() {
        return type;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Figure clone() {
        return new Figure(type, position, projection, color);
    }
}
