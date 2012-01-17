package domain;

import com.google.inject.ImplementedBy;
import domainimpl.SimpleGameOverChecker;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:26 PM
 */
@ImplementedBy(SimpleGameOverChecker.class)
public interface GameOverChecker {
    boolean isEnd(Area area);
}
