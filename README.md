# 자바로 배우는 핵심 자료구조와 알고리즘

> 기술 면접에 필요한 실용주의 자료구조와 알고리즘



### 개요

* **주제** : Java, 자료구조, 알고리즘

* **기간** : 2020/06/21 ~ 2020/08/02 (7주)

* **스터디 시간** : 매주 일요일 10시 ~ 12시 (2시간)

* **스터디 장소** : 신도림, 홍대, 신촌 인근 스터디룸

* **스터디원** : 완료 3명 / 시작 6명 (50%)
    - [jaeyeonling](https://github.com/jaeyeonling)
    - [ohtaeg](https://github.com/ohtaeg)
    - [ChanhaPrk](https://github.com/ChanhaPrk)
     

### 1주차 - OT

#### 스터디 기간
    1. 스터디는 총 5주간 진행한다.
        - Chapter 3, 4, 5 - 어레이 리스트, 링크드 리스트, 이중 연결 리스트
        - Chapter 6 - 트리 순회, BFS, DFS
        - Chapter 7, 8 - 철학으로 가는 길, 인덱서
        - Chapter 9, 10, 11, 12 - Map, 해싱, 해시맵, 트리맵
        - Chapter 13, 17 - 이진 탐색 트리, 정렬
        - 회고 및 미비한 부분 마무리
    2. 챕터 14, 15, 16 (영속성, 위키피디아 크롤링, 불리언 검색)은 실습 위주로 진행되기 때문에 추후 결정한다.


#### 스터디 진행 방법
    1. 프로젝트에 관련된 내용은 Github 기반으로 진행하며, 커뮤니케이션은 슬랙을 이용한다.
    2. 정해진 챕터에 대해 개인적으로 학습한다.
    3. 1..n명 발표자를 선정하여 오프라인 스터디에서 발표를 진행한다.
    4. 발표에 대한 예시 (예시일 뿐 어떠한 방법이든 상관없습니다)
        * 해당 자료구조를 사용하는 알고리즘 문제를 풀기
        * 해당 자료구조를 이용하는 프로젝트 진행
        * 자료구조에 대한 정리 후 전달
        * 자료구조 라이브 코딩
        * ...

#### 스터디 회비
    1. 다음 주 스터디 룸 예약비를 미리 걷는다. (다음 주까지 예약)
    2. 이미 지불한 스터디 룸 예약비에 대한 환불은 불가능하다.

#### 페널티
    1. 무단으로 불참 2회 시 퇴출 당한다. (최소 하루 전 통보)


### 2주차

#### [Chapter 3 - ArrayList 클래스](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/list/ArrayList.java)

##### 3.1 - MyArrayList 메서드 분류하기

##### 3.2 - add 메서드 분류하기

- add 시 배열을 늘릴 때는 복사하는 과정이 있기 때문에 o(1)이 아닌 o(n)이지만 o(1)이라고 한다.

- 그 이유는 [분할 상환 분석](/winner-peace/think-data-structures/issues/3) 때문이다.

##### 3.3 - 문제 크기

- 반복문으로 collection 인자의 각 요소를 순회하므로, O(nm)

- collection의 크기가 상수라면 n에 대해 선형

- collection의 크기가 n에 비례한다면 이차

- 알고리즘 분석에서 반복문의 갯수로 판단하는 실수를 하지 말자.

##### 3.4 - 연결 자료구조

##### 3.5 - 실습3

##### 3.6 - 가비지 컬렉션

- GC와 같은 작업 때문에 실제 애플리케이션 동작과 시간복잡도가 일치하지 않을 수 있다.

- **책에선 이러한 상황을 주의하라고 했지만 그럴 필요가 있을까?**

#### [Chapter 4 - LinkedList 클래스](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/list/LinkedList.java)

##### 4.1 - MyLinkedList 메서드 분류하기

##### 4.2 - MyArrayList와 MyLinkedList 비교하기

##### 4.3 - [프로파일](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/Profiler.java)

##### 4.4 - 결과 해석하기

##### 4.5 - 실습4

#### [Chapter 5 - 이중 연결 클래스](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/list/DoubleLinkedList.java)

##### 5.1 - [성능 프로파일 결과](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/list/ListProfiler.java)


##### 5.2 - LinkedList 메서드 프로파일하기

##### 5.3 - LinkedList 끝에 더하기

##### 5.4 - 이중 연결 리스트

##### 5.5 - 자료구조 선택하기

| 구분                | ArrayList | LinkedList | DoubleLinkedList |
| ------------------- | --------- | ---------- | ---------------- |
| add(끝)             | 1         | n          | 1                |
| add(시작)           | n         | 1          | 1                |
| add(일반적으로)     | n         | n          | n                |
| get/set             | 1         | n          | n                |
| indexOf/lastIndexOf | n         | n          | n                |
| isEmpty/size        | 1         | 1          | 1                |
| remove(끝)          | 1         | n          | 1                |
| remove(시작)        | 1         | 1          | 1                |
| remove(일반적으로)  | n         | n          | n                |

* 시간 복잡도도 자료구조 선택의 조건이 되지만 공간복잡도도 고려해야 한다.

---

> Chater 5까지 책으로 진행 후, 책이 스터디 방향과 적합하지 않아 자체 커리큘럼으로 진행 
>
> 자체 커리큘럼은 주요 자료구조와 주요 정렬을 본인의 생각대로 구현하거나 공부한 후 어떠한 특징을 가지는지 의견을 공유

### 3주차

#### [Queue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue)
- [ArrayCircularQueue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue/circular/ArrayCircularQueue.java)
- [ArrayListQueue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue/ArrayListQueue.java)
- [ArrayQueue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue/ArrayQueue.java)
- [LinkedListQueue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue/LinkedListQueue.java)
- [NodeQueue](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/queue/NodeQueue.java)

#### [Deque](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/deque)
- [LinkedListDeque](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/deque/LinkedListDeque.java)
- [NodeDeque](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/deque/NodeDeque.java)

#### [Stack](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/stack)
- [ArrayListStack](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/stack/ArrayListStack.java)
- [ArrayStack](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/stack/ArrayStack.java)
- [LinkedListStack](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/stack/LinkedListStack.java)
- [NodeStack](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/stack/NodeStack.java)

### 4, 5주차

#### Tree

##### [재연링의 트리](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree)
- [Binary Tree Order](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/PrintableBinaryTree.java)
- [Binary Search Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/BasicBinarySearchTree.java), [Binary Search Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/SimpleBinarySearchTree.java)
- [B- Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/BalancedMinusTree.java)
- [AVL Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/AvlTree.java)
- [Red Black Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/jaeyeonling/tree/RedBlackTree.java)

##### [태태로의 트리](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree)
- [Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/Tree.md)
- [Binary Search Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/BinarySearchTree.md)
- [B- Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/B-Tree.md)
- [B+ Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/B+Tree.md)
- [AVL Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/AVLTree.md)
- [Red Black Tree](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/tree/RedBlackTree.md)

### 6주차

#### [Hash](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/hash/Hash.md)

### 7주차

#### Sort

##### [재연링의 정렬](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort)
- [BubbleSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/BubbleSortTest.java)
- [InsertionSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/InsertionSortTest.java)
- [SelectionSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/SelectionSortTest.java)
- [MergeSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/MergeSortTest.java)
- [RightQuickSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/RightQuickSortTest.java)
- [MiddleQuickSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/MiddleQuickSortTest.java)
- [RandomQuickSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/RandomQuickSortTest.java)
- [HeapSort](/winner-peace/think-data-structures/blob/master/src/test/java/com/winnerpeace/datastructures/jaeyeonling/sort/HeapSortTest.java)

##### [태태로의 정렬](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort)
- [MergeSort](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/merge)
    - [개념](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/merge/MergeSort.md)
    - [구현](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/merge/Merge.java)

- [QuickSort](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/quick)
    - [개념](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/quick/QuickSort.md)
    - [구현](/winner-peace/think-data-structures/blob/master/src/main/java/com/winnerpeace/datastructures/ohtaeg/sort/quick/Quick.java)
