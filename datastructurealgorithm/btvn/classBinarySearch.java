import java.sql.Array;
import java.util.*;

public class classBinarySearch {

    //https://leetcode.com/problems/binary-search/submissions/973336446/
    public int binarySearch(int[] arr, int k, int begin, int end) {
        if (arr[begin] > k || arr[end] < k) return -1;
        if (arr[begin] == k) return begin;
        if (arr[end] == k) return end;
        if (begin +1 == end ) return -1;
        int mid = (begin + end) / 2;
        if (arr[mid] > k) return binarySearch(arr, k, begin, mid);
        else return binarySearch(arr, k, mid, end);
    }


}
