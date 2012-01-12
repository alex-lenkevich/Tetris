package domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:18 PM
 */
public class SimpleArea implements Area {

    private Set<Figure> figures = new HashSet<Figure>();
    private Figure active;

    @Override
    public void add(Figure figure) {
        figures.add(figure);
    }

    @Override
    public boolean contains(final Point point) {
        return point.x < 0 || point.y < 0 || point.x >= getWidth() || Iterables.any(figures, new Predicate<Figure>() {
            @Override
            public boolean apply(Figure figure) {
                return figure.contains(point);
            }
        });
    }

    public Set<Figure> getFigures() {
        return figures;
    }

    @Override
    public boolean remove(Figure figure) {
        return figures.remove(figure);
    }

    @Override
    public int getWidth() {
        return 10;
    }

    @Override
    public int getHeight() {
        return 20;
    }

    @Override
    public Color getColorAt(final Point point) {
        return Iterables.find(figures, new Predicate<Figure>() {
            @Override
            public boolean apply(@Nullable Figure figure) {
                return figure.getPosition().equals(point);
            }
        }).getColor();
    }

    @Override
    public void setActive(Figure figure) {
        checkNotNull(figure);
        active = figure;
    }

    @Override
    public Figure getActive() {
        return active;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("-----------------------------\n");
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (active != null && active.contains(new Point(x, y))) {
                    b.append("A");
                } else {
                    b.append(contains(new Point(x, y)) ? "X" : " ");
                }

            }
            b.append("\n");
        }
        b.append("-----------------------------\n");

        return b.toString();
    }
}
