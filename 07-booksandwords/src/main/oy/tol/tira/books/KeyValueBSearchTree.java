package oy.tol.tira.books;

public class KeyValueBSearchTree<V extends Comparable<V>> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

   

    
    public int size() {

        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something
     * about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter
     * value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function
     * is good or bad (too much collisions against data size).
     */
  
    public String getStatus() {
        if(root == null)
        {
            return "null root";
        }
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<String, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return toReturn;
    }

   /* 
    public boolean add(String key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this
        // Remember null check.
        if (null == key || value == null)
            throw new IllegalArgumentException("Person or phone number cannot be null");
        
        // If root is null, should go there.
        if(root == null)
        {
            root = new TreeNode<>(key, value);
           // TreeNode.currentAddTreeDepth++;
            count++;
            return true;
        }
        

            // update the root node. But it may have children
            // so do not just replace it with this new node but set
            // the keys and values for the already existing root.
       
       else
       {
            int tmp = root.insert(key, value, hashCode(key));
        if(tmp == 1)
                count++;
       }     
        return true;
    }
*/
/*
public boolean add(String key, V value) throws IllegalArgumentException, OutOfMemoryError {
    // TODO: Implement this
    // Remember null check.
    if (null == key || value == null)
        throw new IllegalArgumentException("Key or value cannot be null");

    int hash = hashCode(key); // Calculate hash code for the key

    // If root is null, create a new root node
    if (root == null) {
        root = new TreeNode<>(key, value);
        count++;
        return true;
    } else {
        // Otherwise, insert the new key-value pair into the tree
        int tmp = root.insert(key, value, hash);
        if (tmp == 1)
            count++;
    }
    return true;
}
*/

public boolean add(String key, V value) throws IllegalArgumentException, OutOfMemoryError {
    // TODO: Implement this
    // Remember null check.
    if (null == key || value == null)
        throw new IllegalArgumentException("Person or phone number cannot be null");
    // If root is null, should go there.
    if(root == null)
    {
        root = new TreeNode<>(key, value);
        TreeNode.currentAddTreeDepth++;
        count++;
        return true;
    }
    

        // update the root node. But it may have children
        // so do not just replace it with this new node but set
        // the keys and values for the already existing root.
    int tmp = root.insert(key, value, hashCode(key));
    if(tmp == 1)
            count++;
    return true;
}
    public int hashCode(String m) {
        int hash = 5381;
        //int mod = 994853743;
        // Implement hash function here.
        for(int i = 0; i < m.length(); ++i)
        {
            hash = ((hash * 5381) + (int)(m.charAt(i)) );
        }
        return Math.abs(hash);
    }
   
    public V find(String key) throws IllegalArgumentException {
        // TODO: Implement this. //Think about this
        if (null == key) throw new IllegalArgumentException("Person to find cannot be null");
        if(root == null)
        {
            return null;
        }
        return root.find(key, hashCode(key));
    }

   
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }
/* 
    
    public Pair<String, V>[] toSortedArray() {
        if(root == null)
        {
            return new Pair[0];
        }
        TreeToArrayVisitor<V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<String, V>[] sorted = visitor.getArray();
        
        quickSort(sorted, 0, sorted.length - 1);

        return sorted;
    }

    private void quickSort(Pair<String, V>[] array, int begin, int end) {
        if (begin < end) {
            int q = partition(array, begin, end);
            quickSort(array, begin, q);
            quickSort(array, q + 1, end);
        }
    }
    
    private int partition(Pair<String, V>[] array, int begin, int end) {
        Pair<String, V> pivot = array[begin];
        int i = begin - 1;
        int j = end + 1;
    
        while (true) {
            do {
                i++;
            } while (array[i].getValue().compareTo(pivot.getValue()) > 0);
    
            do {
                j--;
            } while (array[j].getValue().compareTo(pivot.getValue()) < 0);
    
            if (i >= j) {
                return j;
            }
    
            Pair<String, V> temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
*/
//the most correct one
/* 
public Pair<String, V>[] toSortedArray() {
    if (root == null) {
        return new Pair[0];
    }
    TreeToArrayVisitor<V> visitor = new TreeToArrayVisitor<>(count);
    root.accept(visitor);
    Pair<String, V>[] sorted = visitor.getArray();

    quickSort(sorted, 0, sorted.length - 1);

    return sorted;
}

private void quickSort(Pair<String, V>[] array, int begin, int end) {
    if (begin < end) {
        int q = partition(array, begin, end);
        quickSort(array, begin, q);
        quickSort(array, q + 1, end);
    }
}

private int partition(Pair<String, V>[] array, int begin, int end) {
    Pair<String, V> pivot = array[begin];
    int i = begin - 1;
    int j = end + 1;

    while (true) {
        do {
            i++;
        } while (i < end && (array[i] == null || array[i].getValue().compareTo(pivot.getValue()) > 0));

        do {
            j--;
        } while (j > begin && (array[j] == null || array[j].getValue().compareTo(pivot.getValue()) < 0));

        if (i >= j) {
            return j;
        }

        Pair<String, V> temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
*/

public Pair<String, V>[] toSortedArray() {
    if (root == null) {
        return new Pair[0];
    }
    TreeToArrayVisitor<V> visitor = new TreeToArrayVisitor<>(count);
    root.accept(visitor);
    Pair<String, V>[] sorted = visitor.getArray();

    quickSort(sorted, 0, sorted.length - 1);

    return sorted;
}

private void quickSort(Pair<String, V>[] array, int begin, int end) {
    if (begin >= end) {
        return;
    }

    Pair<String, V> pivot = array[begin];

    if(pivot == null || pivot.getValue()== null)
    {
        return;
    }
    int lt = begin;  
    int gt = end;    
    int i = begin + 1; 

    while (i <= gt) {
        if(array[i] == null || array[i].getValue() == null)
        {
            i++;
            continue;
        }
        int cmp = array[i].getValue().compareTo(pivot.getValue());
        if (cmp > 0) {
            swap(array, i, lt);
            i++;
            lt++;
        } else if (cmp < 0) {
            swap(array, i, gt);
            gt--;
        } else {
            i++;
        }
    }

    quickSort(array, begin, lt - 1);
    quickSort(array, gt + 1, end);
}

private void swap(Pair<String, V>[] array, int i, int j) {
    Pair<String, V> temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}


    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based
        // structures.
    }

}
