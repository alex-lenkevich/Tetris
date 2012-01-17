package domainimpl.initializer;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 7:58 PM
 */
public class NatashaImageAreaInitializer extends ImageAreaInitializer {
    public NatashaImageAreaInitializer() {
        super(new int[][]{
                {2,0,2,0,0,3,0,0,4,4,4,0,0,5,0,0,7,0,7,0,7,0,0,8,0},
                {2,0,2,0,3,0,3,0,0,4,0,0,5,0,5,0,7,0,7,0,7,0,8,0,8},
                {2,2,2,0,3,0,3,0,0,4,0,0,5,0,5,0,7,0,7,0,7,0,8,0,8},
                {2,0,2,0,3,3,3,0,0,4,0,0,5,5,5,0,7,0,7,0,7,0,8,8,8},
                {2,0,2,0,3,0,3,0,0,4,0,0,5,0,5,0,7,7,7,7,7,0,8,0,8}
        });
    }
}
