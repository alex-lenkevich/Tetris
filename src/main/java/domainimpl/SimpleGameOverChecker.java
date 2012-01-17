package domainimpl;

import domain.Area;
import domain.Axis;
import domain.GameOverChecker;
import domain.Point;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:07 PM
 */
public class SimpleGameOverChecker implements GameOverChecker {
    @Override
    public boolean isEnd(Area area) {
        return area.contains(new Point(0, area.getSize(Axis.Y), 0), Axis.X, Axis.Z);
    }
}
