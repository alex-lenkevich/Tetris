package domain;

import com.google.common.collect.ImmutableSet;
import domainimpl.SimpleArea;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:07 PM
 */
public class AreaTests {

    @Test
    public void addingFigure() {
        Area area = new SimpleArea();

        area.add(new Figure(FigureType.P, new Point(0, 0)));

        assertEquals(area.getFigures(), ImmutableSet.of(new Figure(FigureType.P, new Point(0, 0))));
    }

    @Test
    public void removingExistFigure() {
        Area area = new SimpleArea();

        area.add(new Figure(FigureType.P, new Point(0, 0)));
        area.add(new Figure(FigureType.P, new Point(1, 1)));
        area.remove(new Figure(FigureType.P, new Point(0, 0)));

        assertEquals(area.getFigures(), ImmutableSet.of(new Figure(FigureType.P, new Point(1, 1))));
    }

    @Test
    public void removingNotExistFigure() {
        Area area = new SimpleArea();

        area.add(new Figure(FigureType.P, new Point(0, 0)));

        assertEquals(area.getFigures(), ImmutableSet.of(new Figure(FigureType.P, new Point(0, 0))));
    }

    @Test
    public void fieldContainsTest() {
        Area area = new SimpleArea();

        area.add(new Figure(FigureType.P, new Point(0, 0)));

        Assert.assertTrue(area.contains(new Point(0, 0)));
        Assert.assertTrue(area.contains(new Point(-1, 0)));
        Assert.assertTrue(area.contains(new Point(0, -1)));
        Assert.assertTrue(area.contains(new Point(10, 0)));
    }

    @Test
    public void fieldNotContainsTest() {
        Area area = new SimpleArea();

        area.add(new Figure(FigureType.P, new Point(0, 0)));

        Assert.assertFalse(area.contains(new Point(1, 0)));
    }

    @Test
    public void activeFigureStoreTest() {
        Area area = new SimpleArea();

        area.setActive(new Figure(FigureType.P, new Point(1, 2)));

        Assert.assertEquals(area.getActive(), new Figure(FigureType.P, new Point(1, 2)));
    }

    @Test
    public void activeFigureNullableFailTest() {
        Area area = new SimpleArea();
        try {
            area.setActive(null);
            fail("Must be exception");
        } catch (Exception e) {
        }
    }

}
