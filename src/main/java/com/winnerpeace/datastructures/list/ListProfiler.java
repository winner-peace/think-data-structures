package com.winnerpeace.datastructures.list;

import com.winnerpeace.datastructures.Profiler;
import org.jfree.data.xy.XYSeries;

import java.util.function.Supplier;

class ListProfiler {

    private static final String message = "message";
    private static final int start = 100;
    private static final int endMillis = 400;

    public static void main(final String... args) {
        final Profile arrayListProfiler = new Profile("ArrayList", ArrayList::new);
        final Profile linkedListProfiler = new Profile("LinkedList", LinkedList::new);
        final Profile doubleLinkedListProfiler = new Profile("DoubleKinkedList", DoubleLinkedList::new);

        final Profile profiler = arrayListProfiler;

//        profiler.add();
//        profiler.addStartPosition();
//        profiler.addEndPosition();
//        profiler.get();
//        profiler.set();
//        profiler.indexOf();
//        profiler.lastIndexOf();
//        profiler.isEmpty();
//        profiler.size();
//        profiler.remove();
//        profiler.removeStartPosition();
//        profiler.removeEndPosition();
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
            final Profiler.Timeable timeable = new Profiler.Timeable() {
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
    private static void runProfiler(String title, Profiler.Timeable timeable) {
        Profiler profiler = new Profiler(title, timeable);
        XYSeries series = profiler.timingLoop(start, endMillis);
        profiler.plotResults(series);
    }
}