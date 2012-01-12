package domain;

import com.google.inject.Inject;

import java.io.StreamCorruptedException;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:08 PM
 */
public class Game {

    private Player player;
    private Area area;
    private Cleaner cleaner;
    private Mover mover;
    private GameOverChecker gameOverChecker;
    private FigureGen figureGen;
    private Score score;

    public void start() {
        figureGen.genNewFigure(area);
        player.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!gameOverChecker.isEnd(area)) {
                    try {
                        Thread.sleep(600);
                        fall();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void moveLeft() {
        mover.moveLeft(area);
        player.update();
    }

    public void moveRight() {
        mover.moveRight(area);
        player.update();
    }

    public void rotate() {
        mover.rotate(area);
        player.update();
    }

    public void fall() {
        boolean free = moveDown();
        player.update();
    }

    public void drop() {
        while (moveDown());
        player.update();
    }

    private boolean moveDown() {
        boolean free = mover.fall(area);
        if (!free) {
            mover.freeze(area);
            figureGen.genNewFigure(area);
            score.figureDrop(cleaner.clear(area));
        }
        return free;
    }

    public Figure getNextFigure(){
        return figureGen.getNext();
    }

    @Inject
    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    @Inject
    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public Player getPlayer() {
        return player;
    }

    @Inject
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    @Inject
    public void setCleaner(Cleaner cleaner) {
        this.cleaner = cleaner;
    }

    public Mover getMover() {
        return mover;
    }

    @Inject
    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public GameOverChecker getGameOverChecker() {
        return gameOverChecker;
    }

    @Inject
    public void setGameOverChecker(GameOverChecker gameOverChecker) {
        this.gameOverChecker = gameOverChecker;
    }

    public FigureGen getFigureGen() {
        return figureGen;
    }

    @Inject
    public void setFigureGen(FigureGen figureGen) {
        this.figureGen = figureGen;
    }



}
