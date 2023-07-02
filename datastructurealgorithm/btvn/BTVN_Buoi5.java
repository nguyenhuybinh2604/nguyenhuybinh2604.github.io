import java.util.Arrays;

public class BTVN_Buoi5 {

    //https://leetcode.com/problems/binary-search/submissions/973336446/
    public int binarySearch_704(int[] arr, int k, int begin, int end) {
        if (arr[begin] > k || arr[end] < k) return -1;
        if (arr[begin] == k) return begin;
        if (arr[end] == k) return end;
        if (begin + 1 == end) return -1;
        int mid = (begin + end) / 2;
        if (arr[mid] > k) return binarySearch_704(arr, k, begin, mid);
        else return binarySearch_704(arr, k, mid, end);
    }

    //https://leetcode.com/problems/search-insert-position/submissions/973831630/
    public int searchInsert_35(int[] nums, int target) {
        return recurse_35(nums, target, 0, nums.length - 1);
    }

    public int recurse_35(int[] arr, int k, int begin, int end) {
        //neu be hon search range thi thay the diem dau
        if (arr[begin] > k) return begin;

        //neu lon hon search range thi add vao ngay sau diem cuoi
        if (arr[end] < k) return end + 1;

        //neu la cac diem o border thi tra luon gia tri border
        if (arr[begin] == k) return begin;
        if (arr[end] == k) return end;

        //neu range chi con 2 phan tu & khong thuoc cac truong hop o tren -> value nam giua 2 phan tu -> insert vao vi tri phan tu thu 2
        if (begin + 1 == end) return begin + 1;

        //neu khong thuoc truong hop nao o tren thi tiep tuc chia va tim kiem
        int mid = (begin + end) / 2;
        if (arr[mid] > k) return recurse_35(arr, k, begin, mid);
        else return recurse_35(arr, k, mid, end);
    }

    //https://leetcode.com/problems/guess-number-higher-or-lower/submissions/973841389/
//    public int guessNumber_374(int n) {
//        int low = 1;
//        int high = n;
//        int mid;
//        while (low <= high) {
//            mid = low + (high - low) / 2;
//            if (guess(mid) == 0) {
//                return mid;
//            } else if (guess(mid) == -1) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;
//            }
//        }
//        return -1;
//    }

    //https://leetcode.com/problems/missing-number/submissions/973861455/
    public int missingNumber_268(int[] nums) {
        Arrays.sort(nums);
        return recurse_268(nums, 0, nums.length - 1);
    }

    public int recurse_268(int[] arr, int low, int high) {
        //neu be hon search range thi thay the diem dau
        if (arr[low] > low) return low;

        //neu lon hon search range thi add vao ngay sau diem cuoi
        if (arr[high] > high && arr[high - 1] == high - 1) return high;

        //neu range chi con 2 phan tu & khong thuoc cac truong hop o tren -> value nam giua 2 phan tu -> insert vao vi tri phan tu thu 2
        if (arr[high] == high) return high + 1;

        //neu khong thuoc truong hop nao o tren thi tiep tuc chia va tim kiem
        int mid = (low + high) / 2;
        if (arr[mid] > mid) return recurse_268(arr, low, mid);
        else return recurse_268(arr, mid, high);
    }

    //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/submissions/974051680/
    public int findTheDistanceValue_1385(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            //tim vi tri cua arr1[i] trong arr2 da sort
            int nIndex = recurse_35(arr2, arr1[i], 0, arr2.length - 1);
            if (nIndex == 0 && arr2[0] - arr1[i] > d) count++;
            else if (nIndex == arr2.length && arr1[i] - arr2[arr2.length - 1] > d) count++;
            else if (nIndex != 0 && nIndex != arr2.length && arr1[i] - arr2[nIndex - 1] > d && arr2[nIndex] - arr1[i] > d)
                count++;
        }
        return count;
    }

    public int fib(int n) {
        if (n == 0) return 0; // 2
        else if (n == 1) return 1; // 2
        else return fib(n - 1) + fib(n - 2); // O(n-1) + O(n-2)
    }
    // 2 + 2 + (4+2) + (6+2) + (10+2) + ...
}
