package domain;

import com.google.inject.Inject;

import java.util.List;

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
    private Scheduler scheduler;
    private AreaInitializer areaInitializer;

    public void start() {
        areaInitializer.init(area);
        figureGen.genNewFigure(area);
        scheduler.start();
        player.start();

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
        while (moveDown()) ;
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

    public List<Figure> getNextFigure() {
        return figureGen.getNext();
    }

    public void pause(){
        scheduler.setPause(true);
        player.update();
    }

    public void unPause(){
        scheduler.setPause(false);
        player.update();
    }

    public void switchPause(){
        if(scheduler.isPause()){
            unPause();
        } else {
            pause();
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    @Inject
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
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

    public AreaInitializer getAreaInitializer() {
        return areaInitializer;
    }

    @Inject
    public void setAreaInitializer(AreaInitializer areaInitializer) {
        this.areaInitializer = areaInitializer;
    }

    public void gameOver() {
        scheduler.isPause();
        player.gameOver();
    }

    public boolean isGameOver() {
        return gameOverChecker.isEnd(area);
    }

    public boolean isPause() {
        return !isGameOver() && scheduler.isPause();

    }

    public void removeBottomLine() {
        cleaner.removeLine(area, 0);
        player.update();
    }

    public void upSpeed(){
        scheduler.setSpeed(scheduler.getSpeed() + 1);
    }

    public void downSpeed(){
        if(scheduler.getSpeed() == 0) return;
        scheduler.setSpeed(scheduler.getSpeed() - 1);
    }


}
