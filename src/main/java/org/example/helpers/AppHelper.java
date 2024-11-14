package org.example.helpers;

import java.util.List;

public interface AppHelper<T> {
    T create();
    T edit(List<T> entities);
    T delete(List<T> entities);
    boolean printList(List<T> entities);
}