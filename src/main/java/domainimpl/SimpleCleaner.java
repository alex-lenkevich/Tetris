package domainimpl;

import domain.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:41 PM
 */
public class SimpleCleaner implements Cleaner {
    @Override
    public int clear(final Area area) {
        int c = 0;
        for (int y = 0; y < area.getSize(Axis.Y); y++) {
            boolean cleanNeeded = true;
            for (int x = 0; x < area.getSize(Axis.X); x++) {
                if (!area.contains(new Point(x, y))) {
                    cleanNeeded = false;
                }
            }
            if (cleanNeeded) {
                removeLine(area, y);
                c++;
                y--;
            }
        }
        return c;
    }

    public void removeLine(Area area, int i){
        Iterator<Figure> iterator = area.getFigures().iterator();
        Set<Figure> set = new HashSet<Figure>();
        while (iterator.hasNext()) {
            Figure figure = iterator.next();
            if (figure.getPosition().y != i) {
                set.add(figure);
            }
        }
        area.getFigures().clear();
        area.getFigures().addAll(set);
        trim(area);
    }

    public void trim(Area area){
        for (int y = 0; y < area.getSize(Axis.Y); y++) {
            boolean isEmpty = true;
            for (int x = 0; x < area.getSize(Axis.X); x++) {
                for (int z = 0; z < area.getSize(Axis.Z); z++) {
                    if (area.contains(new Point(x, y, z))) {
                        isEmpty = false;
                    }
                }
            }
            if (isEmpty) {
                boolean moved = false;
                for (Figure figure : area.getFigures()) {
                    if (figure.getPosition().y > y) {
                        figure.setPosition(figure.getPosition().minus(new Point(0, 1)));
                        moved = true;
                    }
                }
                if (moved) y--;
            }
        }
    }
}
