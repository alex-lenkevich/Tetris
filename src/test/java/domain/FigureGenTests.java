package domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/12/12
 * Time: 12:33 PM
 */
public class FigureGenTests {

    @Test
    public void testNewFigure() throws Exception {
        Area area = new SimpleArea();
        FigureGen figureGen = new SimpleFigureGen();
        figureGen.genNewFigure(area);
        assertNotNull(area.getActive());
    }
}
