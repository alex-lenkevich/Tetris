package domain;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import java.awt.*;
import java.util.Collection;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:19 PM
 */
public class Figure {

    private FigureType type;
    private FigureOrient orient;
    private Point position;
    private Color color;

    public Figure(FigureType type, Point position) {
        this(type, position, FigureOrient.UP, type.getDefaultColor());
    }

    public Figure(FigureType type, Point position, FigureOrient orient) {
        this(type, position, orient, type.getDefaultColor());
    }

    public Figure(FigureType type, Point position, Color color) {
        this(type, position, FigureOrient.UP, color);
    }

    public Figure(FigureType type, Point position, FigureOrient orient, Color color) {
        checkNotNull(type);
        checkNotNull(position);
        checkNotNull(orient);
        checkNotNull(color);

        this.orient = orient;
        this.position = position;
        this.type = type;
        this.color = color;
    }

    public boolean contains(Point point) {
        return Iterables.contains(getPoints(), point);
    }

    @Override
    public String toString() {
        return type.toString() + ": " + position.toString() + ":" + orient;
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
        switch (orient) {
            case UP:
                return ImmutableSet.copyOf(Collections2.transform(points, new Function<Point, Point>() {
                    @Override
                    public Point apply(Point point) {
                        return point.plus(position);
                    }
                }));
            case LEFT:
                return ImmutableSet.copyOf(Collections2.transform(points, new Function<Point, Point>() {
                    @Override
                    public Point apply(Point point) {
                        Point corn = type.getRightBottomCorner();
                        return new Point(point.y, corn.x - point.x).plus(position);
                    }
                }));
            case DOWN:
                return ImmutableSet.copyOf(Collections2.transform(points, new Function<Point, Point>() {
                    @Override
                    public Point apply(Point point) {
                        Point corn = type.getRightBottomCorner();
                        return new Point(corn.x - point.x, corn.y - point.y).plus(position);
                    }
                }));
            case RIGHT:
                return ImmutableSet.copyOf(Collections2.transform(points, new Function<Point, Point>() {
                    @Override
                    public Point apply(Point point) {
                        Point corn = type.getRightBottomCorner();
                        return new Point(corn.y - point.y, point.x).plus(position);
                    }
                }));
        }
        throw new sun.reflect.generics.reflectiveObjects.NotImplementedException();
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

    public FigureOrient getOrient() {
        return orient;
    }

    public void setOrient(FigureOrient orient) {
        this.orient = orient;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
