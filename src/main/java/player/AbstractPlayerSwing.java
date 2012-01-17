package player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.util.Function;
import domain.*;
import game.Game2D;
import sun.awt.VerticalBagLayout;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_PAGE_DOWN;
import static java.awt.event.KeyEvent.VK_PAGE_UP;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 11:41 AM
 */
public abstract class AbstractPlayerSwing extends JFrame implements Player {

    public static final int SIZE = 30;
    public static final int MINI_SIZE = 5;
    public static final int SCORE_HEIGHT = 80;

    public static final NumberFormat scoreFormat = NumberFormat.getIntegerInstance();

    Projection projection = new Projection();

    protected Game2D game;
    protected List<MainPanel> mainPanels = ImmutableList.of(
            new MainPanel(new Projection()),
            new MainPanel(new Projection().up()),
            new MainPanel(new Projection().right())
    );
    protected JPanel scorePanel = new JPanel() {
        @Override
        public void paint(Graphics g) {
            g.drawString("Score: " + scoreFormat.format(game.getScore().getScore()), 10, 20);
            g.drawString("Lines: " + scoreFormat.format(game.getScore().getLines()), 10, 40);
            g.drawString("Speed: " + scoreFormat.format(game.getScheduler().getSpeed()), 10, 60);
            for(int i = 0 ; i < game.getNextFigure().size() ; i ++){
                Figure next = game.getNextFigure().get(i);
                for (domain.Point point : next.getType().getPoints()) {
                    g.setColor(next.getColor());
                    int realY = next.getType().getRightBottomCorner().y - point.y;
                    g.fillRect(100 + i * MINI_SIZE * 5 + (point.x * MINI_SIZE), 5 + (realY * MINI_SIZE), MINI_SIZE, MINI_SIZE);
                }
            }
        }
    };

    protected void updateProjection(Function<Projection, Projection> function){

        for(MainPanel mainPanel : mainPanels){
            mainPanel.setProjection(function.apply(mainPanel.getProjection()));
        }

        int maxV = game.getArea().getSize(projection.vWay.getAxis());
        int maxH = game.getArea().getSize(projection.hWay.getAxis());

        for(MainPanel mainPanel : mainPanels){
            mainPanel.setPreferredSize(new Dimension(maxH * SIZE, maxV * SIZE));
            mainPanel.setMaximumSize(new Dimension(maxH * SIZE, maxV * SIZE));
            mainPanel.setMinimumSize(new Dimension(maxH * SIZE, maxV * SIZE));
        }

        scorePanel.setPreferredSize(new Dimension(maxH * SIZE, SCORE_HEIGHT));
        scorePanel.setMaximumSize(new Dimension(maxH * SIZE, SCORE_HEIGHT));
        scorePanel.setMinimumSize(new Dimension(maxH * SIZE, SCORE_HEIGHT));

        pack();

        System.out.println("updateProjection");

        update();
    }



    @Override
    public void start() {


        scorePanel.setBackground(Color.BLACK);

        setLayout(new VerticalBagLayout());

        getContentPane().add(scorePanel);
        JPanel content = new JPanel(new FlowLayout());
        getContentPane().add(content);
        for(MainPanel mainPanel : mainPanels){
            mainPanel.setBackground(Color.BLACK);
            content.add(mainPanel);
        }

        updateProjection(new Function<Projection, Projection>() {
            @Override
            public Projection apply(Projection projection) {
                return projection;
            }
        });


//        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_ENTER:
                        game.drop();
                        break;
                    case VK_SPACE:
                        game.drop();
                        break;
                    case VK_PAUSE:
                        game.switchPause();
                        break;
                    case VK_BACK_SPACE:
                        game.removeBottomArray();
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
    }

    @Inject
    public void setGame(Game2D game) {
        this.game = game;
    }

    protected abstract void drawFigures(Graphics g, int x, int y);

    class MainPanel extends JPanel {

        Projection projection;

        MainPanel(Projection projection) {
            this.projection = projection;
        }

        public Projection getProjection() {
            return projection;
        }

        public void setProjection(Projection projection) {
            this.projection = projection;
        }

        @Override
        public void paint(Graphics g) {
            Area area = game.getArea();

//            Set<Integer> grey = new HashSet<Integer>();
//            Set<Integer> lightGrey = new HashSet<Integer>();
//
//            int minYActive = area.getHeight();
//            for(domain.Point p : area.getActive().getPoints()){
//                if(p.y < minYActive) minYActive = p.y;
//            }
//
//            for(domain.Point p : area.getActive().getPoints()){
//                if(p.y == minYActive) {
//                    lightGrey.add(p.x);
//                } else {
//                    grey.add(p.x);
//                }
//            }


            int maxV = area.getSize(projection.vWay.getAxis());
            boolean forwardV = projection.vWay.getDirection().isForward();

            int maxH = area.getSize(projection.hWay.getAxis());
            boolean forwardH = projection.hWay.getDirection().isForward();

            for (int y = 0; y < maxV ; y++) {
                for (int x = 0; x < maxH ; x++) {
                    drawFigures(g, x, y);
                }
            }
            g.setColor(Color.GRAY);
            for (domain.Point point : area.getActive().getPoints()) {
                g.setColor(area.getActive().getColor());
                int realX = forwardH
                        ? point.getValue(projection.hWay.getAxis())
                        : maxH - point.getValue(projection.hWay.getAxis()) - 1;
                int realY = forwardV
                        ? maxV - point.getValue(projection.vWay.getAxis()) - 1
                        : point.getValue(projection.vWay.getAxis());
                g.fillRect(realX * SIZE, realY * SIZE, SIZE, SIZE);
            }

            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 24));
            g.drawString(projection.hWay.getAxis() + ":" + projection.vWay.getAxis(), 20, 20);

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
}

}
