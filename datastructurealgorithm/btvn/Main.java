
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        BTVN_Buoi2 btvn_buoi2 = new BTVN_Buoi2();
        int[] myArray1 = {-1,2,-8,-10};
        int[] myArray2 = {22, 28, 8, 6};
        int k = 2;
        BTVN_Buoi4 btvn_buoi4 = new BTVN_Buoi4();
        BTVN_Buoi5 btvn_buoi5 = new BTVN_Buoi5();
        classBinarySearch classBinarySearch = new classBinarySearch();
        BTVN_Buoi6 btvn_buoi6 = new BTVN_Buoi6();
        int[] result = btvn_buoi6.sortArray_912(myArray1);
        for (int i = 0; i < result.length; i++) System.out.print(result[i]);
    }
}
