import java.util.ArrayList;
import java.util.Arrays;
class BalancedTree{
    // Initialize root value to be used after being updated with constructors.
    Node root;
    // This ArrayList will allow us to use the inorderTraverse method and add key values to this arraylist and access
    // the arraylist globally.
    ArrayList<Integer> values = new ArrayList<>();
    // Private class defining Node, stores values and also right and left children if necessary.
    private static class Node{
        private final int value;
        private Node right;
        private Node left;
        private Node(int key){
            value=key;
            right=left=null;
        }
    }

    // Default Constructor, sets root = null.
    public BalancedTree(){
        root = null;
    }

    // Method to return whether or not the given array is sorted.
    static boolean arraySortedOrNot(int[] arr, int n) {
        if (n == 1 || n == 0)
            return true;
        if (arr[n - 1] < arr[n - 2])
            return false;
        return arraySortedOrNot(arr, n - 1);
    }

    // Constructor given array, sorted or not.
    public BalancedTree(int[] arr){
        int start = 0;
        int end = arr.length-1;
        if(!arraySortedOrNot(arr, end)){
            // Just sorts the array if the above statement is false.
            Arrays.sort(arr);
        }
        // All arrays that reach this point are properly sorted and ready to be made into an AVL Tree
        // The creation of an AVL Tree guarantees the BST will be balanced, regardless of the values being sorted.
        root = CreateTree(arr, start, end);
        // treeTravel traverses the BST and adds to the values ArrayList recursively, allowing for inorderTravers()
        // method to be called outside in the main function and return the ArrayList as an int array solely.
        treeTravel(root);
        System.out.println("BST Balanced: "+isBalanced());
        System.out.println("BST Height is equal to: "+height());
        System.out.println("The input arrays were: " +Arrays.toString(inorderTraverse())+'\n');
    }

    public int height(){
        return heightHelper(root);
    }

    // Helper method to find total height of tree, useful for balance method.
    public int heightHelper(Node root){
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightHelper(root.left), heightHelper(root.right));
    }

    // Method to create AVL tree, a self-balancing tree.
    public Node CreateTree(int[] arr, int start, int end) {
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        root.left = CreateTree(arr, start, mid-1);
        root.right = CreateTree(arr, mid + 1, end);
        return root;
    }

    // Check for good measure if BST given is actually balanced.
    boolean isBalanced(){
        return isBalancedHelper(root);
    }

    boolean isBalancedHelper(Node node) {
        if(node == null) {
            return true;
        }
        return Math.abs(heightHelper(node.left) - heightHelper(node.right)) <= 1
                && isBalancedHelper(node.left)
                && isBalancedHelper(node.right);
    }

    private void treeTravel(Node root) {
        if (root != null) {
            treeTravel(root.left);
            values.add(root.value);
            treeTravel(root.right);
        }
    }

    public int[] inorderTraverse(){
        int[] vals = new int[values.size()];
        for (int i=0; i < vals.length; i++)
        {
            vals[i] = values.get(i);
        }
        return vals;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,3,2,4,6,7,1,5};
        int[] arr2 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
        new BalancedTree(arr);
        new BalancedTree(arr2);
    }
}
/* I am not sure what the end objectives of this assignments were given the amount of changes posted on piazza and the
corrections and all that. I know that I tried my best writing and rewriting every method and constructor to the best of
my understanding regarding the several clarifications, however confusing they may have been.
I give a constructor for a sorted array to turn it into a BST, I return the values of the array in order, the trees are
balanced and the height is correct in every instance. 
 */