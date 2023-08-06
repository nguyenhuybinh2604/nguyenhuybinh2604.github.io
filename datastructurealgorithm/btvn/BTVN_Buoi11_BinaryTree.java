import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BTVN_Buoi11_BinaryTree {

    // https://leetcode.com/problems/search-in-a-binary-search-tree/submissions/1011854387/
    public TreeNode searchBST_700(TreeNode root, int val) {
        TreeNode tmp = root;
        // neu gap node null -> tra ve null
        if (tmp == null) return null;
        // neu tim dung value -> tra ve root (tmp)
        if (tmp.val == val) return tmp;
        // neu khong phai 1 trong 2 truong hop tren -> search tai node con left + right
        if (tmp.val > val) return searchBST_700(tmp.left, val);
        else return searchBST_700(tmp.right, val);
    }

    // https://leetcode.com/problems/delete-node-in-a-bst/submissions/1011946847/
    public TreeNode deleteNode_450(TreeNode root, int key) {
        // neu tim den node null -> khong tim thay -> return null
        if (root == null) return null;
        // neu key o nhanh ben trai -> tim phia ben trai
        if (root.val > key) {
            root.left = deleteNode_450(root.left, key);
            // neu key o nhanh ben phai -> tim ve phia ben phai
        } else if (root.val < key) {
            root.right = deleteNode_450(root.right, key);
            // neu key dung o node dang duyet
        } else {
            // neu node bi xoa khong co children ~ la 1 node leaf -> xoa luon node do (doi thanh null)
            if (root.left == null && root.right == null) return null;
                // neu node bi xoa chi co 1 node child -> thay bang chinh node child do
            else if (root.left != null && root.right == null) return root.left;
            else if (root.right != null && root.left == null) return root.right;
                // neu node bi xoa co day du 2 node children
            else {
                // thay gia tri cua node bi xoa boi findVal = max(left) || min(right), thuc hien xoa tiep node findVal
                // o case nay thuc hien voi min(right)
                TreeNode tmp = root.right;
                // di tim gia tri min(right) nam o nhanh trai
                while (tmp.left != null) tmp = tmp.left;
                // update min(right) vao gia tri node dang duyet
                root.val = tmp.val;
                // tim va xoa node min(right)
                root.right = deleteNode_450(root.right, root.val);
            }
        }
        // tra ve tham chieu root node
        return root;
    }

    // https://leetcode.com/problems/binary-tree-preorder-traversal/submissions/1005379749/
    public List<Integer> preorderTraversal_144(TreeNode root) {
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

    // https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/1005381298/
    public List<Integer> inorderTraversal_94(TreeNode root) {
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

    // https://leetcode.com/problems/binary-tree-postorder-traversal/submissions/1005378746/
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

    // https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/1012013459/
    public int maxDepth_104(TreeNode root) {
        // stack de danh dau so node tinh den root
        Deque<Integer> depthStack = new ArrayDeque<>();

        // depth ban dau
        depthStack.push(0);

        // chay de quy tim maxDepth
        return findMaxDepth(root, depthStack);
    }

    private int findMaxDepth(TreeNode root, Deque<Integer> stack) {
        if (root == null) return 0;
        // duyet den moi node != null -> dem them 1
        stack.push(1);
        // ket qua = so node tinh den root + max(max so node nhanh trai, max so node nhanh phai)
        int maxLeft = findMaxDepth(root.left, stack);
        int maxRight = findMaxDepth(root.right, stack);
        // tra ket qua dong thoi go ra 1 node
        return stack.pop() + Math.max(maxRight, maxLeft);
    }

    // https://leetcode.com/problems/path-sum/submissions/1012026192/
    public boolean hasPathSum_112(TreeNode root, int targetSum) {
        // stack de danh dau so node tinh den root
        Deque<Integer> sumStack = new ArrayDeque<>();

        // dummy sum ban dau
        sumStack.push(0);

        // chay de quy tim maxSum
        return findPathSum(root, targetSum, sumStack);
    }

    private boolean findPathSum(TreeNode root, int target, Deque<Integer> stack) {
        // tra false neu gap node null
        if (root == null) return false;

        // neu tim duoc target tai root -> tra luon true
        if (root.left == null && root.right == null && stack.peek() + root.val == target) return true;

        // neu khong thuc hien tim tiep voi root left & right
        // day vao path sum tinh den root
        stack.push(stack.peek() + root.val);

        // tim tiep voi root left & right
        boolean findLeft = findPathSum(root.left, target, stack);

        // neu match voi target thi tra ket qua luon khong can tim o node right nua
        if (findLeft) return true;
        boolean findRight = findPathSum(root.right, target, stack);

        // den duoc day nghia la node left khong tim duoc -> chi can tra kq cua node right
        if (findRight) return true;

        // neu khong tim thay gi -> tra ve false, quay nguoc stack len 1 level
        stack.pop();
        return false;
    }

    // https://leetcode.com/problems/path-sum/submissions/1012029465/
    public boolean hasPathSum_112_cach2(TreeNode root, int targetSum) {
        // khong dung stack nua, tinh tren target duoc tru dan
        if (root == null) return false;

        // neu tim duoc target tai root -> tra luon true
        if (root.left == null && root.right == null && root.val == targetSum) return true;

        // tim tiep voi root left & right
        boolean findLeft = hasPathSum_112_cach2(root.left, targetSum - root.val);

        // neu match voi target thi tra ket qua luon khong can tim o node right nua
        if (findLeft) return true;

        boolean findRight = hasPathSum_112_cach2(root.right, targetSum - root.val);

        // den duoc day nghia la node left khong tim duoc -> chi can tra kq cua node right
        if (findRight) return true;

        // chay de quy tim maxSum
        return false;
    }

    // https://leetcode.com/problems/validate-binary-search-tree/submissions/1013454813/
    public boolean isValidBST_98(TreeNode root) {
        // dung long.min & max lam floor & cap do gia tri node.val tu integer.min -> integer.max-1
        // va do logic check phai dam bao floor < node.val < cap
        // de tranh truong hop root.val dung bang integer.min
        return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean checkBST(TreeNode node, long floor, long cap) {
        if (node != null)
            // check tai root neu val nam ngoai (floor & cap) -> tra ngay ve false
            if (node.val <= floor || node.val >= cap) return false;
            else
                // neu root dap ung dkien -> check tiep node left & right
                // update floor & cap cho tung truong hop
                return (checkBST(node.left, floor, node.val) && checkBST(node.right, node.val, cap));
        // truong hop node null -> tra ve true, cai nay khong lien quan lam den logic nhung dam bao thuat toan chay bt
        // vi muc dich chinh la di tim truong hop false
        return true;
    }

    // https://leetcode.com/problems/range-sum-of-bst/submissions/1013476234/
    public int rangeSumBST_938(TreeNode root, int low, int high) {
        int sum = 0;
        // chay de quy check de sum tung node
        sum = sumRange(root, low, high, sum);
        return sum;
    }

    private int sumRange(TreeNode node, int low, int high, int sum) {
        if (node != null) {
            int sRoot, sLeft, sRight = 0;
            if (node.val >= low && node.val <= high) {
                sRoot = node.val;
            } else sRoot = 0;

            // sumLeft = sum toan bo node o nhanh left nam trong range
            sLeft = sumRange(node.left, low, high, sum);

            // tuong tu voi sumRight
            sRight = sumRange(node.right, low, high, sum);

            // tai 1 node tra ve sum sau khi check cua root, left va right
            return sRoot + sLeft + sRight;
        }
        // neu gap node null tra ve 0
        return 0;
    }

    // https://leetcode.com/problems/same-tree/submissions/1013483170/
    public boolean isSameTree_100(TreeNode p, TreeNode q) {
        return checkSameTree(p, q);
    }

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
}
