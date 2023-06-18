
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        BTVN_Buoi2 btvn_buoi2 = new BTVN_Buoi2();
        int[] myArray1= {-8,-7};
        int[] myArray2= {4,10,-4,5,2};
        int d = 55;
        BTVN_Buoi4 btvn_buoi4 = new BTVN_Buoi4();
        BTVN_Buoi5 btvn_buoi5 = new BTVN_Buoi5();
//        System.out.println(btvn_buoi4.decodeString_394("3[a2[c]]"));
//        btvn_buoi4.reverseString_344(s);
//        System.out.println(btvn_buoi4.decodeString_394_v2("3[a2[c]]"));
        System.out.println(btvn_buoi5.findTheDistanceValue_1385(myArray1,myArray2,d));
    }
}
