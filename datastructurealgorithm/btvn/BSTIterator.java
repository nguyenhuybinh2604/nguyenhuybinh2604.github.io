import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/binary-search-tree-iterator/submissions/1012964328/
public class BSTIterator {
    Deque<TreeNode> stack;
    TreeNode node;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        node = root;
    }

    public int next() {
        // tim gia tri min tiep theo -> day den het nhanh trai cua root
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        // khi gap null thi quay lai pop node dau tien cua stack
        node = stack.pop();
        // lay gia tri cua node do = minVal
        int minVal = node.val;
        // tiep tuc duyet nhanh phai con neu co, neu khong -> gap null -> pop node root
        node = node.right;
        return minVal;
    }

    public boolean hasNext() {
        if (!stack.isEmpty() || node != null) return true;
        else return false;
    }

}
