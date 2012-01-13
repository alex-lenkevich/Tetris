package domain;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:32 PM
 */
@ImplementedBy(SimpleFigureGen.class)
public interface FigureGen {
    void genNewFigure(Area area);

    List<Figure> getNext();

}
