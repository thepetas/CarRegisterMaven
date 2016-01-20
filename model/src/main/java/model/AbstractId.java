package model;

import java.io.Serializable;

public abstract class AbstractId<T extends AbstractId> implements Comparable<T>,
        Serializable {

    private final Integer id;

    public AbstractId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        T that = (T) obj;
        return this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public int compareTo(T o) {
        return this.id - o.getId();
    }

}
