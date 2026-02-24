package de.dan.hobby.moisql.tree;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BPTreeTest {

  @Test
  public void createBtTree() {
    BPTree tree = new BPTree(3);
  }

  @Test
  public void createBtTreeWithBadMagnitude() {
    assertThrows(IllegalArgumentException.class, () -> new BPTree(2));
  }

  @Test
  public void insertAndDeleteBPTree() {
    BPTree tree = new BPTree(3);
    tree.insert(10);
    tree.insert(100);
    tree.insert(75);
    tree.insert(85);
    tree.insert(95);
    tree.insert(105);
    tree.insert(106);
    tree.printTree();
    System.out.println();

    tree.delete(10);
    tree.delete(85);
    tree.printTree();
  }
}