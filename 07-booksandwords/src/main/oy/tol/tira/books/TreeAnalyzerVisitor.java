package oy.tol.tira.books;

class TreeAnalyzerVisitor<K extends Comparable<K>, V> implements Visitor<V> {

	int minHeight = Integer.MAX_VALUE;
	int maxHeight = Integer.MIN_VALUE;
	int currentHeight = 0;

	TreeAnalyzerVisitor() {
		// Nada
	}


	public void visit(TreeNode< V> node) {

		if (null == node.left && null == node.right) {
			minHeight = Math.min(minHeight, currentHeight);
			maxHeight = Math.max(maxHeight, currentHeight);
		} else {
			if (node.left != null) {
				currentHeight++;
				node.left.accept(this);
			}
			if (node.right != null) {
				currentHeight++;
				node.right.accept(this);
			}	
		}
		currentHeight--;
	}

}
