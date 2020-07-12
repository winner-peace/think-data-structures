package com.winnerpeace.datastructures.jaeyeonling.stack;

import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public final class LinkedListStack<E> extends ListStack<E> {

    @Override
    protected List<E> getList() {
        return new LinkedList<>();
    }
}
