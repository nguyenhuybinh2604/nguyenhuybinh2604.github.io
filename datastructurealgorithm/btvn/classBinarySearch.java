import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class classBinarySearch {

    //https://leetcode.com/problems/binary-search/submissions/973336446/
    public int binarySearch(int[] arr, int k, int begin, int end) {
        if (arr[begin] > k || arr[end] < k) return -1;
        if (arr[begin] == k) return begin;
        if (arr[end] == k) return end;
        if (begin + 1 == end) return -1;
        int mid = (begin + end) / 2;
        if (arr[mid] > k) return binarySearch(arr, k, begin, mid);
        else return binarySearch(arr, k, mid, end);
    }

    //https://leetcode.com/problems/relative-sort-array/submissions/974099047/
    public int[] relativeSortArray_1122(int[] arr1, int[] arr2) {
        int index1 = 0, index2 = 0;
        //duyet dan ca 2 mang tu dau den cuoi
        while (index1 < arr1.length || index2 < arr2.length) {
            //thuc hien tim kiem va sap xep neu chua duyet het mang 2
            if (index2 < arr2.length) {
                //duyet arr1 (tiep theo sau phan da sap xep tai index1)
                for (int i = index1; i < arr1.length; i++) {
                    //neu da bang san thi chi can day len 1 buoc
                    if (arr1[index1] == arr2[index2]) index1++;
                        //doi vi tri neu tim thay phan tu giong phan tu dang duyet o arr2
                    else if (arr1[index1] != arr2[index2] && arr1[i] == arr2[index2]) {
                        int tmp = arr1[index1];
                        arr1[index1] = arr1[i];
                        arr1[i] = tmp;
                        //day phan da duyet len truoc 1 buoc
                        index1++;
                    }
                }
                //ket thuc mot vong duyet la da sap xep xong arr2[index2] -> day arr2 len mot buoc
                index2++;
            } else {
                //sau khi da xep het array2 -> sort phan con lai
                Arrays.sort(arr1, index1, arr1.length);
                index1 = arr1.length;
            }
        }
        return arr1;
    }

    //https://leetcode.com/problems/maximum-product-of-three-numbers/submissions/974107221/
    public int maximumProduct_628(int[] nums) {
        Arrays.sort(nums);
        return Math.max(Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3], // 0 - 3
                nums[nums.length - 1] * nums[nums.length - 2] * nums[0]), // 1 - 2
                Math.max(nums[nums.length - 1] * nums[1] * nums[0], // 2 - 1
                        nums[2] * nums[1] * nums[0])); // 3 - 0
    }

    //https://leetcode.com/problems/non-overlapping-intervals/submissions/974180619/
    public int eraseOverlapIntervals_435(int[][] intervals) {
        int n = intervals.length;
        //sort theo right border de ap dung thuat toan tham lam
        Arrays.sort(intervals, Comparator.comparingDouble(o -> o[1]));
        int last = intervals[0][1];
        int count = 1;
        //xay chuoi tach biet dai nhat;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= last) {
                count++;
                last = intervals[i][1];
            }
        }
        //tra ket qua = tong so interval - do dai cua chuoi = so interval toi thieu can loai ra
        return n - count;
    }

    //https://leetcode.com/problems/top-k-frequent-elements/submissions/974210423/
    public int[] topKFrequent_347(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
//dem tan suat xuat hien cua tung phan tu va gan vao map
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        //sort map de day cac phan tu xuat hien nhieu nhat len tren
        countMap = countMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int[] ans = new int[k];
        int index = 0;
        //chay vong lap den k de nhat tung key
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            ans[index] = entry.getKey();
            index++;
            if (index == k) break;
        }
        return ans;
    }

}
