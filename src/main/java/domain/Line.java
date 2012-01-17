package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 7:05 PM
 */
public class Line {
    Axis axis1;
    Integer value1;
    Axis axis2;
    Integer value2;

    public Line(Axis axis1, Integer value1, Axis axis2, Integer value2) {
        this.axis1 = axis1;
        this.value1 = value1;
        this.axis2 = axis2;
        this.value2 = value2;
    }

    public Axis getAxis1() {
        return axis1;
    }

    public Integer getValue1() {
        return value1;
    }

    public Axis getAxis2() {
        return axis2;
    }

    public Integer getValue2() {
        return value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (axis1 != line.axis1) return false;
        if (axis2 != line.axis2) return false;
        if (value1 != null ? !value1.equals(line.value1) : line.value1 != null) return false;
        if (value2 != null ? !value2.equals(line.value2) : line.value2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = axis1 != null ? axis1.hashCode() : 0;
        result = 31 * result + (value1 != null ? value1.hashCode() : 0);
        result = 31 * result + (axis2 != null ? axis2.hashCode() : 0);
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "axis1=" + axis1 +
                ", value1=" + value1 +
                ", axis2=" + axis2 +
                ", value2=" + value2 +
                '}';
    }
}
