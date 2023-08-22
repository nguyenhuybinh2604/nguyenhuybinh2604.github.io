import java.util.*;

public class KiemTraCuoiKhoa {
    // https://leetcode.com/problems/flood-fill/submissions/1025760103/
    // 2 array de duyet 4 diem xung quanh diem hien tai
    static int[] deltaRow = {0, -1, 0, 1};
    static int[] deltaColummn = {-1, 0, 1, 0};

    // 733
    // Toc do: O(mxnx4)
    // Bo nho: O(mxn)
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

    // https://leetcode.com/problems/sum-of-unique-elements/submissions/1025755396/
    // 1748
    // Toc do: O(2n)
    // Bo nho: O(n)
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

    // https://leetcode.com/problems/backspace-string-compare/submissions/1025752466/
    // 844
    // Toc do: O(m+n)
    // Bo nho: O(m+n)
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

    public int nearestExit(char[][] maze, int[] entrance) {

        // dung queue cho BFS
        Queue<int[]> queue = new ArrayDeque<>();

        // khoi tao cac bien size cua maze + cua queue dung de duyet tung luot
        int m = maze.length;
        int n = maze[0].length;
        int queueSize;

        // matrix danh dau visited
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        // danh dau da visit tai entrance
        visited[entrance[0]][entrance[1]] = true;

        int[][] deltaCoordinates = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        queue.offer(entrance);

        int minLength = 0;
        int newRow, newCol;

        while (!queue.isEmpty()) {

            // lay so luong cell can duyet cho luot hien tai
            queueSize = queue.size();

            // truoc moi luot duyet -> loang ra them 1 lop -> tang so buoc len 1
            minLength++;

            // duyet het lop cell dang co san tren queue
            for (int i = 0; i < queueSize; i++) {

                // duyet den cell nao thi pop cell do ra
                int[] curr = queue.poll();

                // duyet 4 cell ke can theo chieu x-y
                for (int j = 0; j < 4; j++) {

                    // lay toa do cua 4 cell ke can
                    newRow = curr[0] + deltaCoordinates[j][0];
                    newCol = curr[1] + deltaCoordinates[j][1];

                    // neu gap cell ngoai bien thi bo qua
                    if (newRow < 0 || newCol < 0 || newRow >= m || newCol >= n) continue;

                    // neu gap cell da duyet hoac gap tuong thi bo qua
                    if (visited[newRow][newCol] || maze[newRow][newCol] == '+') continue;

                    // neu gap cell dung o bien thi tra luon ket qua, chinh la so buoc toi thieu
                    // do ap dung phuong phap loang BFS
                    if (newRow == 0 || newCol == 0 || newRow == m - 1 || newCol == n - 1) return minLength;

                    // neu la 1 cell duong di binh thuogn thi add vao queue de duyet o luot sau
                    queue.offer(new int[]{newRow, newCol});

                    // danh dau da di qua
                    visited[newRow][newCol] = true;
                }
            }
        }

        // neu da duyet het ma chua gap border -> khong tim thay border -> tra ve -1
        return -1;
    }

}
