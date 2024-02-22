package Repo;

import java.util.ArrayList;

public interface Repository<E> {

    ArrayList<E> getAll();

    void add(E object);

    void update(E object);

    void remove(E object);

    boolean load();

    boolean save();
}
