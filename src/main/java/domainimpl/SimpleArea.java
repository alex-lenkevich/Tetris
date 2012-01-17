package domainimpl;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import domain.*;
import domain.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:18 PM
 */
public class SimpleArea implements Area {

    private Set<Figure> figures = new HashSet<Figure>();
    private Figure active;

    public SimpleArea() {
        sizes = new HashMap<Axis, Integer>();
        sizes.put(Axis.X, 6);
        sizes.put(Axis.Y, 20);
        sizes.put(Axis.Z, 1);
    }

    @Override
    public void add(Figure figure) {
        figures.add(figure);
    }

    @Override
    public boolean contains(final domain.Point point, final Axis... ignoreAxises) {
        Collection<Axis> axises = Axis.invertAxisList(ignoreAxises);
        for(Axis axis : axises){
            switch (axis) {
                case X:
                    if(point.x < 0 || point.x >= getSize(Axis.X)) return true;
                    break;
                case Y:
                    if(point.y < 0) return true;
                    break;
                case Z:
                    if(point.z < 0 ||  point.z >= getSize(Axis.Z)) return true;
                    break;
            }
        }
        try{
            return Iterables.any(figures, new Predicate<Figure>() {
                @Override
                public boolean apply(Figure figure) {
                    return figure.contains(point, ignoreAxises);
                }
            });
        } catch (Exception e){
        }
        return false;
    }

    @Override
    public boolean contains(final domain.Point point) {
        return contains(point, new Axis[0]);
    }

    public Set<Figure> getFigures() {
        return figures;
    }

    @Override
    public boolean remove(Figure figure) {
        return figures.remove(figure);
    }

    @Override
    public void setWidth(int length) {
        sizes.put(Axis.X, length);
    }

    @Override
    public Color getColorAt(Line line, Direction direction) {
        return find(line, direction).getColor();
    }

    @Override
    public int getLevel(Line line, Direction direction) {
        return find(line, direction).getPoints().iterator().next().getValue(Axis.invertAxisList(line.getAxis1(), line.getAxis2()).iterator().next());
    }


    private Figure find(Line line, Direction direction) {
        for(Axis axis : Axis.invertAxisList(line.getAxis1(), line.getAxis2())){
            int size = getSize(axis);
            for(int i = direction.isForward() ? 0 : size - 1
                        ; direction.isForward() ? i < size : i >= 0
                    ; i = direction.isForward() ? i + 1 : i - 1  ){

                final Point point = new Point(ImmutableMap.of(
                        line.getAxis1(), line.getValue1(),
                        line.getAxis2(), line.getValue2(),
                        axis, i
                ));
                Predicate predicate = new Predicate<Figure>() {
                    @Override
                    public boolean apply(Figure figure) {
                        return figure.getPosition().equals(point);
                    }
                };
                if(Iterables.any(figures, predicate)){
                    return Iterables.find(figures, predicate);
                }
            }
        }
        throw new RuntimeException("Figure not found");
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
    
    private Map<Axis, Integer> sizes;

    @Override
    public int getSize(Axis axis) {
        return sizes.get(axis);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("-----------------------------\n");
        for (int y = 0; y < getSize(Axis.Y); y++) {
            for (int x = 0; x < getSize(Axis.X); x++) {
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
