package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 5:30 PM
 */
public class Way {

    public final Axis axis;
    public final Direction direction;

    public Way(Axis axis, Direction direction) {
        this.axis = axis;
        this.direction = direction;
    }

    public Axis getAxis() {
        return axis;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Way way = (Way) o;

        return axis == way.axis && direction == way.direction;

    }

    @Override
    public int hashCode() {
        int result = axis != null ? axis.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Way{" +
                "axis=" + axis +
                ", direction=" + direction +
                '}';
    }
}
