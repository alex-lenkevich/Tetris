package domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import sun.awt.VerticalBagLayout;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import static java.awt.event.KeyEvent.*;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:02 PM
 */
public class SimplePlayer extends JFrame implements Player {

    public static final int SIZE = 15;
    public static final int MINI_SIZE = 10;
    public static final NumberFormat scoreFormat = NumberFormat.getIntegerInstance();
    static {
        scoreFormat.setMaximumIntegerDigits(6);
    }

    private Game game;
    private JPanel mainPanel = new JPanel(){

        @Override
        public void paint(Graphics g) {
            Area area = game.getArea();
//            g.setColor(Color.YELLOW);
//            g.fillRect(10, 10, 50, 50);
            for(int y = 0 ; y < area.getHeight() ; y ++){
                for(int x = 0 ; x < area.getWidth(); x ++){
                    final int xx = x;
                    Color back = Color.BLACK;
                    if(Iterables.any(area.getActive().getPoints(), new Predicate<Point>() {
                        @Override
                        public boolean apply(@Nullable Point point) {
                            return point.x == xx;
                        }
                    })){
                       back = new Color(0x222222);
                    }
                    int realY = area.getHeight() - y - 1;
                    Point point = new domain.Point(x, y);
                    g.setColor(area.contains(point) ? area.getColorAt(point) : back);
                    g.fillRect(x * SIZE, realY * SIZE, SIZE, SIZE);
                }
            }
            g.setColor(Color.GRAY);
            for(Point point : area.getActive().getPoints()){
                g.setColor(area.getActive().getColor());
                int realY = area.getHeight() - point.y - 1;
                g.fillRect(point.x * SIZE, realY * SIZE, SIZE, SIZE);
            }
        }

    };
    private JPanel scorePanel = new JPanel(){
        @Override
        public void paint(Graphics g) {
            g.drawString("Score: " + scoreFormat.format(game.getScore().getScore()), 10, 20);
            g.drawString("Lines: " + scoreFormat.format(game.getScore().getLines()), 10, 40);

            for(Point point : game.getNextFigure().getType().getPoints()){
                System.out.println(game.getNextFigure());
                g.setColor(game.getNextFigure().getColor());
                int realY = game.getNextFigure().getType().getRightBottomCorner().y - point.y;
                g.fillRect(100 + (point.x * MINI_SIZE), 5 + (realY * MINI_SIZE), MINI_SIZE, MINI_SIZE);
            }
        }
    };

    @Override
    public void start() {
        mainPanel.setPreferredSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));
        mainPanel.setMaximumSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));
        mainPanel.setMinimumSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));

        scorePanel.setPreferredSize(new Dimension(game.getArea().getWidth() * SIZE, 50));
        scorePanel.setMaximumSize(new Dimension(game.getArea().getWidth() * SIZE, 50));
        scorePanel.setMinimumSize(new Dimension(game.getArea().getWidth() * SIZE, 50));

        scorePanel.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);

        setLayout(new VerticalBagLayout());

        getContentPane().add(scorePanel);
        getContentPane().add(mainPanel);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case VK_LEFT : game.moveLeft();
                        break;
                    case VK_RIGHT : game.moveRight();
                        break;
                    case VK_DOWN : game.fall();
                        break;
                    case VK_UP : game.rotate();
                        break;
                    case VK_SPACE : game.drop();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
    }

    @Override
    public void update() {
        repaint();
    }

    @Inject
    public void setGame(Game game) {
        this.game = game;
    }
}
