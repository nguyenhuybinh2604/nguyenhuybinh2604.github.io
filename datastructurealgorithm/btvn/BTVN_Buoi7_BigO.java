import java.util.*;

public class BTVN_Buoi7_BigO {

    // https://leetcode.com/problems/fibonacci-number/submissions/984266332/
    // Bo nho: tao 1 mang n+1, ngoai ra chi su dung them bien n => O(n)
    // Toc do: moi vong de quy thuc hien 4 lan check & 3 thao tac & do dai 2 vong de
    // quy n-1 & n-2
    // Tinh tu duoi len do da luu vao array -> Moi vong de quy mat khoang 4 lan
    // check & 3 thao tac
    // Chay het n vong -> Khoang 7n thao tac ~O(n)
    public int fib_509(int n) {
        // return cac truong hop 0 va 1
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            // neu khong moi tao mang ghi nho, n+1 vi co n so nguyen duong & so 0
            int[] nums = new int[n + 1];
            // khoi tao 2 gia tri ban dau
            nums[0] = 0;
            nums[1] = 1;
            // chay de quy doi voi n
            return recursive_509(nums, n);
        }
    }

    private int recursive_509(int[] nums, int n) {
        // dieu kien dung neu n=0 hoac nums[n] da duoc tinh san
        if (n == 0 || nums[n] != 0)
            return nums[n];
        // tinh va luu lai neu nums[n-1]<>0 va nums[n] chua duoc tinh
        if (nums[n - 1] != 0 && nums[n] == 0) {
            nums[n] = nums[n - 1] + nums[n - 2];
            return nums[n];
            // neu so lien truoc chua duoc tinh va luu lai -> goi de quy lui ve
            // do de quy lui nen tinh cho n-2 truoc -> co luon ket qua dung cho n-1
        } else
            return recursive_509(nums, n - 2) + recursive_509(nums, n - 1);
    }

    // https://leetcode.com/problems/to-lower-case/submissions/984252423/
    // Bo nho: stringbuilder & i -> O(n)
    // Toc do: vong for: n*(check i<sb.lenth & (i++) & check char la uppercase &
    // convert) = 4n, + sb.toString = 5n -> ~O(n);
    public String toLowerCase_709(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            // kiem tra neu co phai uppercase khong
            if (sb.charAt(i) >= 'A' && sb.charAt(i) <= 'Z') {
                // neu co, thay the bang lowercase
                sb.setCharAt(i, (char) (sb.charAt(i) + 32));
            }
        }
        // convert sb ve lai string va tra ket qua
        return sb.toString();
    }

    // https://leetcode.com/problems/merge-sorted-array/submissions/984465904/
    // Bo nho: Tao ra 03 bien i, j, k, khong tao them mang moi ->~O(0);
    // Toc do: Toi thieu n lan, toi da n + m lan -> O(n+m);
    // Thay vi tao 01 mang moi va duyet tu duoi len -> tan dung khoang trong n slot
    // cua nums1 va duyet tu tren xuong.
    // Phan tu nums1[i] khong bi ghi de vi de den duoc i thi:
    // Hoac toan bo nums2 da duoc ghep vao,
    // Neu den i ma nums2 van con phan tu -> cac phan tu nums1[i] van lon hon ->
    // tiep tuc duoc day ve sau
    public void merge_88(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // thuc hien den khi ghep het nums2 vao nums1
        while (j >= 0) {
            // lay so lon hon ghep vao cho trong gan nhat tu phai sang trai
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array/submissions/984486551/
    // Bo nho: tao them mang ans[n] + 3 bien left, right, k -> O(n)
    // Toc do: duyet nums.length lan x 14 phep tinh -> 14n ~ O(n)
    public int[] sortedSquares_977(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int k = nums.length - 1;
        // Do mang goc da sort -> Chay tu 2 dau ve 0 de lay so co binh phuong lon hon
        // Chen vao mang ans tu phai sang trai (theo k)
        while (left <= right) {
            // Neu ben trai lon hon -> Insert vao truoc
            if (nums[left] * nums[left] >= nums[right] * nums[right]) { // 5
                ans[k] = nums[left] * nums[left]; // 3
                left++; // 1
                // sau moi lan thanh cong lui mang ans sang trai
                k--; // 1
                // Nguoc lai thuc hien tu ben phai
            } else {
                ans[k] = nums[right] * nums[right]; // 3
                right--; // 1
                // sau moi lan thanh cong lui mang ans sang trai
                k--; // 1
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/check-if-n-and-its-double-exist/submissions/984499613/
    // Bo nho: Tao them hashmap (size~n) + 2 bien -> O(n);
    // Toc do: duyet vong for 1 lan qua n so, moi vong thuc hien khoang 10 phep tinh
    // -> ~O(n)
    public boolean checkIfExist_1346(int[] arr) {
        Map<Integer, Integer> doubleMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // check neu ton tai so 1 nua & so gap doi cua arr[i]
            int halfNum = arr[i] / 2;
            // do arr[i] max = 1000 -> so gap doi khong bi tran so
            int doubleNum = arr[i] * 2;
            // check ton tai so 1 nua
            if (halfNum * 2 == arr[i] && doubleMap.containsKey(halfNum))
                return true;
            // check ton tai so gap doi
            else if (doubleMap.containsKey(doubleNum))
                return true;
            // neu khong tim thay so 1 nua/so gap doi va chua ghi nhan arr[i] -> add vao map
            // de theo doi
            else if (!doubleMap.containsKey(arr[i])) {
                doubleMap.put(arr[i], 1);
            }
        }
        // Neu chay het vong lap van khong tim thay -> tra ve false
        return false;
    }

    // https://leetcode.com/problems/toeplitz-matrix/submissions/984727718/
    // Bo nho: 1 deque size n + (m-1) array size n -> O(m*n)
    // Toc do: 1 vong for size n + m-1 vong for khoang size n -> O(m*n)
    public boolean isToeplitzMatrix_766(int[][] matrix) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // lay dong dau matrix vao deque
        for (int i = matrix[0].length - 1; i >= 0; i--) {
            deque.push(matrix[0][i]);
        }
        // check tung dong tiep theo
        for (int j = 1; j < matrix.length; j++) {
            // day deque sang phai 1 buoc va them so dau tien cua matrix[j]
            deque.removeLast();
            deque.addFirst(matrix[j][0]);
            // convert deque sang array va so sanh voi matrix[j], tra false neu khong equal
            int[] intArray = deque.stream().mapToInt(Integer::intValue).toArray();
            if (!Arrays.equals(matrix[j], intArray))
                return false;
        }
        return true;
    }

    // https://leetcode.com/problems/toeplitz-matrix/submissions/987070119/
    // Bo nho: Tao them bien i & j << so voi m, n -> O(1);
    // Toc do: Vong lap (m-1)*(n-1) -> O(m*n);
    public boolean isToeplitzMatrix_766_2(int[][] matrix) {
        // Tai moi phan tu [i][j] so sanh voi phan tu tiep theo tren duong cheo la [i+1][j+1]
        // Do dai vong lap chi den m-1 & n-1 dam bao duyet het cac phan tu can thiet va khong tran mang
        // Co 2 phan tu chi xuat hien 1 lan va bi bo qua: [m][0] va [0][n]
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1])
                // Neu khac nhau tra luon ket qua false
                    return false;
            }
        }
        // Neu chay het va khong gap false -> tra ve true
        return true;
    }
}
