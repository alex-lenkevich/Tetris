package domain;

import com.google.common.collect.Iterables;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 11:34 AM
 */
public enum Axis {

    X, Y, Z;

    public static List<Axis> invertAxisList(Axis... ignoreAxises) {
        final List<Axis> axises = new ArrayList<Axis>(Arrays.asList(values()));
        axises.removeAll(Arrays.asList(ignoreAxises));
        return axises;
    }

    public Axis getNext(Axis axis){
        if(this == axis) return this;
        return invertAxisList(this, axis).iterator().next();
    }
    public boolean orientChangeNeeded(Axis axis, boolean forward){
        return axis != this && forward ^ invertAxisList(axis).get(0) == this;
    }

    
}
