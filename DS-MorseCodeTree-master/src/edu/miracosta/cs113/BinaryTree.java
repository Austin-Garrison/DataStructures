package edu.miracosta.cs113;

import java.util.Scanner;

public class BinaryTree<T> {

    protected static class Node<T>
	{
		protected T data;
		protected Node<T> left;
		protected Node<T> right;

		public Node(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		public String toString ()
		{
			return data.toString();
		}
	}
	
	protected Node<T> root;

    public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        root = new Node<>(data);
		if (leftTree != null) 
			root.left = leftTree.root;
		else 
			root.left = null;
		
		if (rightTree != null) 
			root.right = rightTree.root;
		else
			root.right = null;
    }

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node<T> root) 
	{
		this.root = root;
	}

    public BinaryTree<T> getLeftSubtree() {
        if (root != null && root.left != null) 
			return new BinaryTree<>(root.left);
		else
			return null;
    }

    public BinaryTree<T> getRightSubtree() {
        if (root != null && root.right != null) 
			return new BinaryTree<>(root.right);
		else
			return null;
    }

    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public String toString() 
	{
		 StringBuilder sb = new StringBuilder();
		 toString(root, 1, sb);
		 return sb.toString();
	}

    private void toString(Node<T> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) 
		{
			sb.append(" ");
		}
		
		if (node == null) 
		{
			sb.append("null\n");
		} 
		else 
		{
			sb.append(node.toString());
			sb.append("\n");
			toString(node.left, depth + 1, sb);
			toString(node.right, depth + 1, sb);
		}
    }

    public static BinaryTree<String> readBinaryTree(Scanner scanner) {
        String data = scanner.nextLine().trim();
		if (data.equals("null"))
			return null;

		else 
		{
			BinaryTree<String> leftTree = readBinaryTree(scanner);
			BinaryTree<String> rightTree = readBinaryTree(scanner);
			return new BinaryTree<>(data, leftTree, rightTree);
		}
    }

    public T getData() {
        return root.data;
    }

    public void addLeft(T data) {
		root.left = new Node<T>(data);
	}
	
	public void addRight(T data) {
		root.right = new Node<T>(data);
	}

}
