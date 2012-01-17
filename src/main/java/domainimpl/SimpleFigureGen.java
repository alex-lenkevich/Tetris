package domainimpl;

import domain.*;

import java.util.LinkedList;
import java.util.List;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:34 PM
 */
public abstract class SimpleFigureGen implements FigureGen {

    LinkedList<Figure> next = new LinkedList<Figure>();

    @Override
    public void genNewFigure(Area area) {
        genNewNext(area);
        area.setActive(next.getFirst());
        next.removeFirst();
        genNewNext(area);
    }

    @Override
    public List<Figure> getNext() {
        return next;
    }

    private void genNewNext(Area area) {
        while(next.size() < 3){
            int number = (int) Math.round(Math.random() * (getFiguresToGen().size() - 1));
            FigureType figureType = getFiguresToGen().get(number);
            Point p = new Point((area.getSize(Axis.X) - figureType.getRightBottomCorner().x) / 2, area.getSize(Axis.Y));
            next.addLast(new Figure(figureType, p));
        }
    }
    
    protected abstract List<FigureType> getFiguresToGen();

}
