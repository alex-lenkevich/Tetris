package domain;

import com.google.inject.ImplementedBy;

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

    Set<Figure> getFigures();

    boolean remove(Figure figure);

    void setActive(Figure figure);

    Figure getActive();

    int getWidth();

    int getHeight();

    void setWidth(int w);

    void setHeight(int h);

    Color getColorAt(Point point);

}
