package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(5);
        assert bst.height() == 1; assert bst.size() == 1;
        assert !bst.add(5);
        bst.add(3); bst.add(1); bst.add(8); bst.add(6); bst.add(4); bst.add(7);
        bst.add(10); bst.add(11); bst.add(12); bst.add(13); bst.add(14);
        //bst.add(2); bst.add(9); bst.add(15);
        System.out.println("Height: " + Integer.toString(bst.height()));
        System.out.println("Size: " + Integer.toString(bst.size()));
        bst.printTree();
        System.out.println("Visualizing...");
        BSTVisualizer bstvis = new BSTVisualizer("Visualizer", 500, 500);
        bstvis.drawTree(bst);
        bst.rebuild();
        bst.printTree();
        bstvis.drawTree(bst);
    }

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
        if(root == null) {
            root = new BinaryNode<E>(x);
            return true;
        }
        boolean result = root.add(x);
        if(result) size++;
        return result;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
        if(root == null) return 0;
        return root.height();
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
        if(root == null) return 0;
        return root.size();
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
        if(root == null) return;
        root.printTree();
        System.out.print("\n");
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
        E[] a = (E[]) new Comparable[size()];
        toArray(root, a, 0);
        for(E e : a) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println("Done with toArray");
        root = buildTree(a, 0, size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n.left != null) index = toArray(n.left, a, index);
        a[index] = n.element;
        index++;
        if(n.right != null) index = toArray(n.right, a, index);
        return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
        int mid = (last+first)/2;
		BinaryNode<E> node = new BinaryNode<E>(a[mid]);
        if(mid-first > 0) node.left = buildTree(a, first, mid-1);
        //System.out.println(mid);
        if(last-mid > 0) node.right = buildTree(a, mid+1, last);
        return node;
	}
	


	static class BinaryNode<E extends Comparable<? super E>> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}

        private int height() {
            int n = 0;
            if(left != null) n = left.height();
            if(right != null) {
                int n2 = right.height();
                if(n2 > n) n = n2;
            }
            return n+1;
        }

        private int size() {
            int n = 1;
            if(left != null) n += left.size();
            if(right != null) n += right.size();
            return n;
        }

        private boolean add(E x) {
            if(element.compareTo(x) > 0) {
                if(left == null) {
                    left = new BinaryNode<E>(x);
                    return true;
                }
                return left.add(x);
            } else if (element.compareTo(x) < 0) {
                if(right == null) {
                    right = new BinaryNode<E>(x);
                    return true;
                }
                return right.add(x);
            }
            return false;
        }

        public void printTree() {
            if(left != null) left.printTree();
            System.out.print(element + " ");
            if(right != null) right.printTree();
        }
	}
	
}
