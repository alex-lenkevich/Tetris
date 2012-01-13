package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 6:10 PM
 */
public class SimpleScore implements Score {

    private int score = 0;
    private int lines = 0;
    private int speed = 0;

    @Override
    public void figureDrop(int lineRemoveCount) {
        score += getSumTo(lineRemoveCount);
        lines += lineRemoveCount;
    }
    
    private int getSumTo(int i){
        return i == 0 ? 0 : getSumTo(i - 1) + i;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getLines() {
        return lines;
    }

}
