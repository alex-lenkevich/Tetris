package domain;

import com.google.inject.ImplementedBy;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 6:55 PM
 */
@ImplementedBy(EmptyAreaInitializer.class)
public interface AreaInitializer {
    void init(Area area);
}
