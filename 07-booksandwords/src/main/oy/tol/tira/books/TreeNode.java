package oy.tol.tira.books;

class TreeNode<V> {

   private int hash = -1;
   Pair<String, V> keyValue;
   TreeNode<V> left = null;
   TreeNode<V> right = null;
   static int currentAddTreeDepth = 0;
   static int longestCollisionChain = 0;
   // OPTIONAL Handling collisions with a linked list in the tree node.
   LinkedListImplementation<Pair<String,V>> list = null;
   
   // Needed for searching by key; value is not then needed.
   TreeNode(String key) throws NullPointerException {
      if (null == key)
         throw new NullPointerException("K cannot be null");
      keyValue = new Pair<>(key, null);
      this.hash = hashCode(key);
      left = null;
      right = null;
      list = null;
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
   TreeNode(String toAdd, V value) throws NullPointerException {
      if (null == toAdd)
         throw new NullPointerException("K cannot be null");
      keyValue = new Pair<>(toAdd, value);
      this.hash = hashCode(toAdd);
      left = null;
      right = null;
      list = null;
   }

   V find(String toFind, int toFindHash) {
      if (this.hash == toFindHash) {
         if (this.keyValue.getKey().equals(toFind)) {
            // same key, so we found them
            return this.keyValue.getValue();
         } else {
            // OPTIONAL different key have the same hash, keep looking from the linked list.
            if (null != list) {
               int index = list.indexOf(new Pair<String,V>(toFind, null));
               if (index >= 0) {
                  return list.get(index).getValue();
               }
            }
            // END OPTIONAL 
         }
      } else if (toFindHash < this.hash) {
         if (null != left) {
            return left.find(toFind, toFindHash);
         }
      } else if (toFindHash > this.hash) {
         if (null != right) {
            return right.find(toFind, toFindHash);
         }
      }
      return null;
   }
/*
   int insert(K key, V value, int toInsertHash) throws RuntimeException {
      if (null == key || null == value) {
         throw new NullPointerException("Keys or values cannot be null");
      }
      int added = 0;
      TreeNode.currentAddTreeDepth++;
      if (toInsertHash < this.hash) {
         if (null == left) {
            left = new TreeNode<>(key, value);
            added = 1;
         } else {
            added = left.insert(key, value, toInsertHash);
         }
      } else if (toInsertHash > this.hash) {
         if (null == right) {
            right = new TreeNode<>(key, value);
            added = 1;
         } else {
            added = right.insert(key, value, toInsertHash);
         }
      } else { // equal hashes
         if (this.keyValue.getKey().equals(key)) {
            // Key-value pair already in tree, update the value for the key.
            this.keyValue = new Pair<>(key, value);
         } else {
            // OPTIONAL different key, same hash, put in the linked list.
            if (null == list) {
               list = new LinkedListImplementation<>();
               list.add(new Pair<>(key, value));
            } else {
               Pair<K,V> newItem = new Pair<>(key, value);
               int index = list.indexOf(newItem);
               if (index < 0) {
                  list.add(newItem);
               } else {
                  list.remove(index);
                  list.add(newItem);
               }
            }
            added = 1;
            if (list.size() > TreeNode.longestCollisionChain) {
               TreeNode.longestCollisionChain = list.size();
            }
            // END OPTIONAL
         }
      }
      return added;
   }
*/
int insert(String key, V value, int toInsertHash) throws RuntimeException {
   if (null == key || null == value) {
       throw new NullPointerException("Keys or values cannot be null");
   }
   int added = 0;
   TreeNode< V> current = this;
   while (true) {
       TreeNode.currentAddTreeDepth++;
       if (toInsertHash < current.hash) {
           if (null == current.left) {
               current.left = new TreeNode<>(key, value);
               added = 1;
               break;
           } else {
               current = current.left;
           }
       } else if (toInsertHash > current.hash) {
           if (null == current.right) {
               current.right = new TreeNode<>(key, value);
               added = 1;
               break;
           } else {
               current = current.right;
           }
       } else { // equal hashes
           if (current.keyValue.getKey().equals(key)) {
               // Key-value pair already in tree, update the value for the key.
               current.keyValue = new Pair<>(key, value);
               added = 1;
               break;
           } else {
               // Handle collision by adding to linked list
               if (null == current.list) {
                   current.list = new LinkedListImplementation<>();
               }
               Pair<String, V> newItem = new Pair<>(key, value);
               int index = current.list.indexOf(newItem);
               if (index < 0) {
                   current.list.add(newItem);
                   added = 1;
                   if (current.list.size() > TreeNode.longestCollisionChain) {
                       TreeNode.longestCollisionChain = current.list.size();
                   }
               } else {
                   // Update existing value in linked list
                   current.list.get(index).setValue(value);
                   added = 1;
               }
               break;
           }
       }
   }
   return added;
}

   public void accept(Visitor<V> visitor) {
      visitor.visit(this);
   }

   @Override 
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      TreeNode<?> other = (TreeNode<?>) obj;
      if (keyValue == null) {
         if (other.keyValue != null) {
            return false;
         }
      } else if (!keyValue.equals(other.keyValue)) {
         return false;
      }
      return true;
   }
}
