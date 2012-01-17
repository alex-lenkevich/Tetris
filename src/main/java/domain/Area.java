package domain;

import com.google.inject.ImplementedBy;
import domainimpl.SimpleArea;

import java.awt.*;
import java.util.Set;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:18 PM
 */
@ImplementedBy(SimpleArea.class)
public interface Area {

    void add(Figure figure);

    boolean contains(Point point);

    boolean contains(Point point, Axis... ignoreAxises);

    Set<Figure> getFigures();

    boolean remove(Figure figure);

    void setActive(Figure figure);

    Figure getActive();

    int getSize(Axis axis);

    void setWidth(int length);

    Color getColorAt(Line line, Direction direction);

    int getLevel(Line line, Direction direction);
}
