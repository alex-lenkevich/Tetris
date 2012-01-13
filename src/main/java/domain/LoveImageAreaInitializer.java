package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 7:02 PM
 */
public class LoveImageAreaInitializer extends ImageAreaInitializer {
    public LoveImageAreaInitializer() {
        super(new int[][]{
                {1,0,0,0,1,1,1,0,1,0,1,0,1,1},
                {1,0,0,0,1,0,1,0,1,0,1,0,1,0},
                {1,0,0,0,1,0,1,0,1,0,1,0,1,1},
                {1,0,0,0,1,0,1,0,1,0,1,0,1,0},
                {1,1,1,0,1,1,1,0,0,1,0,0,1,1}
        });
    }
}
