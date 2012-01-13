package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 8:11 PM
 */
public class OksanaImageAreaInitializer extends ImageAreaInitializer {
    public OksanaImageAreaInitializer() {
        super(new int[][]{
                {0,5,0,0,2,0,2,0,0,3,0,0,0,4,0,0,7,0,7,0,0,8,0},
                {5,0,5,0,2,0,2,0,3,0,3,0,4,0,4,0,7,0,7,0,8,0,8},
                {5,0,5,0,2,2,0,0,3,0,0,0,4,0,4,0,7,7,7,0,8,0,8},
                {5,0,5,0,2,0,2,0,3,0,3,0,4,4,4,0,7,0,7,0,8,8,8},
                {0,5,0,0,2,0,2,0,0,3,0,0,4,0,4,0,7,0,7,0,8,0,8}
        });
    }
}
