package domain;

import domainimpl.SimpleArea;
import domainimpl.SimpleMover;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 8:04 PM
 */
public abstract class MoverTests {

    @Test
    public void testFall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertTrue(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(1, 0));
    }

    @Test
    public void testMoveRight() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertTrue(mover.moveWest(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

    @Test
    public void testMoveLeft() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertTrue(mover.moveEast(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testDifFall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();

        area.add(new Figure(FigureType.O, new Point(1, 1)));
        area.add(new Figure(FigureType.I, new Point(4, 1), new Projection().rotateRight()));

        area.setActive(new Figure(FigureType.J, new Point(2, 3), new Projection().rotateLeft()));

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
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertTrue(mover.moveWest(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

    @Test
    public void testDifMoveLeft() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertTrue(mover.moveEast(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testImpossibleMoveLeftByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(0, 1)));
        assertFalse(mover.moveEast(area));
        assertEquals(area.getActive().getPosition(), new Point(0, 1));
    }

    @Test
    public void testImpossibleMoveLeftByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(0, 0)));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(1, 1)));
        assertFalse(mover.moveEast(area));
        assertEquals(area.getActive().getPosition(), new Point(1, 1));
    }

    @Test
    public void testImpossibleMoveRightByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(9, 1)));
        assertFalse(mover.moveWest(area));
        assertEquals(area.getActive().getPosition(), new Point(9, 1));
    }

    @Test
    public void testImpossibleMoveRightByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(9, 0)));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(8, 1)));
        assertFalse(mover.moveWest(area));
        assertEquals(area.getActive().getPosition(), new Point(8, 1));
    }

    @Test
    public void testImpossibleFallByWall() throws Exception {
        Area area = new SimpleArea();
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(3, 0)));
        assertFalse(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(3, 0));
    }

    @Test
    public void testImpossibleFallByFigure() throws Exception {
        Area area = new SimpleArea();
        area.add(new Figure(FigureType.I, new Point(0, 0), new Projection().rotateLeft()));
        Mover mover = new SimpleMover();
        area.setActive(new Figure(FigureType.P, new Point(2, 1)));
        assertFalse(mover.fall(area));
        assertEquals(area.getActive().getPosition(), new Point(2, 1));
    }

}
