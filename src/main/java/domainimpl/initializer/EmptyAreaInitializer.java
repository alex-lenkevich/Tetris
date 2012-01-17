package domainimpl.initializer;

import domain.Area;
import domain.AreaInitializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 6:56 PM
 */
public class EmptyAreaInitializer implements AreaInitializer {
    @Override
    public void init(Area area) {
        // nothing to do
    }
}
