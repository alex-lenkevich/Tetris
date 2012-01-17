package player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.util.Function;
import domain.Line;
import domain.Projection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:02 PM
 */
public class PlayerSwing3D extends AbstractPlayerSwing {

    public PlayerSwing3D() {
        mainPanels = ImmutableList.of(
                new MainPanel(new Projection()),
                new MainPanel(new Projection().up()),
                new MainPanel(new Projection().right())
        );

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_NUMPAD4:
                        game.move(projection.hWay.getAxis(), projection.hWay.getDirection().getNext());
                        break;
                    case VK_NUMPAD6:
                        game.move(projection.hWay.getAxis(), projection.hWay.getDirection());
                        break;
                    case VK_NUMPAD2:
                        game.move(projection.viewWay.getAxis(), projection.viewWay.getDirection().getNext());
                        break;
                    case VK_NUMPAD8:
                        game.move(projection.viewWay.getAxis(), projection.viewWay.getDirection());
                        break;
                    case VK_NUMPAD9:
                        System.out.println("9");
                        game.move(projection.vWay.getAxis(), projection.vWay.getDirection());
                        break;
                    case VK_NUMPAD3:
                        game.move(projection.vWay.getAxis(), projection.vWay.getDirection().getNext());
                        break;


                    case VK_W:
                        game.rotate(projection.hWay.getAxis(), projection.hWay.getDirection().getNext());
                        break;
                    case VK_S:
                        game.rotate(projection.hWay.getAxis(), projection.hWay.getDirection());
                        break;
                    case VK_A:
                        game.rotate(projection.vWay.getAxis(), projection.vWay.getDirection());
                        break;
                    case VK_D:
                        game.rotate(projection.vWay.getAxis(), projection.vWay.getDirection().getNext());
                        break;
                    case VK_Q:
                        game.rotate(projection.viewWay.getAxis(), projection.viewWay.getDirection());
                        break;
                    case VK_E:
                        game.rotate(projection.viewWay.getAxis(), projection.viewWay.getDirection().getNext());
                        break;


//                    case VK_PAGE_UP:
//                        game.upSpeed();
//                        break;
//                    case VK_PAGE_DOWN:
//                        game.downSpeed();
//                        break;
                    case VK_INSERT:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.up();
                            }
                        });
                        break;
                    case VK_DELETE:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.down();
                            }
                        });
                        break;
                    case VK_HOME:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.left();
                            }
                        });
                        break;
                    case VK_END:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.right();
                            }
                        });
                        break;
                    case VK_PAGE_UP:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.rotateLeft();
                            }
                        });
                        break;
                    case VK_PAGE_DOWN:
                        updateProjection(new Function<Projection, Projection>() {
                            @Override
                            public Projection apply(Projection projection) {
                                return projection.rotateRight();
                            }
                        });
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    protected void drawFigures(Graphics g, int x, int y) {

        int maxV = game.getArea().getSize(projection.vWay.getAxis());
        boolean forwardV = projection.vWay.getDirection().isForward();

        int maxH = game.getArea().getSize(projection.hWay.getAxis());
        boolean forwardH = projection.hWay.getDirection().isForward();

        Color back = Color.BLACK;

        domain.Point point = new domain.Point(ImmutableMap.of(projection.hWay.getAxis(), x, projection.vWay.getAxis(), y));
        Line line = new Line(projection.hWay.getAxis(), x, projection.vWay.getAxis(), y);
        int level = -1;
        if(game.getArea().contains(point, projection.viewWay.getAxis())){
            level = game.getArea().getLevel(line, projection.viewWay.getDirection());
//                        g.setColor(area.getColorAt(line, projection.viewWay.getDirection()));
            g.setColor(new Color(0x222222 + 0x050505 * (level)));
        } else {
            g.setColor(back);
        }


        int realY = forwardV ? maxV - y - 1 : y;
        int realX = forwardH ? x : maxH - x - 1;


        g.fillRect(realX * SIZE, realY * SIZE, SIZE, SIZE);

        if(level >= 0){
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 18));
            g.setColor(Color.GREEN);


            FontMetrics metrics = g.getFontMetrics();
            int fontHeight = metrics.getHeight();
            int textWidth = metrics.stringWidth(String.valueOf(level));
            System.out.println(String.valueOf(level) + "-" + (realX * SIZE - textWidth) / 2 + ":" + (realY * SIZE - fontHeight) / 2);
            g.drawString(String.valueOf(level), realX * SIZE + (SIZE - textWidth) / 2, (realY + 1) * SIZE - (fontHeight) / 2);
        }
    }
}
