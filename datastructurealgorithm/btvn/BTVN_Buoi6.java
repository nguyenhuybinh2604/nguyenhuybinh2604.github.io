import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BTVN_Buoi6 {

    //https://leetcode.com/problems/neither-minimum-nor-maximum/submissions/978413943/
    public int findNonMinOrMax(int[] nums) {
        if (nums.length < 3) return -1;
        int num1 = nums[0];
        int num2 = nums[1];
        int num3 = nums[2];
        if ((num2 > num1 && num1 > num3) || (num3 > num1 && num1 > num2)) return num1;
        if ((num1 > num2 && num2 > num3) || (num3 > num2 && num2 > num1)) return num2;
        if ((num1 > num3 && num3 > num2) || (num2 > num3 && num3 > num1)) return num3;
        return -1;
    }

    //https://leetcode.com/problems/sort-an-array/submissions/983614039/
    public int[] sortArray_912(int[] nums) {
        mergesort_912(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergesort_912(int[] nums, int l, int r) {
        if (l < r) {
            // chia doi mang va sort 2 nua
            int mid = l + (r - l) / 2;
            // sort dung de quy
            mergesort_912(nums, l, mid);
            mergesort_912(nums, mid + 1, r);
            // thuc hien merge
            merge_912(nums, l, mid, r);
        }
    }

    public void merge_912(int[] nums, int start, int mid, int end) {
        // lay 2 ban sao nua dau va nua cuoi
        int[] arr1 = Arrays.copyOfRange(nums, start, mid + 1);
        int[] arr2 = Arrays.copyOfRange(nums, mid + 1, end + 1);
        int i = 0, j = 0;
        int index = start;
        // duyet dong thoi 2 mang va ghep vao theo tu tu be -> lon
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                nums[index] = arr1[i];
                i++;
            } else {
                nums[index] = arr2[j];
                j++;
            }
            index++;
        }
        // neu duyet xong 1 trong 2 mang ma mang kia van con phan tu -> ghep tiep so con lai
        while (i < arr1.length) {
            nums[index] = arr1[i];
            i++;
            index++;
        }
        while (j < arr2.length) {
            nums[index] = arr2[j];
            j++;
            index++;
        }
    }

    //https://leetcode.com/problems/array-partition/submissions/983620014/
    // de sum so min trong pair la lon nhat thi so do phai di kem voi mot so khac lon hon hoac bang
    // do do sort mang va chia tung pair (0,1),(2,3),(4,5)...(n-1,n). Lay tong cac so dung truoc.
    public int arrayPairSum_561(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) sum += nums[i];
        }
        return sum;
    }

    //https://leetcode.com/problems/longest-square-streak-in-an-array/submissions/983634837/
    public int longestSquareStreak_2501(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> countMap = new HashMap<>();
        int sum = -1;
        for (int i = 0; i < nums.length; i++) {
            int sqrt = (int) Math.sqrt(nums[i]);
            // kiem tra xem da xuat hien can 2 cua phan tu i chua
            if (sqrt * sqrt == nums[i] && countMap.containsKey(sqrt)) {
                // neu da xuat hien can 2 cua nums[i] -> update STT cua nums[i] theo sqrt(nums[i])
                // tuong duong voi viec ghep sqrt(nums[i]) vao chuoi va danh lai STT
                countMap.put(nums[i], countMap.get(sqrt) + 1);
                // so sanh sum voi do dai chuoi gan nhat duoc tim thay de lay max do dai chuoi
                if (countMap.get(sqrt) + 1 > sum) {
                    sum = countMap.get(sqrt) + 1;
                }
                //neu chua xuat hien can 2 cua nums[i] -> nums[i] la phan tu dau tien trong chuoi binh phuong
            } else {
                countMap.put(nums[i], 1);
            }
        }
        return sum;
    }
}
