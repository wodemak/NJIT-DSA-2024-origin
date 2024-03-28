package oy.tol.tira.books;

import java.util.Arrays;

class TreeToArrayVisitor<V> implements Visitor<V> {

   private Pair<String, V>[] elements;
   private int count = 0;

   @java.lang.SuppressWarnings({ "unchecked" })
   public TreeToArrayVisitor(int count) {
      elements = (Pair<String, V>[]) new Pair[count];
      this.count = 0;
   }

   /*
    * @Override
    * public void visit(TreeNode<K, V> node) {
    * if (node.left != null) {
    * node.left.accept(this);
    * }
    * elements[count++] = new Pair<>(node.keyValue.getKey(),
    * node.keyValue.getValue());
    * /*if (node.list != null) {
    * for (int index = 0; index < node.list.size(); index++) {
    * Pair<K,V> item = node.list.get(index);
    * elements[count++] = new Pair<K, V>(item.getKey(), item.getValue());
    * }
    * }
    */
   // if (node.right != null) {
   // node.right.accept(this);
   // }
   // }

   @Override
   public void visit(TreeNode<V> node) {
      if (node.left != null) {
         node.left.accept(this);
      }
      if (count < elements.length) {
         elements[count++] = new Pair<>(node.keyValue.getKey(), node.keyValue.getValue());
      } else {

         resizeArray();

         elements[count++] = new Pair<>(node.keyValue.getKey(), node.keyValue.getValue());
      }
      if (node.list != null) {
         for (int index = 0; index < node.list.size(); index++) {
            Pair<String, V> item = node.list.get(index);
            if (count < elements.length) {
               elements[count++] = new Pair<String, V>(item.getKey(), item.getValue());
            } else {

               resizeArray();

               elements[count++] = new Pair<String, V>(item.getKey(), item.getValue());
            }
         }
      }

      if (node.right != null) {
         node.right.accept(this);
      }
   }

   /*
    * @Override
    * public void visit(TreeNode<V> node) {
    * if (node.left != null) {
    * node.left.accept(this);
    * }
    * elements[count++] = new Pair<>(node.keyValue.getKey(),
    * node.keyValue.getValue());
    * if (node.list != null) {
    * for (int index = 0; index < node.list.size(); index++) {
    * Pair<String,V> item = node.list.get(index);
    * elements[count++] = new Pair<String, V>(item.getKey(), item.getValue());
    * }
    * }
    * if (node.right != null) {
    * node.right.accept(this);
    * }
    * }
    */
   private void resizeArray() {
      int newSize = elements.length * 2+5;
      Pair<String, V>[] newArray = Arrays.copyOf(elements, newSize);
      elements = newArray;
   }

   Pair<String, V>[] getArray() {
      return elements;
   }

}
