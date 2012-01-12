package domain;

import com.google.inject.ImplementedBy;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 6:10 PM
 */
@ImplementedBy(SimpleScore.class)
public interface Score {

    void figureDrop(int lineRemoveCount);
    int getScore();
    int getLines();

}
