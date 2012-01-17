package domain;

import domainimpl.SimpleArea;
import domainimpl.SimpleCleaner;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 1:39 PM
 */
public class CleanerTests {

    @Test
    public void testFilledRow() throws Exception {
        Cleaner cleaner = new SimpleCleaner();
        Area area = provideArea();
        area.add(new Figure(FigureType.P, new Point(4, 0)));
        area.add(new Figure(FigureType.P, new Point(3, 0)));
        for(int i = 0 ; i < 10 ; i ++){
            area.add(new Figure(FigureType.P, new Point(i, 1)));
            area.add(new Figure(FigureType.P, new Point(i, 2)));
        }

        area.add(new Figure(FigureType.P, new Point(3, 3)));
        area.add(new Figure(FigureType.P, new Point(5, 4)));
        area.add(new Figure(FigureType.P, new Point(5, 3)));
        area.add(new Figure(FigureType.P, new Point(6, 4)));

        int n = cleaner.clear(area);

        assertEquals(n, 2);

        assertTrue(area.contains(new Point(4, 0)));
        assertTrue(area.contains(new Point(3, 0)));

        assertTrue(area.contains(new Point(3, 1)));
        assertTrue(area.contains(new Point(5, 2)));
        assertTrue(area.contains(new Point(5, 1)));
        assertTrue(area.contains(new Point(6, 2)));

        assertFalse(area.contains(new Point(5, 0)));

    }

    @Test
    public void test2() throws Exception {
        Cleaner cleaner = new SimpleCleaner();
        Area area = provideArea();
        initArea(area,  new int[][]{
                {1,0,0,1,0,0,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,0,0,1,0,0,1,1,1}
        });
        cleaner.clear(area);
        testArea(area, new int[][]{
                {1,0,0,1,0,0,1,1,1,1},
                {1,1,0,0,1,0,0,1,1,1}
        });

        initArea(area, new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,0,0,1,0,0,1,1,1}
        });

        cleaner.clear(area);
        testArea(area, new int[][]{
                {1,0,0,1,0,0,1,1,1,1},
                {1,1,0,0,1,0,0,1,1,1},
                {1,1,0,0,1,0,0,1,1,1}
        });

    }

    public static void initArea(domain.Area area, int[][] points){
        for(int x = 0 ; x < points.length ; x++){
            for(int y = 0 ; y < points[x].length ; y++){
                if(points[x][y] != 0){
                    area.add(new Figure(domain.FigureType.P, new Point(y, x)));
                }
            }
        }
    }

    private void testArea(Area area, int[][] points){
        for(int x = 0 ; x <points.length ; x++){
            for(int y = 0 ; y < points[x].length ; y++){
                if(points[x][y] != 0){
                    assertTrue(area.contains(new Point(y, x)), x + ":" + y);
                } else {
                    assertFalse(area.contains(new Point(y, x)), x + ":" + y);
                }
            }
        }
    }

    private SimpleArea provideArea() {
        return new SimpleArea(){
            @Override
            public int getSize(Axis axis) {
                return axis == Axis.Z ? 1 : 10;
            }


        };
    }

}
