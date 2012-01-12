package domain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: alexander.lenkevich
 * Date: 1/10/12
 * Time: 6:41 PM
 */
public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point minus(Point point) {
        checkNotNull(point);
        return new Point(this.x - point.x, this.y - point.y);
    }

    public Point plus(Point point) {
        checkNotNull(point);
        return new Point(this.x + point.x, this.y + point.y);
    }

    @Override
    public String toString() {
        return "(" + x + ":" + y + ")";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


}
