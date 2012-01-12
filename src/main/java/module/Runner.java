package module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.*;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:23 PM
 */
public class Runner {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Game game = injector.getInstance(Game.class);
        game.start();
    }

}
