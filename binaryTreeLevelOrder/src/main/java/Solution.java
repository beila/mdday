import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> listList = levelOrderTopLeft(root);
        Collections.reverse(listList);
        return listList;
    }

    private List<List<Integer>> levelOrderTopLeft(TreeNode root) {
        List<List<TreeNode>> listListTreeNode = levelOrderTopLeftTreeNode(root);
        List<List<Integer>> listList = new ArrayList<>();
        for (List<TreeNode> listTreeNode: listListTreeNode) {
            List<Integer> list = listTreeNode.stream().map(n -> n.val).collect(Collectors.toList());
            listList.add(list);
        }
        return listList;
    }

    List<List<TreeNode>> levelOrderTopLeftTreeNode(TreeNode root) {
        List<List<TreeNode>> listList = new ArrayList<>();
        listList.add(Arrays.asList(root));
        List<TreeNode> nextList;
        do {
            nextList = new ArrayList<>();
            for (TreeNode n: listList.get(listList.size())) {
                if (n.left != null) nextList.add(n.left);
                if (n.right != null) nextList.add(n.right);
            }
            listList.add(nextList);
        } while (!nextList.isEmpty());
        return listList;
    }
}
