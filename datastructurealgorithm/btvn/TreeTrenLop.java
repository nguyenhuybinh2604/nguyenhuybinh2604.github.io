import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeTrenLop {
    //    public TreeNode insertIntoBST(TreeNode root, int val) {
//
//    }
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        TreeNode temp = root;
        while (temp.val != val) {
            if (temp.val > val) {
                if (temp.left == null) {
                    temp.left = new TreeNode(val);
                    break;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = new TreeNode(val);
                    break;
                }
                temp = temp.right;
            }
        }
        return null;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            // add value cua root sau khi da add het branches
            list.add(root.val);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }
    private void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            // add value cua root giua cac branches
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}
