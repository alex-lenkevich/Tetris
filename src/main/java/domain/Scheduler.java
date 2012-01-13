package domain;

import com.google.inject.Inject;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 4:31 PM
 */
public class Scheduler extends Thread {

    Game game;
    private boolean pause;
    private int speed = 0;

    public void run() {
        while (!game.getGameOverChecker().isEnd(game.getArea())) {
            try {
                Thread.sleep(500 / (speed + 1));
                if (!pause) {
                    game.fall();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.gameOver();
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    int getSpeed() {
        return speed;
    }

    void setSpeed(int speed){
        if(speed < 0) throw new IllegalArgumentException("speed must by grate then 0");
        this.speed = speed;
    }

    @Inject
    public void setGame(Game game) {
        this.game = game;
    }



}
