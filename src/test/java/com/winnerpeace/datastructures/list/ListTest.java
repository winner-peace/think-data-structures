package com.winnerpeace.datastructures.list;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public abstract class ListTest {

    private static final int FIRST_INDEX = 0;
    private static final int RANDOM_BOUND = 100;

    private List<Long> list;

    public abstract List<Long> getList();

    @BeforeEach
    void setUp() {
        list = getList();
    }

    @DisplayName("add: ")
    @Nested
    class Add {

        @DisplayName("값을 추가한다.")
        @Test
        void add() {
            // when
            final boolean isAdded = list.add(random());

            // then
            assertThat(isAdded).isTrue();
        }

        @DisplayName("특정 위치에 값을 추가한다.")
        @Test
        void addIndex() {
            // when
            final boolean isAdded = list.add(list.size(), random());

            // then
            assertThat(isAdded).isTrue();
        }

        @DisplayName("범위를 넘어가는 위치에 값을 추가 시 예외처리 한다.")
        @Test
        void addIndexOut() {
            // when
            final ThrowableAssert.ThrowingCallable action = () -> list.add(randomInt(RANDOM_BOUND), random());

            // then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(action);
        }

        @DisplayName("여러 값을 추가한다.")
        @Test
        void addAll() {
            // given
            final java.util.List<Long> dump = insertDump();
            final List<Long> values = new ArrayList<>();
            dump.forEach(values::add);

            // when
            final boolean isAdded = list.addAll(values);

            // then
            assertThat(isAdded).isTrue();
        }
    }

    @DisplayName("get: ")
    @Nested
    class Get {

        @DisplayName("첫 위치에 값을 조회한다.")
        @Test
        void getFirstIndex() {
            // given
            final long value = random();
            list.add(value);

            // when
            final long getValue = list.get(FIRST_INDEX);

            // then
            assertThat(getValue).isEqualTo(value);
        }

        @DisplayName("특정 위치에 값을 조회한다.")
        @Test
        void get() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int foundIndex = randomIndex();
            final long expected = dump.get(foundIndex);

            // when
            final long getValue = list.get(foundIndex);

            // then
            assertThat(getValue).isEqualTo(expected);
        }

        @DisplayName("범위를 넘어가는 위치에 값을 조회 시 예외처리 한다.")
        @Test
        void getIndexOut() {
            // given
            final long value = random();
            list.add(value);

            // when
            final ThrowableAssert.ThrowingCallable action = () -> list.get(list.size() + 1);

            // then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(action);
        }
    }

    @DisplayName("set: ")
    @Nested
    class Set {

        @DisplayName("첫 인덱스에 값을 삽입한다.")
        @Test
        void setFirstIndex() {
            // given
            final long addValue = random();

            // when / then
            list.set(FIRST_INDEX, addValue);
        }

        @DisplayName("인덱스에 값을 삽입하면 기존의 값을 반환한다.")
        @Test
        void setWillReturnOriginalValue() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int foundIndex = randomIndex();
            final long expected = dump.get(foundIndex);

            // when
            final long originalValue = list.set(foundIndex, random());

            // then
            assertThat(originalValue).isEqualTo(expected);
        }

        @DisplayName("마지막 인덱스에 값을 삽입한다.")
        @Test
        void setLastIndex() {
            // given
            final java.util.List<Long> dump = insertDump();
            final long addValue = random();
            final int lastIndex = dump.size();

            // when / then
            list.set(lastIndex, addValue);
        }

        @DisplayName("범위를 넘어가는 위치에 값을 삽입하면 예외처리 한다.")
        @Test
        void setIndexOut() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int lastIndex = dump.size();

            // when
            final ThrowableAssert.ThrowingCallable action = () -> list.set(lastIndex + 1, random());

            // then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(action);
        }
    }

    @DisplayName("indexOf: ")
    @Nested
    class IndexOf {

        @DisplayName("값의 인덱스 조회 시 값이 없다면 " + List.ELEMENT_NOT_FOUND + "을 반환한다.")
        @Test
        void indexOfNotFound() {
            // when
            final int foundIndex = list.indexOf(random());

            // then
            assertThat(foundIndex).isEqualTo(List.ELEMENT_NOT_FOUND);
        }

        @DisplayName("첫 인덱스의 값을 조회한다.")
        @Test
        void indexOfFirst() {
            // given
            final long value = random();
            list.add(value);

            // when
            final int foundIndex = list.indexOf(value);

            // then
            assertThat(foundIndex).isEqualTo(FIRST_INDEX);
        }

        @DisplayName("값의 인덱스를 조회한다.")
        @Test
        void indexOf() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int index = randomIndex();
            final long value = dump.get(index);

            // when
            final int foundIndex = list.indexOf(value);

            // then
            assertThat(index).isEqualTo(foundIndex);
        }
    }

    @DisplayName("lastIndexOf: ")
    @Nested
    class LastIndexOf {

        @DisplayName("값의 인덱스 조회 시 값이 없다면 " + List.ELEMENT_NOT_FOUND + "을 반환한다.")
        @Test
        void lastIndexOfNotFound() {
            // when
            final int foundIndex = list.lastIndexOf(random());

            // then
            assertThat(foundIndex).isEqualTo(List.ELEMENT_NOT_FOUND);
        }

        @DisplayName("첫 인덱스의 값을 조회한다.")
        @Test
        void lastIndexOfFirst() {
            // given
            final long value = random();
            list.add(value);

            // when
            final int foundIndex = list.lastIndexOf(value);

            // then
            assertThat(foundIndex).isEqualTo(FIRST_INDEX);
        }

        @DisplayName("값의 인덱스를 조회한다.")
        @Test
        void lastIndexOf() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int index = randomIndex();
            final long value = dump.get(index);

            // when
            final int foundIndex = list.lastIndexOf(value);

            // then
            assertThat(index).isEqualTo(foundIndex);
        }
    }

    @DisplayName("isEmpty: ")
    @Nested
    class IsEmpty {

        @DisplayName("값이 없다면 비어있다.")
        @Test
        void isEmpty() {
            // when
            final boolean isEmpty = list.isEmpty();

            // then
            assertThat(isEmpty).isTrue();
        }

        @DisplayName("값이 있다면 비어있지 않다.")
        @Test
        void nonEmpty() {
            // given
            insertDump();

            // when
            final boolean isEmpty = list.isEmpty();

            // then
            assertThat(isEmpty).isFalse();
        }
    }

    @DisplayName("size: ")
    @Nested
    class Size {

        @DisplayName("기본 사이즈는 " + List.EMPTY_SIZE + "이다.")
        @Test
        void defaultSize() {
            // when
            final int size = list.size();

            // then
            assertThat(size).isEqualTo(List.EMPTY_SIZE);
        }

        @DisplayName("사이즈를 조회한다.")
        @Test
        void size() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int expected = dump.size();

            // when
            final int size = list.size();

            // then
            assertThat(size).isEqualTo(expected);
        }

        @DisplayName("값을 추가하면 사이즈가 증가한다.")
        @Test
        void incrementSize() {
            // given
            insertDump();

            // when
            final int size = list.size();
            list.add(random());
            final int expected = size + 1;

            // then
            assertThat(list.size()).isEqualTo(expected);
        }

        @DisplayName("값을 제거하면 사이즈가 감소한다.")
        @Test
        void decrementSize() {
            // given
            final java.util.List<Long> dump = insertDump();

            // when
            final int size = list.size();
            list.remove(dump.get(FIRST_INDEX));
            final int expected = size - 1;

            // then
            assertThat(list.size()).isEqualTo(expected);
        }
    }

    @DisplayName("remove: ")
    @Nested
    class Remove {

        @DisplayName("값을 기준으로 제거한다.")
        @Test
        void removeByValue() {
            // given
            final long value = random();
            list.add(value);

            // when
            final boolean isRemoved = list.remove(value);

            // then
            assertThat(isRemoved).isTrue();
        }

        @DisplayName("여러 값을 기준으로 제거한다.")
        @Test
        void removeAllSuccessByValue() {
            // given
            final java.util.List<Long> dump = insertDump();
            final List<Long> values = new ArrayList<>();
            dump.forEach(values::add);

            // when
            final boolean isRemoved = list.removeAll(values);

            // then
            assertThat(isRemoved).isTrue();
        }

        @DisplayName("제거된 값이 없으면 false를 반환한다.")
        @Test
        void removeAllFailByValue() {
            // given
            insertDump();
            final List<Long> values = new ArrayList<>();

            // when
            final boolean isRemoved = list.removeAll(values);

            // then
            assertThat(isRemoved).isFalse();
        }

        @DisplayName("없는 값을 제거하면 false를 반환한다.")
        @Test
        void removeByValueNotExists() {
            // given
            final long value = random();

            // when
            final boolean isRemoved = list.remove(value);

            // then
            assertThat(isRemoved).isFalse();
        }

        @DisplayName("특정 위치에 값을 제거한다.")
        @Test
        void removeByIndex() {
            final java.util.List<Long> dump = insertDump();
            final int index = randomIndex();
            final long expected = dump.get(index);

            // when
            final long removeValue = list.remove(index);

            // then
            assertThat(removeValue).isEqualTo(expected);
        }

        @DisplayName("범위를 넘어가는 위치에 값을 제거하면 예외처리 한다.")
        @Test
        void removeByIndexOut() {
            final int index = randomInt();

            // when
            final ThrowableAssert.ThrowingCallable action = () -> list.remove(index);

            // then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(action);
        }
    }

    @DisplayName("contains: ")
    @Nested
    class Contains {

        @DisplayName("값이 존재한다.")
        @Test
        void contains() {
            // given
            final java.util.List<Long> dump = insertDump();
            final int index = randomIndex();
            final long value = dump.get(index);

            // when
            final boolean isContains = list.contains(value);

            // then
            assertThat(isContains).isTrue();
        }

        @DisplayName("값이 존재하지 않다.")
        @Test
        void notContains() {
            // given
            final long value = random();

            // when
            final boolean isContains = list.contains(value);

            // then
            assertThat(isContains).isFalse();
        }
    }

    @DisplayName("clear: ")
    @Nested
    class Clear {

        @DisplayName("리스트를 클리어한다.")
        @Test
        void clear() {
            // given
            insertDump();

            // when / then
            list.clear();
        }

        @DisplayName("빈 리스트를 클리어한다.")
        @Test
        void emptyClear() {
            // when / then
            list.clear();
        }

        @DisplayName("클리어하면 size가 초기화된다.")
        @Test
        void clearResetSize() {
            // given
            insertDump();

            // when
            list.clear();

            // then
            assertThat(list.size()).isEqualTo(List.EMPTY_SIZE);
        }
    }

    private int randomIndex() {
        return randomIndex(list.size());
    }

    private int randomIndex(final int end) {
        final int indexTerm = 1;
        return randomInt(end) - indexTerm;
    }

    private int randomInt() {
        return (int) random();
    }

    private long random() {
        return random(RANDOM_BOUND);
    }

    private int randomInt(final int bound) {
        return (int) random(bound);
    }

    private long random(final int bound) {
        return random(0, bound);
    }

    private long random(final int origin,
                        final int bound) {
        if (origin >= bound) {
            return origin;
        }

        return ThreadLocalRandom.current().nextLong(origin + 1, bound + 1);
    }

    private java.util.List<Long> insertDump() {
        return insertDump(random());
    }

    private java.util.List<Long> insertDump(final long size) {
        return LongStream.range(0, size)
                .peek(list::add)
                .boxed()
                .collect(toList());
    }
}