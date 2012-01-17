package domain;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:41 PM
 */
public class Point {

    public final int x;
    public final int y;
    public final int z;

    public Point(Map<Axis, Integer> values) {
        int x = 0, y = 0, z = 0;
        for(Map.Entry<Axis, Integer> entry : values.entrySet()){
            switch (entry.getKey()) {
                case X:
                    x = entry.getValue();
                    break;
                case Y:
                    y = entry.getValue();
                    break;
                case Z:
                    z = entry.getValue();
                    break;
            }
        }

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(int x, int y) {
        this(x, y, 0);
    }

    public Point minus(Point point) {
        checkNotNull(point);
        return create(this.x - point.x, this.y - point.y, this.z - point.z);
    }

    public Point plus(Point point) {
        checkNotNull(point);
        return create(this.x + point.x, this.y + point.y, this.z + point.z);
    }

    protected Point create(int x, int y, int z) {
        return new Point(x, y, z);
    }

    /*
    checkNotNull(point);
        return new Point(this.x - point.x, this.y - point.y, this.z - point.z);

        checkNotNull(point);
        return new Point(this.x + point.x, this.y + point.y, this.z - point.z);
     */

    public int getValue(Axis axis) {
        switch (axis) {
            case X:
                return x;
            case Y:
                return y;
            case Z:
                return z;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ":" + y + ":" + z + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y && z == point.z;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
