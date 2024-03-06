package oy.tol.tra;

public class Algorithms {

    public static int binarySearch(int target, Integer[] array, int left, int right) {
        while (left <= right) {
            int middle = left + (right - left)/2;
            if(array[middle] > target){
                right = middle - 1;
            }
            else if (array[middle] < target) {
                left = middle + 1;
            }
            else{
                return middle;
            }
        }
        return -1;
    }

    public static void sort(Integer[] array) {
        int len = array.length;
        for(int i = 0; i < len-1; i++){
            for(int j = 0; j < len - i - 1; j++){
               if (array[j] > array[j+1]) {        
                  int temp = array[j+1];        
                  array[j+1] = array[j];
                  array[j] = temp;
              }
            }
         }
        
    }

    public static void sort(String[] array) {
        int len = array.length;
        for(int i = 0; i < len - 1; i++){
            for(int j = 0; j < len - i - 1; j++){
               if (array[j].compareTo(array[j + 1]) > 0) {        
                  String temp = array[j+1];        
                  array[j+1] = array[j];
                  array[j] = temp;
              }
            }
         }
    }

    public static int binarySearch(String target, String[] array, int left, int right) {
        while (left<=right) {
            int middle = left + (right - left)/2;
            if(array[middle].compareTo(target) > 0){
                right = middle - 1;
            }
            else if(array[middle].compareTo(target) < 0) {
                left = middle + 1;
            }
            else{
                return middle;
            }
        }
        return -1;
        
    }

}
