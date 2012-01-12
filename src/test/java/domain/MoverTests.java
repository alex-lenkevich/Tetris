package domain;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 8:04 PM
 */
public class MoverTests {

    @Test
    public void testFall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertTrue(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(1, 0));
    }

    @Test
    public void testMoveRight() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertTrue(mover.moveRight(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

    @Test
    public void testMoveLeft() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertTrue(mover.moveLeft(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testDifFall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();


        //5  LL
        //4   LI
        //3   LI
        //2 OO I
        //1 OO I
        //0
        // 012345


        area.add(new Figure(FigureType.O, new Point(1, 1), FigureOrient.UP));
        area.add(new Figure(FigureType.I, new Point(4, 1), FigureOrient.LEFT));

        area.setActive(new Figure(FigureType.J, new Point(2, 3), FigureOrient.RIGHT));

        assertTrue(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 2));


        assertTrue(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
        assertFalse(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

    @Test
    public void testDifMoveRight() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertTrue(mover.moveRight(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

    @Test
    public void testDifMoveLeft() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertTrue(mover.moveLeft(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testImpossibleMoveLeftByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(0, 1), FigureOrient.UP));
        assertFalse(mover.moveLeft(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testImpossibleMoveLeftByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(0, 0), FigureOrient.UP));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1), FigureOrient.UP));
        assertFalse(mover.moveLeft(area));
        assertEquals(area.getActive().getPosition(), new Point(1, 1));
    }

    @Test
    public void testImpossibleMoveRightByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(9, 1), FigureOrient.UP));
        assertFalse(mover.moveRight(area));
        assertEquals(area.getActive().getPosition(), new Point(9, 1));
    }

    @Test
    public void testImpossibleMoveRightByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(9, 0), FigureOrient.UP));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(8, 1), FigureOrient.UP));
        assertFalse(mover.moveRight(area));
        assertEquals(area.getActive().getPosition(), new Point(8, 1));
    }

    @Test
    public void testImpossibleFallByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(3, 0), FigureOrient.UP));
        assertFalse(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(3, 0));
    }

    @Test
    public void testImpossibleFallByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(0, 0), FigureOrient.LEFT));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(2, 1), FigureOrient.UP));
        assertFalse(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

}
