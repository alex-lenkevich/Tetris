package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 6:10 PM
 */
public class SimpleScore implements Score {
    
    private int score = 0;
    private int lines = 0;

    @Override
    public void figureDrop(int lineRemoveCount) {
        switch (lineRemoveCount){
            case 4 :
                score += 4;
            case 3 :
                score += 3;
            case 2 :
                score += 2;
            case 1 :
                score += 1;
        }
        lines += lineRemoveCount;
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
