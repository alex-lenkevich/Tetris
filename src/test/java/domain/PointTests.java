package domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 7:38 PM
 */
public class PointTests {

    @Test
    public void testCreating() throws Exception {
        Point point = new Point(3, 5);

        assertEquals(point.x, 3);
        assertEquals(point.y, 5);
    }

    @Test
    public void testEquals() throws Exception {
        Point point = new Point(3, 5);
        Point point2 = new Point(3, 5);

        assertEquals(point, point2);
    }

    @Test
    public void testNotEquals() throws Exception {
        Point point = new Point(3, 5);
        Point point2 = new Point(5, 3);

        assertNotEquals(point, point2);
    }

    @Test
    public void testPlus() throws Exception {
        Point point1 = new Point(3, 5);
        Point point2 = new Point(2, 1);

        Point act = point1.plus(point2);
        Point exp = new Point(5, 6);

        assertEquals(act, exp);
    }

    @Test
    public void testMinus() throws Exception {
        Point point1 = new Point(3, 5);
        Point point2 = new Point(2, 1);

        Point act = point1.minus(point2);
        Point exp = new Point(1, 4);

        assertEquals(act, exp);
    }
}
