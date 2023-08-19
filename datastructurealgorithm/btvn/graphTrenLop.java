//import java.util.ArrayDeque;
//import java.util.Queue;
//
//public class graphTrenLop {
//
//    boolean[] visited = new boolean[7];
//    ArrayDeque<Integer> stack = new ArrayDeque<>();
//        stack.push(0);
//    visited[0]=true;
//
//        while(!stack.isEmpty())
//
//    {
//        int v = stack.pop();
//        System.out.println(v);
//        for (int i = 0; i < 7; i++) {
//            if (graph[v][i] == 1 && !visited[i]) {
//                visited[i] = true;
//                stack.push(i);
//            }
//        }
//    }
//
//    static void dfs(int[][] graph, int v) {
//        visited[v] = true;
//        System.out.println(v);
//        for (int i = 6; i >= 0; i--) {
//            if (graph[v][i] == 1 && !visited[i]) {
//                dfs(graph, i);
//            }
//        }
//    }
//
//    static void bfs(int[][] graph) {
//        Queue<Integer> q = new ArrayDeque<>();
//        q.add(0);
//        visited[0] = true;
//
//        while (!q.isEmpty()) {
//            int v = q.poll();
//            System.out.println(v);
//            for (int i = 0; i < 7; i++) {
//                if (graph[v][i] == 1 && !visited[i]) {
//                    q.add(i);
//                    visited[i] = true;
//                }
//            }
//        }
//    }
//
//}
