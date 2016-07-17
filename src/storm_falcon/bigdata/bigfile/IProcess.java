package storm_falcon.bigdata.bigfile;

import java.util.Vector;

/**
 * Created by Storm_Falcon on 2016/4/27.
 *
 */
public abstract class IProcess<E> {

    protected Vector<E> vector = null;

    public IProcess(Vector<E> vector) {
        this.vector = vector;
    }

    public abstract void doFun(String s);
}
