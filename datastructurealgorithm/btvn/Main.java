
public class Main {
    public static void main(String[] args) {
        int[] myArray = {5,4,2,4};
        BTVN_Buoi10_Hashing btvnBuoi10Hashing = new BTVN_Buoi10_Hashing();
        BTVN_Buoi9 btvnBuoi9 = new BTVN_Buoi9();
//        System.out.println(btvnBuoi9.continuousSubarrays_2762(myArray));
        String testStr ="dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String[] testArr = testStr.split("\n");
        for (String str:testArr) {
            System.out.println(str + " " + str.length());
        }
        System.out.println(btvnBuoi9.lengthLongestPath_388(testStr));
    }
}
