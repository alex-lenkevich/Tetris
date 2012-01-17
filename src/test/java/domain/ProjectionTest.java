package domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 3:48 PM
 */
public class ProjectionTest {
    @Test
    public void testUp() throws Exception {
        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
                );

        projection = projection.up();

        assertEquals(projection, new Projection(
                new Way(Axis.Y, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.FORWARD)
        ));
        projection = projection.up();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.BACK)
        ));
        projection = projection.up();
        assertEquals(projection, new Projection(
                new Way(Axis.Y, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.BACK)
        ));
        projection = projection.up();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));

    }

    @Test
    public void testDown() throws Exception {
        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );

        projection = projection.down();

        assertEquals(projection, new Projection(
                new Way(Axis.Y, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.BACK)
        ));
        projection = projection.down();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.BACK)
        ));
        projection = projection.down();
        assertEquals(projection, new Projection(
                new Way(Axis.Y, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.FORWARD)
        ));
        projection = projection.down();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));
    }

    @Test
    public void testLeft() throws Exception {
        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );

        projection = projection.left();

        assertEquals(projection, new Projection(
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.left();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.left();
        assertEquals(projection, new Projection(
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.left();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));

    }

    @Test
    public void testRight() throws Exception {

        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );

        projection = projection.right();

        assertEquals(projection, new Projection(
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.right();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.right();
        assertEquals(projection, new Projection(
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Z, Direction.BACK),
                new Way(Axis.Y, Direction.FORWARD)
        ));
        projection = projection.right();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));

    }

    @Test
    public void testRotateLeft() throws Exception {
        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );

        projection = projection.rotateLeft();

        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD)
        ));
        projection = projection.rotateLeft();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Y, Direction.BACK)
        ));
        projection = projection.rotateLeft();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD),
                new Way(Axis.X, Direction.BACK)
        ));
        projection = projection.rotateLeft();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));

    }

    @Test
    public void testRotateRight() throws Exception {
        Projection projection = new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );

        projection = projection.rotateRight();

        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD),
                new Way(Axis.X, Direction.BACK)
        ));
        projection = projection.rotateRight();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.BACK),
                new Way(Axis.Y, Direction.BACK)
        ));
        projection = projection.rotateRight();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.Y, Direction.BACK),
                new Way(Axis.X, Direction.FORWARD)
        ));
        projection = projection.rotateRight();
        assertEquals(projection, new Projection(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        ));

    }
}
