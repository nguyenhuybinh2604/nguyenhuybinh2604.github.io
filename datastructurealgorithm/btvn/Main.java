
public class Main {
    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;
        BTVN_Buoi11_BinaryTree btvn_buoi11_binaryTree = new BTVN_Buoi11_BinaryTree();
        System.out.println(btvn_buoi11_binaryTree.maxDepth_104(node3));
    }
}
