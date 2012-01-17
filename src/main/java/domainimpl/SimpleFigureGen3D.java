package domainimpl;

import domain.FigureType;

import java.util.List;

/**
 * User: alexander.lenkevich
 * Date: 1/17/12
 * Time: 6:37 PM
 */
public class SimpleFigureGen3D extends SimpleFigureGen {
    @Override
    protected List<FigureType> getFiguresToGen() {
        return FigureType.getFiguresToGen3D();
    }
}
