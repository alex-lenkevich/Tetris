package domain;

import com.google.inject.ImplementedBy;
import player.PlayerSwing2D;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:13 PM
 */
@ImplementedBy(PlayerSwing2D.class)
public interface Player {

    void start();

    void update();

    void gameOver();
}
