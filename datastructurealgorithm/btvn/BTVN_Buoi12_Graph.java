
import java.util.*;

public class BTVN_Buoi12_Graph {

    // https://leetcode.com/problems/number-of-islands/submissions/1025474680/
    // 2 array de duyet 4 diem xung quanh diem hien tai
    static int[] deltaRow = {0, -1, 0, 1};
    static int[] deltaColummn = {-1, 0, 1, 0};

    public int numIslands_200(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;

        // array de danh dau visited hay chua , tranh vong lap vo han
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // duyet qua mang grid, neu bat gap 1 diem la dao va chua duoc duyet qua -> dung PP loang de duyet
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    BFS_200(grid, visited, i, j);
                    // sau khi loang xong toan bo dao > tang SL dao dem duoc
                    count++;
                }
            }
        return count;
    }

    private void BFS_200(char[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        // duyet 4 diem xung quanh
        for (int k = 0; k < 4; k++) {
            int iNew = i + deltaRow[k];
            int jNew = j + deltaColummn[k];

            // chi loang ra neu diem xung quanh dap ung =='1' va chua duoc duyet
            // cac toa do iNew, jNew dap ung nam trong grid
            if (iNew >= 0 && jNew >= 0 && iNew < grid.length && jNew < grid[0].length && !visited[iNew][jNew] && grid[iNew][jNew] == '1')
                BFS_200(grid, visited, iNew, jNew);
        }
    }

    // https://leetcode.com/problems/island-perimeter/submissions/1025496562/
    static int count_463;

    public int islandPerimeter_463(int[][] grid) {
        if (grid.length == 0) return 0;
        count_463 = 0;

        // array de danh dau visited hay chua , tranh vong lap vo han
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // duyet qua mang grid, neu bat gap 1 diem la dao va chua duoc duyet qua -> dung PP loang de duyet
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    BFS_463(grid, visited, i, j);
                }
            }
        return count_463;

    }

    private void BFS_463(int[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        // duyet 4 diem xung quanh
        for (int k = 0; k < 4; k++) {
            int iNew = i + deltaRow[k];
            int jNew = j + deltaColummn[k];

            // neu gap duong bien -> +1 chu vi
            if (iNew < 0 || iNew >= grid.length || jNew < 0 || jNew >= grid[0].length)
                count_463++;
                // neu gap nuoc -> +1 chu vi
            else if (grid[iNew][jNew] == 0)
                count_463++;
                // chi loang ra neu diem xung quanh dap ung ==1 va chua duoc duyet
                // cac toa do iNew, jNew dap ung nam trong grid
            else if (!visited[iNew][jNew] && grid[iNew][jNew] == 1)
                BFS_463(grid, visited, iNew, jNew);
        }
    }

    // https://leetcode.com/problems/keys-and-rooms/submissions/1025559142/
    public boolean canVisitAllRooms_841(List<List<Integer>> rooms) {
        int countRoom = 0;

        // tra ngay false neu phong 0 khong co key nao (do n>=2)
        if (rooms.get(0).size() == 0) return false;

        // theo doi phong da duoc mo chua
        boolean[] visited = new boolean[rooms.size()];

        // queue de luu chum chia khoa
        Queue<Integer> keyList = new ArrayDeque<>();

        // chia khoa vao phong dau tien
        keyList.add(0);

        while (!keyList.isEmpty()) {
            // lay index phong tren cung queue
            int currentRoom = keyList.poll();

            // mo khoa phong (ghe tham)
            visited[currentRoom] = true;

            // tang SL phong da mo khoa
            countRoom++;

            // duyet cac key tim thay trong phong do
            rooms.get(currentRoom).stream().forEach(key -> {
                // add keys chi khi chum chia khoa chua co key do & phong cua key do chua duoc mo
                if (!keyList.contains(key) && !visited[key]) keyList.add(key);
            });
        }

        return countRoom == rooms.size();
    }

    //https://leetcode.com/problems/battleships-in-a-board/submissions/1025595954/
    public int countBattleships_419(char[][] board) {
        if (board.length == 0) return 0;
        int count = 0;

        // duyet qua mang board, neu bat gap 1 diem la ship -> dung PP loang de duyet
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    // nhan chim ship co chua diem ij
                    sinkShip_419(board, i, j);
                    // sau khi nhan chim toan bo tang SL ship dem duoc
                    count++;
                }
            }
        return count;
    }

    private void sinkShip_419(char[][] board, int i, int j) {

        // bien thanh nuoc
        board[i][j] = '.';

        // tim diem tiep theo cua tau, do duyet tu top left -> bottom right nen chi co 2 huong
        // sang phai
        if (j < board[i].length - 1 && board[i][j + 1] == 'X') sinkShip_419(board, i, j + 1);

        // huong xuong
        if (i < board.length - 1 && board[i + 1][j] == 'X') sinkShip_419(board, i + 1, j);

    }

    // https://leetcode.com/problems/find-if-path-exists-in-graph/submissions/1025622872/
    public boolean validPath_1971(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;

        // theo doi cac vertex da duyet
        boolean[] visited = new boolean[n];

        // liet ke cac diem co the den cua moi vertex
        HashMap<Integer, List<Integer>> pathMap = new HashMap<>();

        // khoi tao map cho list cac diem den cua tung vertex
        for (int i = 0; i < n; i++) pathMap.put(i, new ArrayList<>());

        // moi path tao ra 02 diem den nguoc chieu
        for (int[] edge : edges) {
            pathMap.get(edge[0]).add(edge[1]);
            pathMap.get(edge[1]).add(edge[0]);
        }
        return BFS_1971(pathMap, visited, source, destination);

    }

    private boolean BFS_1971(HashMap<Integer, List<Integer>> pathMap, boolean[] visited, int source, int destination) {
        // neu duyet duoc den dich -> tra ve true
        if (source == destination) return true;

        // danh dau vertex da duyet
        visited[source] = true;
        boolean tmpAns = false;

        for (int vertex : pathMap.get(source)) {
            // tmpAns = xet xem it nhat co 1 truong hop true hay khong
            if (!visited[vertex]) tmpAns = tmpAns || BFS_1971(pathMap, visited, vertex, destination);

            // neu tim thay thi tra luon true khong phai duyet tiep
            if (tmpAns) return true;
        }

        return tmpAns;
    }

    public boolean canFinish_207(int numCourses, int[][] prerequisites) {
        // ban chat nhu bai 1971 nhung chi duyet 1 chieu
        // bat dau tu mon so 0 co the di het cac mon con lai khong
        int count = 0;

        // theo doi cac mon da duyet
        boolean[] visited = new boolean[numCourses];

        // liet ke cac diem co the den cua moi vertex
        HashMap<Integer, List<Integer>> pathMap = new HashMap<>();

        // khoi tao map cho list cac mon den duoc tu tung mon
        for (int i = 0; i < numCourses; i++) pathMap.put(i, new ArrayList<>());

        // add cac mon destination
        for (int[] prerequisite : prerequisites) {
            pathMap.get(prerequisite[1]).add(prerequisite[0]);
        }

        BFS_207(pathMap, visited, 0);

        for (int i = 0; i < numCourses; i++) if (visited[i]) count++;
        if (count == numCourses) return true;
        else return false;

    }

    private void BFS_207(HashMap<Integer, List<Integer>> pathMap, boolean[] visited, int i) {

        // danh dau mon da duyet
        visited[i] = true;

        for (int j : pathMap.get(i)) {
            // tmpAns = xet xem it nhat co 1 truong hop true hay khong
            if (!visited[j]) BFS_207(pathMap, visited, j);
        }
    }

    // 1091
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/submissions/1026645922/
    public int shortestPathBinaryMatrix(int[][] grid) {
        // lay do dai cua grid
        int n = grid.length;

        if (n == 1 && grid[0][0] == 0) return 1;

        // neu 1 trong 2 diem dau & cuoi !=0 -> tra luon -1
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;

        // matrix de theo doi cell da duoc duyet qua chua
        boolean[][] visited = new boolean[n][n];

        // queue dung cho BFS
        Queue<int[]> queue = new ArrayDeque<>();

        // matrix 08 huong di chuyen
        int[][] directions = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

        // bat dau dem do dai quang duong tu cell goc = 1
        int count = 1;
        int x, y, qSize;

        // day vao cell goc
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        // bat dau duyet BFS
        while (!queue.isEmpty()) {

            // sau moi lan loang -> cong 1 vao quang duong
            count++;

            // lay so luong node can duyet qua truoc moi luot BFS
            qSize = queue.size();

            // bat dau duyet tung luot
            for (int i = 0; i < qSize; i++) {
                int[] currentCell = queue.poll();

                for (int j = 0; j < 8; j++) {
                    x = currentCell[0] + directions[j][0];
                    y = currentCell[1] + directions[j][1];

                    // neu ra ngoai bien -> bo qua
                    if (x < 0 || x >= n || y < 0 || y >= n) continue;

                    // neu da duyet hoac cell khong di qua duoc -> bo qua
                    if (grid[x][y] == 1 || visited[x][y]) continue;

                    // neu khi duyet gap dung cell target lan dau -> tra ve count chinh la quang duong ngan nhat
                    if (x == n - 1 && y == n - 1) return count;

                    // neu chua gap target ma gap 1 cell duong di thong thuong ->
                    if (grid[x][y] == 0) {

                        // add vao queue de duyet o vong sau
                        queue.offer(new int[]{x, y});

                        // danh dau da di qua
                        visited[x][y] = true;
                    }
                }
            }
        }

        // neu duyet het ma khong tra duoc ket qua -> khong gap duoc target -> tra ve -1
        return -1;
    }

    // 797
//    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
//        List<List<Integer>> ans = new ArrayList<>();
//        List<Integer> path = new ArrayList();
//        int n = graph.length;
//        int target = n - 1;
//        boolean[] visited = new boolean[n];
//
//        visited[0] = true;
//
//        Deque<Integer> listNode = new ArrayDeque<>();
//
//        listNode.add(0);
//        path.add(0);
//
//        while (!listNode.isEmpty()) {
//
//            int currentNode = listNode.pop();
//            if (currentNode == n - 1) {
//                ans.add(new ArrayList<>(path));
//            }
//
//            int[] nextNodes = graph[currentNode];
//            for (int i = 0; i < nextNodes.length; i++) {
//
//
//            }
//
//        }
//
//    }


}
