package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:07 PM
 */
public class SimpleGameOverChecker implements GameOverChecker {
    @Override
    public boolean isEnd(Area area) {
        for(int x = 0 ; x < area.getWidth() ; x ++){
            if(area.contains(new Point(x, area.getHeight()))) {
                return true;
            }
        }
        return false;
    }
}
