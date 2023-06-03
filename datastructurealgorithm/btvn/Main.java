
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        BTVN_Buoi2 btvn_buoi2 = new BTVN_Buoi2();
        int[] myArray = {0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
        int[][] points = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
        System.out.println(btvn_buoi2.findMinArrowShots_452(points));
    }
}
