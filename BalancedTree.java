import java.util.Arrays;
// Node class to store right and left nodes along with key value.
class Node{
    int key;
    Node left, right;
    Node(int val){
        key=val;
        left=right=null;
    }
}
public class BalancedTree {
    // Default constructor
    public BalancedTree(){
        Node root = null;
    }
    // Constructor to create BST given array (Sorted or Non-Sorted)
    public BalancedTree(int[] arr){
        // Initiate start and end values to not have them be constructor parameters.
        int start = 0;
        int end = arr.length-1;
        if(SortedOrNot(arr, arr.length)){
            Node root = CreateBST(arr, start, end);
            // Method to print values
            InOrder(root);
            // Method to print height to see if BST is balanced
            height(root);
        }
        else{
            // if not sorted, then sort and continue
            Arrays.sort(arr);
            Node root = CreateBST(arr, start, end);
            InOrder(root);
            System.out.println(height(root));
        }
    }
    // Prints out node values in increasing order recursively.
    void InOrder(Node root) {
        if(root == null){
            return;
        }
        InOrder(root.left);
        System.out.println(root.key);
        InOrder(root.right);
    }
    // Method to return boolean if array is sorted
    boolean SortedOrNot(int[] arr, int len) {
        if (arr == null || len < 2)
            return true;
        if (arr[len - 2] > arr[len - 1])
            return false;
        return SortedOrNot(arr, len - 1);
    }
    // Returns BST height to check if the tree is balanced.
    int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
    // Creates BST in a balanced manner automatically by
    // taking the middle value of the array and making that the root, then continuing on with the left and right
    // sides irrespective of one another, the left side of the BST doesn't mind for the right side at all.
    public Node CreateBST(int[] arr, int start, int end) {
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        root.left = CreateBST(arr, start, mid-1);
        root.right = CreateBST(arr, mid + 1, end);
        return root;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{5,9,7,1,3,2,6,4,8};
        BalancedTree BST = new BalancedTree(arr);
    }
}
