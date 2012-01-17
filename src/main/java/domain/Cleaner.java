package domain;

import com.google.inject.ImplementedBy;
import domainimpl.SimpleCleaner;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:41 PM
 */
@ImplementedBy(SimpleCleaner.class)
public interface Cleaner {
    int clear(Area area);

    void removeLine(Area area, int i);
}
