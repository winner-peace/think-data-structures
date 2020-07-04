package com.winnerpeace.datastructures.chapter6.stack;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ArrayListStack<E> extends ListStack<E> {

    private static final int DEFAULT_CAPACITY = 10;

    public final int capacity;

    public ArrayListStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    protected List<E> getList() {
        return new ArrayList<>(capacity);
    }
}
