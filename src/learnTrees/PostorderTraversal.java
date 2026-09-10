package learnTrees;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Flow;

public class PostorderTraversal {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        PostorderTraversal postorderTraversal = new PostorderTraversal();
        System.out.println(postorderTraversal.recursiveTraversal(root));
        System.out.println(postorderTraversal.iterativeTraversalUsing2Stacks(root));
//        System.out.println(postorderTraversal.iterativeTraversalUsing1Stack(root));
    }

//    private List<Integer> iterativeTraversalUsing1Stack(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode node = root;
//        while(true){
//            if(node != null) {
//                stack.push(node);
//                node = node.left;
//            } else {
//                if(!stack.isEmpty()){ break;}
//                TreeNode temp = stack.peek().right;
//                if(temp == null){
//                    temp = stack.pop();
//                    list.add(temp.val);
//                    while(!stack.isEmpty() && temp == stack.peek().right){
//                } else {
//                    node = temp;
//                }
//                }
//            }
//        }
//    }

    private List<Integer> iterativeTraversalUsing2Stacks(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        stack1.push(root);
        while(!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            stack2.push(node);
            if(node.left != null) stack1.push(node.left);
            if(node.right != null) stack1.push(node.right);
        }
        while(!stack2.isEmpty()){
            TreeNode node = stack2.pop();
            list.add(node.val);
        }
        return list;
    }

    private List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.addAll(recursiveTraversal(root.left));
        list.addAll(recursiveTraversal(root.right));
        list.add(root.val);
        return list;
    }
}
