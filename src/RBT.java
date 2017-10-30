/*
 * Name: Caezar Johnlery T. Sabijon
 * BSIT3
 * CSc124
 */

import java.awt.*;

//class node
class redblackNode
{
	redblackNode leftNode, rightNode;
	int color;
	int element;
	
	//constructor
	public redblackNode(int theElement)
	{
		this(theElement, null, null);
	}
	
	public redblackNode(int theElement, redblackNode lN, redblackNode rN)
	{
		leftNode = lN;
		rightNode = rN;
		element = theElement;
		color = 1;
	}	
}

//class tree
class redblackTree
{
	private redblackNode current, root, uncle, great, newNode;
	private static redblackNode nullNode;
	
	static
	{
		nullNode = new redblackNode(0);
		nullNode.leftNode = nullNode;
		nullNode.rightNode = nullNode;
	}
	
	static final int BLACK = 1;
	static final int RED = 0;
	
	//constructor
	public redblackTree(int negInf)
	{
		newNode = new redblackNode(negInf);
		newNode.leftNode = nullNode;
		newNode.rightNode = nullNode;
	}
	
	//return if tree is empty
	public boolean isEmpty()
	{
		return newNode.rightNode == nullNode;
	}
	
	//making the tree empty
	public void makeEmpty()
	{
		newNode.rightNode = nullNode;
	}
	
	//insert item on the tree
	public void insert(int data)
	{
		current = root = uncle = newNode;
		nullNode.element = data;
		while (current.element != data)
		{
			great = uncle;
			uncle = root;
			root = current;
			
			//if data is less than the parent data, it is inserted in the left. 
			//if data is greater than the parent data, it is inserted in the right.
			current = data < current.element ? current.leftNode : current.rightNode;
			//if two child
			if(current.leftNode.color == RED && current.rightNode.color == RED)
				recolor(data);
		}
		
		//if present, insert fails
		if(current != nullNode)
			return;
		current = new redblackNode(data, nullNode, nullNode);
		//join to parent
		if(data < root.element)
			root.leftNode = current;
		else
			root.rightNode = current;
		recolor(data);
	}
	
	//recoloring 
	private void recolor(int data)
	{
		//color flip
		current.color = RED;
		current.leftNode.color = BLACK;
		current.rightNode.color = BLACK;
		
		if(root.color == RED)
		{
			uncle.color = RED;
			if(data < uncle.element != data < root.element)
				root = rotate(data, uncle);
			current = rotate(data, great);
			current.color = BLACK;
					
		}
		newNode.rightNode.color = BLACK;
	}
	
	private redblackNode rotate (int data, redblackNode root)
	{
		if(data < root.element)
			return root.leftNode = data < root.leftNode.element ? rotateLeftChild(root.leftNode) : rotateRightChild(root.leftNode);
	else
		return root.rightNode = data < root.rightNode.element ? rotateLeftChild(root.rightNode) : rotateRightChild(root.rightNode);
	}
	
	//rotate tree with left child
	private redblackNode rotateLeftChild(redblackNode leftN)
	{
		redblackNode rightN = leftN.leftNode;
		leftN.leftNode = rightN.rightNode;
		rightN.rightNode = leftN;
		return rightN;
	}
	
	//rotate tree with left child
	private redblackNode rotateRightChild(redblackNode rightN)
	{
		redblackNode leftN = rightN.rightNode;
		rightN.rightNode = leftN.leftNode;
		leftN.leftNode = rightN;
		return leftN;
	}
	
/*	
	public int countNodes()
	{
		return countNodes(newNode.rightNode);
	}
	
	private int countNodes(redblackNode right)
	{
		if(right == null)
			return 0;
		else
		{
			int left = 1;
			left += countNodes(right.leftNode);
			left += countNodes(right.rightNode);
			return 1;
		}
	}*/
/*	
	public boolean search(int value)
	{
		return search(newNode.rightNode, value);
	}
	
	private boolean search(redblackNode right, int value)
	{
		boolean found = false;
		
		while ((right != null) && !found)
		{
			int rightValue = right.element;
			if(value < rightValue)
				right = right.leftNode;
			else if(value > rightValue)
				right = right.rightNode;
			else
			{
				found = true;
				break;
			}
			found = search(right, value);
		}
		
		return found;
	}
	*/
	
	public void preOrder()
	{
		preOrder(newNode.rightNode);
	}
	
	private void preOrder(redblackNode right)
	{
		if(right != nullNode)
		{
			char color = 'B'; //black
			if(right.color == 0)
				color = 'R';
			System.out.print(right.element +""+color+" ");
			preOrder(right.leftNode);
			preOrder(right.rightNode);
		}
	}
	
	
}
public class RBT 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		redblackTree rbt = new redblackTree(Integer.MIN_VALUE);
		rbt.insert(10);
		rbt.insert(6);
		rbt.insert(4);
		rbt.insert(8);
		rbt.insert(9);
		rbt.insert(11);
		rbt.insert(15);
		
		//print tree in preOrder traversal
		System.out.println(" Red Black Tree ");
		rbt.preOrder();
		
	}

}
