package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import game.Game2D;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:23 PM
 */
public class Runner {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Game2D game2D = injector.getInstance(Game2D.class);
        game2D.start();
    }

}
