package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * User: alexander.lenkevich
 * Date: 1/16/12
 * Time: 2:15 PM
 */
public enum Direction {

    FORWARD, BACK;

    public Direction getNext(){
        return this == FORWARD ? BACK : FORWARD;
    }

    public boolean isForward(){
        return this == FORWARD;
    }

    public boolean isBack(){
        return this == BACK;
    }

}
