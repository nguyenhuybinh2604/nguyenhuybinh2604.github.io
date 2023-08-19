import java.util.*;

public class KiemTraCuoiKhoa {
    // 2 array de duyet 4 diem xung quanh diem hien tai
    static int[] deltaRow = {0, -1, 0, 1};
    static int[] deltaColummn = {-1, 0, 1, 0};

    // 733
    //
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        // luu lai mau goc
        int originalColor = image[sr][sc];

        // bat dau duyet tu pixel goc
        BFS_733(image, sr, sc, originalColor, color);

        // tra ket qua sau khi to mau moi
        return image;
    }

    private void BFS_733(int[][] image, int row, int col, int originalColor, int color) {

        // to mau moi cho pixel
        image[row][col] = color;

        // duyet tiep cac pixel lien ke theo 2 truc x&y
        for (int i = 0; i < 4; i++) {

            // lay toa do cua 4 pixel ke can theo x&y
            int rowNew = row + deltaRow[i];
            int colNew = col + deltaColummn[i];

            // chi loang sang neu pixel ke can nam trong image && khac mau target (color) && co cung mau voi mau goc
            // voi logic nay khong can tao them array visited mxn
            if (rowNew >= 0 && rowNew < image.length && colNew >= 0 && colNew < image[0].length && image[rowNew][colNew] != color && image[rowNew][colNew] == originalColor)
                BFS_733(image, rowNew, colNew, originalColor, color);
        }
    }

    // 1748

    public int sumOfUnique(int[] nums) {
        // luu data dang set -> lay unique number
        HashSet<Integer> countSet = new HashSet<>();

        // mang danh dau so co lap lai hay khong
        // size = 101 do nums[i] tu 1->100;
        boolean[] isRepeated = new boolean[101];

        for (int i = 0; i < nums.length; i++) {
            // neu da gap nums[i] roi -> danh dau co lap lai
            if (countSet.contains(nums[i])) isRepeated[nums[i]] = true;
            countSet.add(nums[i]);
        }

        // khoi tao bien tinh tong
        int count = 0;

        // duyet qua toan bo set
        for (Integer element : countSet) {
            // cong tung so neu khong bi lap lai
            if (!isRepeated[element]) count += element;
        }

        return count;
    }

    // 844

    public boolean backspaceCompare(String s, String t) {
        // tra ve ket qua so sanh 2 chuoi moi sau khi xoa
        return buildString(s).equals(buildString(t));
    }

    // ham de tao chuoi sau khi xoa ky tu lien truoc cac dau #
    private String buildString(String str) {
        StringBuilder newStr = new StringBuilder();

        // duyet tung character trong str
        for (char c : str.toCharArray()) {

            // add vao chuoi neu char <> #
            if (c != '#') newStr.append(c);

                // neu gap # thi check xem truoc do co ky tu nao khong
            else {
                // neu co ky tu thi xoa
                if (newStr.length() > 0) newStr.deleteCharAt(newStr.length() - 1);
            }
        }
        return newStr.toString();
    }

    // https://leetcode.com/problems/same-tree/submissions/1025750715/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSameTree(p, q);
    }

    // 100
    // Toc do: O (n)
    // Bo nho: O (2n)
    private boolean checkSameTree(TreeNode nodeA, TreeNode nodeB) {
        if (nodeA != null && nodeB != null) {
            // neu gia tri tai root khac nhau -> tra ve false
            if (nodeA.val != nodeB.val) return false;

            // neu giong nhau thi check tiep left va right
            return checkSameTree(nodeA.left, nodeB.left) && checkSameTree(nodeA.right, nodeB.right);
        }
        // neu chi 1 trong 2 null -> false
        else if (nodeA != null && nodeB == null) return false;
        else if (nodeA == null && nodeB != null) return false;
            // ca 2 cung null -> true
        else return true;
    }

    // https://leetcode.com/problems/intersection-of-two-linked-lists/submissions/1025748899/
    // 160
    // Toc do: O(m+n)
    // Bo nho: O(m+n+max(m+n))
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // tao hashset de check node co trung nhau ko
        Set<ListNode> nodeSet = new HashSet<>();

        // lay node head cua A
        ListNode tmpA = headA;

        // dien het A vao hashset
        while (tmpA != null) {
            nodeSet.add(tmpA);
            tmpA = tmpA.next;
        }

        // lam tuong tu voi B, neu gap 1 node o B da xuat hien -> tra ve node do
        ListNode tmpB = headB;
        while (tmpB != null) {
            if (nodeSet.contains(tmpB)) return tmpB;
            tmpB = tmpB.next;
        }

        // neu chay het ma k tra duoc ket qua -> null
        return null;
    }

    // 1926

    // Toc do: O(mxn x (so phep tinh tai moi cell x4 huong)) ~O(52xmxn), m,n ~100;
    // Memory: O(2x(mxn)), chu yeu dung 2 array maze va currLength
    // dat bien static de doc lap voi de quy
    static int minLength;

    public int nearestExit(char[][] maze, int[] entrance) {

        // do di tim min nen khoi tao minLength ban dau = max;
        minLength = Integer.MAX_VALUE;

        // matrix de danh dau khoang cach tu entrance den ij
        int[][] currLength = new int[maze.length][maze[0].length];

        // bat dau duyet tu entrance
        BFS_1926(maze, currLength, entrance[0], entrance[1]);

        // tra ket qua, neu van con o maxInt -> tra -1
        if (minLength == Integer.MAX_VALUE) return -1;
        else return minLength;
    }

    private void BFS_1926(char[][] maze, int[][] currLength, int row, int col) {

        // danh dau cell da duoc duyet (bien thanh tuong)
        maze[row][col] = '+';

        // duyet tiep cac cell lien ke theo 2 truc x&y
        for (int i = 0; i < 4; i++) {

            // lay toa do cua 4 cell ke can theo x&y
            int rowNew = row + deltaRow[i];
            int colNew = col + deltaColummn[i];

            // chi loang sang neu cell ke can nam trong maze && la duong di
            // voi logic nay khong can tao them array visited size mxn
            if (rowNew >= 0 && rowNew < maze.length && colNew >= 0 && colNew < maze[0].length && maze[rowNew][colNew] == '.') {

                // dat min length la gia tri hien tai + 1
                int minVal = currLength[row][col] + 1;

                // tim tiep xung quanh target cell de xem do co phai min chua
                for (int j = 0; j < 4; j++) {
                    int row2 = rowNew + deltaRow[j];
                    int col2 = colNew + deltaColummn[j];

                    // check xung quanh target cell co phai valid cell ko
                    if (row2 >= 0 && row2 < maze.length && col2 >= 0 && col2 < maze[0].length)
                        minVal = Math.min(minVal, currLength[row2][col2]+1);
                }
                currLength[rowNew][colNew] = minVal;

                // check xem co phai la cell nam o duong bien khong
                // luu y chi can check 1 dieu kien ve row/col vi logic code dam bao khong duyet den cac cell ngoai bien
                if (rowNew == 0 || rowNew == maze.length - 1 || colNew == 0 || colNew == maze[0].length - 1) {

                    // neu dung o duong bien thi update minLength
                    minLength = Math.min(minLength, currLength[rowNew][colNew]);
                }

                // goi de quy voi cell tiep theo
                BFS_1926(maze, currLength, rowNew, colNew);
            }
        }
    }
}
