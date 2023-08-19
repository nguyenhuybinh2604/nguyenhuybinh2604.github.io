import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        char[][] maze =
                {{'+','.','+','+','+','+','+'},
                        {'+','.','+','.','.','.','+'},
                        {'+','.','+','.','+','.','+'},
                        {'+','.','.','.','.','.','+'},
                        {'+','+','+','+','.','+','.'}};
        int[] entrance = {0, 1};

        KiemTraCuoiKhoa kiemTraCuoiKhoa = new KiemTraCuoiKhoa();
        System.out.println(kiemTraCuoiKhoa.nearestExit(maze, entrance));

    }
}
