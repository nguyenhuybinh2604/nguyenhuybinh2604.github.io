import java.sql.Array;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] myArray = {1, 1, 3, 3, 5, 6, 6, 8, 8};
        int[] rec1 = {0, 0, 1, 1};
        int[] rec2 = {2, 2, 3, 3};
        System.out.println(solution.isRectangleOverlap_836(rec1,rec2));
    }

}
