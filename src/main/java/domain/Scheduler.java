package domain;

import com.google.inject.Inject;
import game.Game2D;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 4:31 PM
 */
public class Scheduler extends Thread {

    Game2D game2D;
    private boolean pause;
    private int speed = 0;

    public void run() {
        while (!game2D.getGameOverChecker().isEnd(game2D.getArea())) {
            try {
                Thread.sleep(500 / (speed + 1));
                if (!pause) {
                    game2D.fall();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game2D.gameOver();
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) throw new IllegalArgumentException("speed must by grate then 0");
        this.speed = speed;
    }

    @Inject
    public void setGame2D(Game2D game2D) {
        this.game2D = game2D;
    }


}
