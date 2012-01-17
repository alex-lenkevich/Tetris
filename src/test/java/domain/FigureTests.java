package domain;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 7:57 PM
 */
public class FigureTests {

    @Test
    public void testPContains() throws Exception {
        Figure figure = new Figure(FigureType.P, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertFalse(figure.contains(new Point(0, 0)));
        assertFalse(figure.contains(new Point(0, 2)));
        assertFalse(figure.contains(new Point(1, 0)));
        assertFalse(figure.contains(new Point(0, -1)));
    }

    @Test
    public void testIContains() throws Exception {
        Figure figure = new Figure(FigureType.I, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(1, 3)));
        assertTrue(figure.contains(new Point(1, 4)));
        assertTrue(figure.contains(new Point(1, 5)));
        assertFalse(figure.contains(new Point(0, 4)));
        assertFalse(figure.contains(new Point(0, -1)));
    }

    @Test
    public void testIRotateContains() throws Exception {
        Figure figure = new Figure(FigureType.I, new Point(1, 2), new Projection().rotateLeft());
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(3, 2)));
        assertTrue(figure.contains(new Point(4, 2)));
        assertFalse(figure.contains(new Point(0, 2)));
    }

    @Test
    public void testJContains() throws Exception {
        Figure figure = new Figure(FigureType.J, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(3, 2)));
        assertTrue(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(1, 3)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testLContains() throws Exception {
        Figure figure = new Figure(FigureType.L, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(3, 2)));
        assertTrue(figure.contains(new Point(1, 3)));
        assertFalse(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testLLeftContains() throws Exception {
        Figure figure = new Figure(FigureType.L, new Point(1, 2), new Projection().rotateRight());
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(1, 3)));
        assertTrue(figure.contains(new Point(1, 4)));
        assertTrue(figure.contains(new Point(2, 4)));
        assertFalse(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testOContains() throws Exception {
        Figure figure = new Figure(FigureType.O, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(2, 0)));
        assertFalse(figure.contains(new Point(0, 2)));
        assertFalse(figure.contains(new Point(0, -1)));
    }

    @Test
    public void testSContains() throws Exception {
        Figure figure = new Figure(FigureType.S, new Point(1, 2));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(3, 2)));
        assertTrue(figure.contains(new Point(1, 3)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(1, 2)));
        assertFalse(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testTContains() throws Exception {
        Figure figure = new Figure(FigureType.T, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(3, 2)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertFalse(figure.contains(new Point(1, 3)));
        assertFalse(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testTRotateContains() throws Exception {
        Figure figure = new Figure(FigureType.T, new Point(1, 2), new Projection().rotateRight().rotateRight());
        assertTrue(figure.contains(new Point(1, 3)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertTrue(figure.contains(new Point(3, 3)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertFalse(figure.contains(new Point(1, 2)));
        assertFalse(figure.contains(new Point(3, 2)));
        assertFalse(figure.contains(new Point(2, -1)));
    }


    @Test
    public void testZContains() throws Exception {
        Figure figure = new Figure(FigureType.Z, new Point(1, 2));
        assertTrue(figure.contains(new Point(1, 2)));
        assertTrue(figure.contains(new Point(2, 2)));
        assertTrue(figure.contains(new Point(2, 3)));
        assertTrue(figure.contains(new Point(3, 3)));
        assertFalse(figure.contains(new Point(1, 3)));
        assertFalse(figure.contains(new Point(3, 2)));
        assertFalse(figure.contains(new Point(2, -1)));
    }

    @Test
    public void testRotateLeft() throws Exception {
        Figure figure = new Figure(FigureType.L, new Point(0, 10), new Projection().rotateRight());
        assertEquals(figure.getPoints(), ImmutableSet.of(
                new Point(0, 12),
                new Point(0, 11),
                new Point(0, 10),
                new Point(1, 12)
        ));
    }

    @Test
     public void testRotateDown() throws Exception {
        Figure figure = new Figure(FigureType.L, new Point(0, 10), new Projection().rotateLeft().rotateLeft());

        assertEquals(figure.getPoints(), ImmutableSet.of(
                new Point(2, 11),
                new Point(1, 11),
                new Point(0, 11),
                new Point(2, 10)
        ));
    }

    @Test
    public void testRotateRight() throws Exception {
        Figure figure = new Figure(FigureType.L, new Point(0, 10), new Projection().rotateLeft());

        assertEquals(figure.getPoints(), ImmutableSet.of(
                new Point(1, 10),
                new Point(1, 11),
                new Point(1, 12),
                new Point(0, 10)
        ));
    }

    @Test
    public void testFall() throws Exception {
        assertEquals(new Figure(FigureType.P, new Point(1, 1)).getPoints(), ImmutableSet.of(new Point(1, 1)));
    }
}
