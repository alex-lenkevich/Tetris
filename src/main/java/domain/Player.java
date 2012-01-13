package domain;

import com.google.inject.ImplementedBy;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:13 PM
 */
@ImplementedBy(SimplePlayer.class)
public interface Player {

    void start();

    void update();

    void gameOver();
}
