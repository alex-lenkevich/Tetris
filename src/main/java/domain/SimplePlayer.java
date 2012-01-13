package domain;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.inject.Inject;
import sun.awt.VerticalBagLayout;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 1:02 PM
 */
public class SimplePlayer extends JFrame implements Player {

    public static final int SIZE = 15;
    public static final int MINI_SIZE = 5;
    public static final int SCORE_HEIGHT = 80;

    public static final NumberFormat scoreFormat = NumberFormat.getIntegerInstance();

    private Game game;
    private JPanel mainPanel = new JPanel() {

        @Override
        public void paint(Graphics g) {
            Area area = game.getArea();

            Set<Integer> grey = new HashSet<Integer>();
            Set<Integer> lightGrey = new HashSet<Integer>();
            
            int minYActive = area.getHeight();
            for(Point p : area.getActive().getPoints()){
                if(p.y < minYActive) minYActive = p.y;
            }

            for(Point p : area.getActive().getPoints()){
                if(p.y == minYActive) {
                    lightGrey.add(p.x);
                } else {
                    grey.add(p.x);
                }
            }

            for (int y = 0; y < area.getHeight(); y++) {
                for (int x = 0; x < area.getWidth(); x++) {
                    final int xx = x;
                    Color back = Color.BLACK;

                    if (lightGrey.contains(x)) {
                        back = new Color(0x333333);
                    } else if(grey.contains(x)) {
                        back = new Color(0x222222);
                    }
                    int realY = area.getHeight() - y - 1;
                    Point point = new domain.Point(x, y);
                    g.setColor(area.contains(point) ? area.getColorAt(point) : back);
                    g.fillRect(x * SIZE, realY * SIZE, SIZE, SIZE);
                }
            }
            g.setColor(Color.GRAY);
            for (Point point : area.getActive().getPoints()) {
                g.setColor(area.getActive().getColor());
                int realY = area.getHeight() - point.y - 1;
                g.fillRect(point.x * SIZE, realY * SIZE, SIZE, SIZE);
            }
            if (game.isPause()) {
                paintText(g, "PAUSE");
            }

            if (game.isGameOver()) {
                paintText(g, "GAME OVER");
            }
        }

        private void paintText(Graphics g, String text) {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 18));
            g.setColor(Color.GREEN);


            FontMetrics metrics = g.getFontMetrics();
            int fontHeight = metrics.getHeight();
            int textWidth = metrics.stringWidth(text);

            g.drawString(text, (int) (getSize().getWidth() - textWidth) / 2, (int) (getSize().getHeight() - fontHeight) / 2);
        }
    };
    private JPanel scorePanel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            g.drawString("Score: " + scoreFormat.format(game.getScore().getScore()), 10, 20);
            g.drawString("Lines: " + scoreFormat.format(game.getScore().getLines()), 10, 40);
            g.drawString("Speed: " + scoreFormat.format(game.getScheduler().getSpeed()), 10, 60);
            for(int i = 0 ; i < game.getNextFigure().size() ; i ++){
                Figure next = game.getNextFigure().get(i);
                for (Point point : next.getType().getPoints()) {
                    g.setColor(next.getColor());
                    int realY = next.getType().getRightBottomCorner().y - point.y;
                    g.fillRect(100 + i * MINI_SIZE * 5 + (point.x * MINI_SIZE), 5 + (realY * MINI_SIZE), MINI_SIZE, MINI_SIZE);
                }
            }
        }
    };

    @Override
    public void start() {
        mainPanel.setPreferredSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));
        mainPanel.setMaximumSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));
        mainPanel.setMinimumSize(new Dimension(game.getArea().getWidth() * SIZE, game.getArea().getHeight() * SIZE));

        scorePanel.setPreferredSize(new Dimension(game.getArea().getWidth() * SIZE, SCORE_HEIGHT));
        scorePanel.setMaximumSize(new Dimension(game.getArea().getWidth() * SIZE, SCORE_HEIGHT));
        scorePanel.setMinimumSize(new Dimension(game.getArea().getWidth() * SIZE, SCORE_HEIGHT));

        scorePanel.setBackground(Color.BLACK);
        mainPanel.setBackground(Color.BLACK);

        setLayout(new VerticalBagLayout());

        getContentPane().add(scorePanel);
        getContentPane().add(mainPanel);

        pack();

//        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_LEFT:
                        game.moveLeft();
                        break;
                    case VK_RIGHT:
                        game.moveRight();
                        break;
                    case VK_DOWN:
                        game.fall();
                        break;
                    case VK_UP:
                        game.rotate();
                        break;
                    case VK_SPACE:
                        game.drop();
                        break;
                    case VK_PAUSE:
                        game.switchPause();
                        break;
                    case VK_BACK_SPACE:
                        game.removeBottomLine();
                        break;
                    case VK_PAGE_UP:
                        game.upSpeed();
                        break;
                    case VK_PAGE_DOWN:
                        game.downSpeed();
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

    @Override
    public void gameOver() {
        update();
        System.out.println("GAME OVER");
    }

    @Inject
    public void setGame(Game game) {
        this.game = game;
    }
}
