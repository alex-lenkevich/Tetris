package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 10:51 AM
 */
public enum Orient {

    UP, RIGHT, DOWN, LEFT;

    public Orient getNext(){
        LinkedList<Orient> list = new LinkedList<Orient>(Arrays.asList(values()));
        return list.get((list.lastIndexOf(this) + 1) % list.size());
    }

}
