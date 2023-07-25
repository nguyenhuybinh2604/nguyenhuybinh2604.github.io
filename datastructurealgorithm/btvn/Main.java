
public class Main {
    public static void main(String[] args) {
        int[] myArray = {2,2};
        String test = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String[] arr = test.split("\n");
        for (String s:arr) System.out.prinstln(s);
    }
}
