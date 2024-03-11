package oy.tol.tra;

public class Algorithms {

    public static int binarySearch(int target, Integer[] array, int left, int right) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] > target) {
                right = middle - 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void sort(Integer[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

    }

    public static void sort(String[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static int binarySearch(String target, String[] array, int left, int right) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle].compareTo(target) > 0) {
                right = middle - 1;
            } else if (array[middle].compareTo(target) < 0) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;

    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        int left = fromIndex;
        int right = toIndex;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (fromArray[middle].compareTo(aValue) > 0) {
                right = middle - 1;
            } else if (fromArray[middle].compareTo(aValue) < 0) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;

    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        // implement Quicksort here...
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex-1);
            quickSort(array, partitionIndex+1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        // implement partition here...
        E pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
}
