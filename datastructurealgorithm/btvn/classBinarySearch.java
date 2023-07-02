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

    public int[] sortArray(int[] nums) {


        return mergeSort(nums, 0, nums.length - 1);
    }

    int[] mergeSort(int[] nums, int l, int r) {
        if (l == r) {
            return new int[]{nums[l]};
        }

        // chia va sort
        int mid = l + (r - l)/2;
        int[] a1 = mergeSort(nums, l, mid);
        int[] a2 = mergeSort(nums, mid+1, r);

        // merge
        int[] result = merge(a1, a2);

        return result;
    }

    int[] merge(int[] a1, int[] a2) {
        int result[] = new int[a1.length + a2.length];
        int i1 = 0, i2 = 0; //
        int idx = 0;

        while (idx < result.length) {
            if(i1 < a1.length && i2 < a2.length) {
                if(a1[i1] < a2[i2]) {
                    result[idx] = a1[i1];
                    idx++;
                    i1++;
                } else {
                    result[idx] = a2[i2];
                    idx++;
                    i2++;
                }
            } else {
                if(i1 < a1.length) {
                    result[idx] = a1[i1];
                    idx++;
                    i1++;
                } else {
                    result[idx] = a2[i2];
                    idx++;
                    i2++;
                }
            }
        }

        return result;
    }

    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; ++i) {
            int square = i * i;
            if (checkNumber(i, square)) {
                sum = sum + (i * i);
            }
        }
        return sum;
    }

    public boolean checkNumber(int target, int number) {
        //Truong hop so can kiem tra bang dung so muc tieu thi khong can check them, tra ve true
        if (number == target) return true;
        //Neu so can kiem tra da nho hon muc tieu -> khi chia thanh cac digit -> tong digit chac chan tiep tuc nho hon -> Khong dap ung dkien tong digit = target
        if (number < target) return false;
        //Neu lan de quy (t-1) so digit cong vao da vuot qua target (t-1) dan den target (t) < 0 -> Khong co loi giai thoa man
        if (target < 0) return false;
        //Dung de quy tach dan tung chu so tu phai sang trai va dung de quy kiem tra tren phan con lai
        //Thu tu tu phai sang trai la de tan dung phep chia mod
        //Tach 01 chu so dau tien va kiem tra (...)x10 + x = target
        boolean check1 = checkNumber(target - number % 10, number / 10);
        //Tach 2 chu so dau tien va kiem tra (...)x100 + xx = target
        boolean check2 = checkNumber(target - number % 100, number / 100);
        //Tuong tu nhu tren va chi can chia toi da 3 lan vi sau lan de quy nay phan con lai toi da chi 3 chu so ( max n = 1 000, n^2 = 1 000 000)
        boolean check3 = checkNumber(target - number % 1000, number / 1000);
        //kiem tra xem co it nhat 01 truong hop nao dap ung khong
        return check1 || check2 || check3;
    }

    public boolean isHappy(int n) {
        //Map de kiem tra so da trai qua chua
        Map<Integer, Integer> checkMap = new HashMap<>();
        //Chay de quy
        return recursive_202(n, checkMap) == 1;
    }

    public int recursive_202(int n, Map<Integer, Integer> checkMap) {
        //Neu gap 1 tra ra ket qua
        if (n == 1) return 1;
        //Neu n da gap -> vong lap vo han -> khong phai happy number
        if (checkMap.containsKey(n)) return -1;
            //Neu chua gap add vao map de theo doi
        else checkMap.put(n, 1);

        //Tinh tong binh phuong cac chu so
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum = sum + digit * digit;
            n = n / 10;
        }
        //Dung de quy chay tiep cho ket qua
        return recursive_202(sum, checkMap);
    }
}
