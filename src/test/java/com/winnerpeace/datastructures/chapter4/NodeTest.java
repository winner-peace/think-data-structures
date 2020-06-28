package com.winnerpeace.datastructures.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class NodeTest {

    @DisplayName("첫 값을 생성한다.")
    @Test
    void first() {
        // given
        final int value = 100;

        // when
        final Node<Integer> node = Node.first(value);

        // then
        assertThat(node).isNotNull();
    }

    @DisplayName("값을 가져온다.")
    @Test
    void getValue() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);

        // when
        final int getValue = node.getValue();

        // then
        assertThat(getValue).isEqualTo(value);
    }

    @DisplayName("값을 비교한다.")
    @Test
    void equalsValue() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);

        // when
        final boolean equalsValue = node.equalsValue(value);

        // then
        assertThat(equalsValue).isTrue();
    }

    @DisplayName("링크를 연결해여 노드를 생성한다.")
    @Test
    void next() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);

        // when
        final Node<Integer> nextNode = node.createNext(value);

        // then
        assertThat(nextNode.getValue()).isEqualTo(value);
    }

    @DisplayName("다음 노드가 존재한다.")
    @Test
    void hasNextExists() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);
        node.createNext(value);

        // when
        final boolean hasNext = node.hasNext();

        // then
        assertThat(hasNext).isTrue();
    }

    @DisplayName("다음 노드가 존재하지 않는다.")
    @Test
    void hasNextNonExists() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);

        // when
        final boolean hasNext = node.hasNext();

        // then
        assertThat(hasNext).isFalse();
    }

    @DisplayName("다음 노드를 가져온다.")
    @Test
    void getNextExists() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);
        final Node<Integer> nextNode = node.createNext(value);

        // when
        final Optional<Node<Integer>> foundNext = node.getNext();

        // then
        assertThat(foundNext).isPresent()
                .hasValue(nextNode);
    }

    @DisplayName("다음 노드가 없다면 가져오지 못한다.")
    @Test
    void getNextNotExists() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);

        // when
        final Optional<Node<Integer>> foundNext = node.getNext();

        // then
        assertThat(foundNext).isNotPresent();
    }

    @DisplayName("마지막 위치에 노드를 생성한다.")
    @Test
    void append() {
        // given
        final int value = 100;
        final Node<Integer> node = Node.first(value);
        final Node<Integer> nextNode = node.createNext(value);

        // when
        final Node<Integer> appendNode = node.append(value);

        // then
        assertThat(nextNode.getNext()).isPresent()
                .hasValue(appendNode);
    }

    @DisplayName("다음 노드를 변경한다.")
    @Test
    void changeNext() {
        // given
        final int value = 100;
        final int changeValue = 300;
        final Node<Integer> firstNode = Node.first(value);
        firstNode.createNext(200);

        // when
        firstNode.changeNext(changeValue);

        // then
        assertThat(firstNode.getNext()).isPresent()
                .map(Node::getValue)
                .hasValue(changeValue);
    }

    @DisplayName("다음 노드를 변경하면 기존 노드를 반환한.")
    @Test
    void changeNextReturnOriginalNode() {
        // given
        final int value = 100;
        final Node<Integer> firstNode = Node.first(value);
        final Node<Integer> expected = firstNode.createNext(200);

        // when
        final Node<Integer> changeTargetNode = firstNode.changeNext(300);

        // then
        assertThat(changeTargetNode).isEqualTo(expected);
    }

    @DisplayName("다음 노드를 제거한다.")
    @Test
    void removeNext() {
        // given
        final int value = 100;
        final int nextValue = 200;
        final Node<Integer> firstNode = Node.first(value);
        firstNode.createNext(nextValue);

        // when
        final Optional<Node<Integer>> removeTargetNode = firstNode.removeNext();

        // then
        assertThat(removeTargetNode).isPresent()
                .map(Node::getValue)
                .hasValue(nextValue);
    }

    @DisplayName("다음 노드가 없는 상태에서 제거하면 빈값을 반환한다.")
    @Test
    void removeNextNotExists() {
        // given
        final int value = 100;
        final Node<Integer> firstNode = Node.first(value);

        // when
        final Optional<Node<Integer>> removeTargetNode = firstNode.removeNext();

        // then
        assertThat(removeTargetNode).isNotPresent();
    }
}