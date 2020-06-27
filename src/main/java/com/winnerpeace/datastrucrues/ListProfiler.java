package com.winnerpeace.datastrucrues;

import com.winnerpeace.datastrucrues.chapter3.ArrayList;
import com.winnerpeace.datastrucrues.chapter4.LinkedList;
import com.winnerpeace.datastrucrues.chapter5.DoubleLinkedList;
import org.jfree.data.xy.XYSeries;

import java.util.function.Supplier;

class ListProfiler {

    private static final String message = "message";
    private static final int start = 100;
    private static final int endMillis = 400;

    public static void main(final String... args) {
        final var arrayListProfiler = new Profile("ArrayList", ArrayList::new);
        final var linkedListProfiler = new Profile("LinkedList", LinkedList::new);
        final var doubleLinkedListProfiler = new Profile("DoubleKinkedList", DoubleLinkedList::new);

        final var profiler = arrayListProfiler;

        profiler.add();
        profiler.addStartPosition();
        profiler.addEndPosition();
        profiler.get();
        profiler.set();
        profiler.indexOf();
        profiler.lastIndexOf();
        profiler.isEmpty();
        profiler.size();
        profiler.remove();
        profiler.removeStartPosition();
        profiler.removeEndPosition();
    }

    private static class Profile {

        private final String title;
        private final Supplier<List<String>> listSupplier;

        public Profile(final String title,
                       final Supplier<List<String>> listSupplier) {
            this.title = title;
            this.listSupplier = listSupplier;
        }

        private void remove() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.remove(message);
                    }
                }
            };

            runProfiler(title + ".remove", timeable);
        }

        private void removeStartPosition() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.remove(0);
                    }
                }
            };

            runProfiler(title + ".removeStartPosition", timeable);
        }

        private void removeEndPosition() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.remove(n - i - 1);
                    }
                }
            };

            runProfiler(title + ".removeEndPosition", timeable);
        }

        private void indexOf() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.indexOf(message);
                    }
                }
            };

            runProfiler(title + ".indexOf", timeable);
        }

        private void lastIndexOf() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.lastIndexOf(message);
                    }
                }
            };

            runProfiler(title + ".lastIndexOf", timeable);
        }

        private void isEmpty() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.isEmpty();
                    }
                }
            };

            runProfiler(title + ".isEmpty", timeable);
        }

        private void size() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.size();
                    }
                }
            };

            runProfiler(title + ".size", timeable);
        }

        private void addStartPosition() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.add(0, message);
                    }
                }
            };

            runProfiler(title + ".addStartPosition", timeable);
        }

        private void addEndPosition() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.add(list.size(), message);
                    }
                }
            };

            runProfiler(title + ".addEndPosition", timeable);
        }

        private void add() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }
            };

            runProfiler(title + ".add", timeable);
        }

        private void get() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.get(i);
                    }
                }
            };

            runProfiler(title + ".get", timeable);
        }

        private void set() {
            final var timeable = new com.winnerpeace.datastrucrues.Profiler.Timeable() {
                List<String> list;

                public void setup(int n) {
                    list = listSupplier.get();
                    for (int i = 0; i < n; i++) {
                        list.add(message);
                    }
                }

                public void timeMe(int n) {
                    for (int i = 0; i < n; i++) {
                        list.set(n, message);
                    }
                }
            };

            runProfiler(title + ".set", timeable);
        }
    }

    /**
     * Runs the profiles and displays results.
     */
    private static void runProfiler(String title, com.winnerpeace.datastrucrues.Profiler.Timeable timeable) {
        com.winnerpeace.datastrucrues.Profiler profiler = new com.winnerpeace.datastrucrues.Profiler(title, timeable);
        XYSeries series = profiler.timingLoop(start, endMillis);
        profiler.plotResults(series);
    }
}