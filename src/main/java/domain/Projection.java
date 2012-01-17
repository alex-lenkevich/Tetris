package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static domain.Direction.BACK;
import static domain.Direction.FORWARD;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 2:19 PM
 */
public class Projection {

    public final Way viewWay;
    public final Way hWay;
    public final Way vWay;
//    Axis axis;
//    Orient orient;
//    Direction direction;

//    public Projection(Axis axis, Orient orient, Direction direction) {
//        this.axis = axis;
//        this.orient = orient;
//        this.direction = direction;
//    }

    public Projection() {
        this(
                new Way(Axis.Z, Direction.FORWARD),
                new Way(Axis.X, Direction.FORWARD),
                new Way(Axis.Y, Direction.FORWARD)
        );
    }

    public Projection(Way viewWay, Way hWay, Way vWay) {
        this.viewWay = viewWay;
        this.hWay = hWay;
        this.vWay = vWay;
    }

    private Direction getNewDirection(Direction direction, Way way) {
        return direction.isForward() ? way.getDirection() : way.getDirection().getNext();
    }

    private Projection rotateH(Direction direction){
        Axis newVAxis = viewWay.getAxis();
        Direction newVDirection = getNewDirection(direction, viewWay);
        Way newVWay = new Way(newVAxis, newVDirection);

        Axis newViewAxis = vWay.getAxis();
        Direction newViewDirection = getNewDirection(direction.getNext(), vWay);
        Way newViewWay = new Way(newViewAxis, newViewDirection);

        return new Projection(newViewWay, hWay, newVWay);
    }

    private Projection rotateV(Direction direction){
        Axis newHAxis = viewWay.getAxis();
        Direction newHDirection = getNewDirection(direction, viewWay);
        Way newHWay = new Way(newHAxis, newHDirection);

        Axis newViewAxis = hWay.getAxis();
        Direction newViewDirection = getNewDirection(direction.getNext(), hWay);
        Way newViewWay = new Way(newViewAxis, newViewDirection);

        return new Projection(newViewWay, newHWay, vWay);
    }

    private Projection rotateView(Direction direction){
        Axis newHAxis = vWay.getAxis();
        Direction newHDirection = getNewDirection(direction, vWay);
        Way newHWay = new Way(newHAxis, newHDirection);

        Axis newVAxis = hWay.getAxis();
        Direction newVDirection = getNewDirection(direction.getNext(), hWay);
        Way newVWay = new Way(newVAxis, newVDirection);

        return new Projection(viewWay, newHWay, newVWay);
    }

    public Projection up(){
        return rotateH(FORWARD);
    }

    public Projection down(){
        return rotateH(BACK);
    }

    public Projection right(){
        return rotateV(FORWARD);
    }

    public Projection left(){
        return rotateV(BACK);
    }

    public Projection rotateRight(){
        return rotateView(FORWARD);
    }

    public Projection rotateLeft(){
        return rotateView(BACK);
    }

    public Projection rotate(Axis axis, Direction direction){
        switch (axis) {
            case X:
                return rotateH(direction);
            case Y:
                return rotateV(direction);
            case Z:
                return rotateView(direction);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projection that = (Projection) o;

        if (hWay != null ? !hWay.equals(that.hWay) : that.hWay != null) return false;
        if (vWay != null ? !vWay.equals(that.vWay) : that.vWay != null) return false;
        if (viewWay != null ? !viewWay.equals(that.viewWay) : that.viewWay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = viewWay != null ? viewWay.hashCode() : 0;
        result = 31 * result + (hWay != null ? hWay.hashCode() : 0);
        result = 31 * result + (vWay != null ? vWay.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "viewWay=" + viewWay +
                ", hWay=" + hWay +
                ", vWay=" + vWay +
                '}';
    }
}
