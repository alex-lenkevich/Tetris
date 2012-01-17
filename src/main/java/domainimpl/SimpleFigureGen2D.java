package domainimpl;

import domain.FigureType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * User: alexander.lenkevich
 * Date: 1/17/12
 * Time: 6:37 PM
 */
public class SimpleFigureGen2D extends SimpleFigureGen {
    @Override
    protected List<FigureType> getFiguresToGen() {
        return FigureType.getFiguresToGen2D();
    }
}
