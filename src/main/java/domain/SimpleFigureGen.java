package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:34 PM
 */
public class SimpleFigureGen implements FigureGen {

    Figure next;

    @Override
    public void genNewFigure(Area area) {
        if(next == null) genNewNext(area);
        area.setActive(next);
        genNewNext(area);
    }

    @Override
    public Figure getNext() {
        return next;
    }

    private void genNewNext(Area area){
        Point p = new Point(area.getWidth() / 2, area.getHeight());
        int number = (int) Math.round(Math.random() * (FigureType.getFiguresToGen().size() - 1));
        FigureType figureType = FigureType.getFiguresToGen().get(number);
        next = new Figure(figureType, p, FigureOrient.UP);
    }

}
