package learnTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {


    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        InorderTraversal inorderTraversal = new InorderTraversal();
        System.out.println(inorderTraversal.recursiveTraversal(root));
        System.out.println(inorderTraversal.iterativeTraversal(root));
    }

    private List<Integer> iterativeTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty()) break;
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    private List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        list.addAll(recursiveTraversal(root.left));
        list.add(root.val);
        list.addAll(recursiveTraversal(root.right));
        return list;
    }
}
