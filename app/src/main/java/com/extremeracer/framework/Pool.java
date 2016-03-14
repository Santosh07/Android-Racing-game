package com.extremeracer.framework;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private List<T> freeObjects;
    private PoolObjectFactory<T> factory;
    private int maxSize;

    public Pool(PoolObjectFactory factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        freeObjects = new ArrayList<T>(maxSize);
    }

    public T newObject() {
        T object;

        if (freeObjects.isEmpty()) {
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        return object;
    }

    public void free(T object) {
        if (freeObjects.size() < maxSize) {
            freeObjects.add(object);
        }
    }
}
