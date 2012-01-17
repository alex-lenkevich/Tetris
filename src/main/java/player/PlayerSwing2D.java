package player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.util.Function;
import domain.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_PAGE_DOWN;
import static java.awt.event.KeyEvent.VK_PAGE_UP;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 11:41 AM
 */
public class PlayerSwing2D extends AbstractPlayerSwing {

    public PlayerSwing2D() {
        mainPanels = ImmutableList.of(
                new MainPanel(new Projection())
        );

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_UP:
                        game.rotate(projection.viewWay.getAxis(), projection.viewWay.getDirection());
                        break;
                    case VK_DOWN:
                        game.fall();
                        break;
                    case VK_RIGHT:
                        game.move(projection.hWay.getAxis(), projection.hWay.getDirection());
                        break;
                    case VK_LEFT:
                        game.move(projection.hWay.getAxis(), projection.hWay.getDirection().getNext());
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
        if(game.getArea().contains(point, projection.viewWay.getAxis())){
            g.setColor(game.getArea().getColorAt(line, projection.viewWay.getDirection()));
            //System.out.println(game.getArea().getColorAt(line, projection.viewWay.getDirection()));
        } else {
            g.setColor(back);
        }
        {
        int realY = forwardV ? maxV - y - 1 : y;
        int realX = forwardH ? x : maxH - x - 1;

        g.fillRect(realX * SIZE, realY * SIZE, SIZE, SIZE);
        }


        Collection<domain.Point> forecastPoints = game.getMover().getDropFigureForecast(game.getArea()).getPoints();
        for (domain.Point forecastPoint : forecastPoints) {
            g.setColor(new Color(0x00DD00));
            int realX = forwardH
                    ? forecastPoint.getValue(projection.hWay.getAxis())
                    : maxH - forecastPoint.getValue(projection.hWay.getAxis()) - 1;
            int realY = forwardV
                    ? maxV - forecastPoint.getValue(projection.vWay.getAxis()) - 1
                    : forecastPoint.getValue(projection.vWay.getAxis());

            if(!forecastPoints.contains(forecastPoint.minus(new domain.Point(1, 0)))){ // left
                g.drawLine((realX) * SIZE, (realY) * SIZE, (realX) * SIZE, (realY + 1) * SIZE);
            }
            if(!forecastPoints.contains(forecastPoint.minus(new domain.Point(0, 1)))){ // bottom
                g.drawLine(realX * SIZE, (realY + 1) * SIZE, (realX + 1) * SIZE, (realY + 1) * SIZE);
            }
            if(!forecastPoints.contains(forecastPoint.plus(new domain.Point(0, 1)))){ // top
                g.drawLine(realX * SIZE, (realY) * SIZE, (realX + 1) * SIZE, (realY) * SIZE);
            }
            if(!forecastPoints.contains(forecastPoint.plus(new domain.Point(1, 0)))){ // right
                g.drawLine((realX + 1) * SIZE, (realY) * SIZE, (realX + 1) * SIZE, (realY + 1) * SIZE);
            }

        }

    }
}
