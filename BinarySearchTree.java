import java.util.*;
public class BinarySearchTree {
    public static class Node {
        int key;
        Node left, right;
        Node(int data) {
            key = data;
            left = right = null;
        }
    }
    public static boolean arraySortedOrNot(int[] arr, int n) {
        if (n <= 1)
            return true;
        if (arr[n - 1] < arr[n - 2])
            return false;
        return arraySortedOrNot(arr, n - 1);
    }
    public static Node BST(int[] arr, int l, int r){
        if(!arraySortedOrNot(arr, arr.length)){
            Arrays.sort(arr);
        }
        else if (l > r){
            return null;
        }
        int mid = (l+r)/2;
        Node node = new Node(arr[mid]);
        node.left = BST(arr, l, mid - 1);
        node.right = BST(arr, mid + 1, r);
        return node;
    }
    public static boolean isBalanced(Node node) {
        int l;
        int r;
        if (node == null)
            return true;
        return Math.abs(height(node.left) - height(node.right)) <= 1
                && isBalanced(node.left) && isBalanced(node.right);
    }
    static int height(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    public static void InOrder(Node node){
        if (node == null) {
            return;
        }
        InOrder(node.left);
        System.out.print(node.key + " ");
        InOrder(node.right);
    }
    public static void main(String[] args) {
        int[] arr = new int[]{9,1,3,7,6,2,4,8,5,12,20,15 };
        Node root = BST(arr, 0, arr.length-1);
        Arrays.sort(arr);
        Node rootsorted = BST(arr, 0, arr.length-1);
        System.out.println("Is this tree balanced?: "+isBalanced(root));
        InOrder(root);
        System.out.println('\n');
        System.out.println("Is this tree balanced?: "+isBalanced(rootsorted));
        InOrder(rootsorted);
    }
}