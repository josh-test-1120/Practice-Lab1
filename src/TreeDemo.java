import java.util.Arrays;
import java.util.Stack;

class Node{
	   int value;
	   Node left, right;
	   
	   public Node(int value){
	      this.value = value;
	      left = null;
	      right = null;
	   }

	}

	class BinarySearchTree{

	   Node root;
	  
	   
	   /*
	   recursive insert method
		
	   */
	   /*
	   inserts a node into the tree
	   */
	   public void insert(int value){
	      //tree is empty
	      if(root == null){
	         root = new Node(value);
	         return;
	      }else{
	         Node current = root;
	         Node parent = null;
	         
	         while(true){
	            parent = current;
	            
	            if(value < current.value){
	               current = current.left;
	               if(current == null){
	                  parent.left = new Node(value);
	                  return;
	               }
	            }else{
	               current = current.right;
	               if(current == null){
	                  parent.right = new Node(value);
	                  return;
	               }
	            }
	           
	         }//closing while
	      
	      }//closing main if-else 
	   }
	   
	   /*
	   pre-order traversal
	   Prints the value of every node preorder
	   */
	   public void preOrderTraversal(Node root) {
		   // Initialize the variables
		   Node current = root;
		   Stack<Node> stack = new Stack<>();

		   // Loop to traverse the tree
		   while (current != null || !stack.isEmpty()) {
			   if (current == null) current = stack.pop(); // Get node from stack
			   if (current.right != null) stack.push(current.right); // add right to stack

			   // Output handling
			   if (current.value == root.value) System.out.printf("%s",current.value);
			   else System.out.printf(" -> %s",current.value);
			   current = current.left;
		   }
		   System.out.print("\n"); // Send a newline to close out the string
	   }
	   /*
	   in-order traversal
	   */
	   public void inOrderTraversal(Node root) {
		   // Initialize the variables
		   Node current = root;
		   Stack<Node> stack = new Stack<>();
		   String result = ""; // Store results in variable

		   // Loop to traverse the tree
		   while (current != null || !stack.isEmpty()) {
			   // Get current from stack (parent)
			   if (current == null) {
				   current = stack.pop();
				   // Concatenate node to reference map
				   result = pushString(current.value,result);
				   // Move right
				   current = current.right;
			   }
			   // If no left node, handle this node and then go right
			   else if (current.left == null) {
				   // Concatenate node to reference map
				   result = pushString(current.value,result);
				   // Move right
				   current = current.right;
			   }
			   // If left exists, put current on stack and go left
			   else {
				   stack.push(current);
				   current = current.left;
			   }

		   }
		   System.out.println(result);
	   }
	   /*
	   post-order traversal
	   */
	  
	   public void postOrderTraversal(Node root) {
		   // Initialize the variables
		   Node current = root;
		   Stack<Node> stack = new Stack<>();
		   String result = ""; // Store results in variable

		   // Loop to traverse the tree
		   while (current != null || !stack.isEmpty()) {
			   // Get current from stack (parent)
			   if (current == null) {
				   current = stack.pop();
				   // Check to see if right have been checked before
				   boolean exists = false;
				   if (current.right != null) {
					   exists = result.contains(String.valueOf(current.right.value));
				   }
				   // If already existing in the result path
				   if (exists) {
					   // Concatenate node to reference map
					   result = pushString(current.value,result);
					   current = null; // Set to null to speed up traversal
				   }
				   // else we save it for final processing
				   else {
					   // Push to stack again for return checks
					   stack.push(current);
					   current = current.right; // Move right
				   }
			   }
			   // Handle the current node
			   else {
				   // If leaf node
				   if (current.left == null && current.right == null) {
					   result = pushString(current.value,result);
					   current = null;
				   }
				   // else push to stack and move left
				   else {
					   stack.push(current);
					   current = current.left;
				   }
			   }
		   }
		   // Output the results
		   System.out.println(result);
	   }
	   // Handles String concatenation
	   public String pushString(int data, String result) {
		   // Push the string alone if empty
		   if (result == "") result += data;
		   // else make it like a reference map
		   else {
			   result += " -> ";
			   result += data;
		   }
		   return result;
	   }

	   /*
	   a method to find the node in the tree
	   with a specific value
	   */
	   public boolean find(Node root, int key){

	   }

	   /*
	   a method to find the node in the tree
	   with a smallest key
	   */
	   public int getMin(Node root){
		   // Initialize variables
		   Node current = root;
		   // Iterate through the tree
		   while (current.left != null) current = current.left;
		   // Return the minimum value
		   return current.value;
	   }

	   /*
	   a method to find the node in the tree
	   with a largest key
	   */
	   public int getMax(Node root){
		   // Initialize variables
		   Node current = root;
		   // Iterate through the tree
		   while (current.right != null) current = current.right;
		   // Return the maximum value
		   return current.value;
	   }

	   /*
	   this method will not compile until getMax
	   is implemented
	   */
	   public Node delete(Node root, int key){
	      
	      if(root == null){
	         return root;
	      }else if(key < root.value){
	         root.left = delete(root.left, key);
	      }else if(key > root.value){
	         root.right = delete(root.right, key);
	      }else{
	         //node has been found
	         if(root.left==null && root.right==null){
	            //case #1: leaf node
	            root = null;
	         }else if(root.right == null){
	            //case #2 : only left child
	            root = root.left;
	         }else if(root.left == null){
	            //case #2 : only right child
	            root = root.right;
	         }else{
	            //case #3 : 2 children
	            root.value = getMax(root.left);
	            root.left = delete(root.left, root.value);
	         }
	      }
	      return root;  
	   }
	}

	public class TreeDemo{
	   public static void main(String[] args){
	      BinarySearchTree t1  = new BinarySearchTree();
	      t1.insert( 24);
	      t1.insert(80);
	      t1.insert(18);
	      t1.insert(9);
	      t1.insert(90);
	      t1.insert(22);
	            
	      System.out.println("pre-order :");
		  t1.preOrderTraversal(t1.root);
		  System.out.println("in-order :");
	      t1.inOrderTraversal(t1.root);
		  System.out.println("post-order :");
		  t1.postOrderTraversal(t1.root);
		  System.out.printf("minimum value in tree: %s\n",t1.getMin(t1.root));
		  System.out.printf("maximum value in tree: %s\n",t1.getMax(t1.root));
//		  System.out.printf("does %s exist in tree: %s\n",90,t1.find(t1.root,90));
//		  System.out.printf("does %s exist in tree: %s\n",50,t1.find(t1.root,50));
	   }  
	}
