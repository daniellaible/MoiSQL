package de.dan.hobby.moisql.tree;

import de.dan.hobby.moisql.datatype.IDataType;
import de.dan.hobby.moisql.datatype.numeric.Int;
import de.dan.hobby.moisql.datatype.text.VarChar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Laible
 * @since 0.0.1
 * <p>
 * Basic datastructure of a B+Tree to retrieve keys in a fast way.
 * While creating a new B+Tree you need to provide the magnitude of the tree.
 * Some refer to the magnitude also as order or degree of the tree.
 * The magnitude defines how many keys a node can hold and how many children an
 * InternalNode can have.
 */
public class BPTree {

  private static final Logger logger = LoggerFactory.getLogger(BPTree.class);

  private VarChar tableName;

  private int magnitude;

  private Node root;

  private IDataType[] dataStructure;

  private VarChar[] columnNames;

  /**
   * Initialize a new B+Tree with a certain magnitude.
   * This structure is used to build a table for a database.
   * The id of a row is the element the tree is structured upon.
   * Within a leaf of this tree the row data is saved. The row data
   * is of the type IDataType[]. IDataType is the interface all sql
   * datatype wrappers are using.
   * The minimum magnitude is 3
   *
   * @param magnitude
   * @param tableName
   */
  public BPTree(int magnitude, VarChar tableName) throws IllegalArgumentException {
    if (magnitude < 3) {
      logger.warn("Magnitude needs to be bigger 3 but was: {}", magnitude);
      throw new IllegalArgumentException("Illegal value for magnitude");
    }
    this.magnitude = magnitude;
    this.tableName = tableName;
    this.root = new LeafNode();
    logger.trace("New tree with magnitude {} was created", magnitude);
  }

  /**
   * Tables need a header row containing the datatype of the data stored in that column and
   * the columns need a name. In the dataStruct field you define the datatype and in the
   * columnsNames you define the columnma,es.
   *
   * @param dataStruct
   * @param columnNames
   * @throws IllegalArgumentException
   */
  public void specifyDataStructure(@NotNull IDataType[] dataStruct, @NotNull VarChar[] columnNames)
      throws IllegalArgumentException {
    if (dataStruct.length != columnNames.length) {
      throw new IllegalArgumentException("Data size mismatch");
    }
    this.dataStructure = dataStruct;
    this.columnNames = columnNames;
  }

  public IDataType[] getDataStructure() {
    return dataStructure;
  }

  public VarChar[] getColumnNames() {
    return columnNames;
  }

  public String getTableName() {
    return tableName.getValue();
  }

  /**
   * Inserts a row to the datastructure using the id as key
   *
   * @param row
   */
  public void insertRow(IDataType[] row) {
    var tempKey = (Int) row[0];
    int key = tempKey.getValue();
    LeafNode leaf = findLeafToInsert(root, key);
    insertSorted(leaf, row, key);

    if (leaf.keys.size() > maxKeys()) {
      splitLeaf(leaf);
    }
  }

  /**
   * Deletes a row from the datastructure
   *
   * @param key
   */
  public void delete(int key) {
    if (root == null) {
      return;
    }

    LeafNode leaf = findLeafToInsert(root, key);
    int idx = Collections.binarySearch(leaf.keys, key);

    if (idx < 0) {
      return;
    }

    leaf.keys.remove(idx);

    if (leaf == root) {
      if (leaf.keys.isEmpty()) {
        root = new LeafNode();
      }
      return;
    }

    if (leaf.keys.size() < minKeys()) {
      fixUnderflow(leaf);
    } else {
      updateParentKeyAfterDeletion(leaf);
    }
  }

  //TODO This needs to be tested
  /**
   *
   * @throws UnsupportedOperationException
   * @param key
   * @return row of the table, if no element is found null is returned
   */
  public IDataType[] findRow(int key) {
    throw new UnsupportedOperationException("Needs to be tested first");
/*    nodeRunner(root, key);
      return null;*/
  }

  //TODO This needs to be tested
  private IDataType[] nodeRunner(Node node, int key) {
    //This won't word - we need the range and not if the list contains the element
    if (node.keys.contains(key) && node.isLeaf()) {
      LeafNode leaf = (LeafNode) node;
      for (IDataType[] row : leaf.rows) {
        Int temp = (Int) row[0];
        int currentId = temp.getValue();
        if (currentId == key) {
          return row;
        }
      }

    } else if (!node.isLeaf()) {
      int i = 0;
      while (i < node.keys.size() && node.keys.get(i) < key) {
        i++;
      }
      InternalNode intern = (InternalNode) node;
      Node newNode = intern.children.get(i);
      return nodeRunner(newNode, key);
    }
    return null;
  }


  private void fixUnderflow(Node node) {
    if (node.parent == null) {
      return;
    }

    InternalNode parent = (InternalNode) node.parent;
    int index = parent.children.indexOf(node);

    Node leftSibling = (index > 0)
        ? parent.children.get(index - 1)
        : null;

    Node rightSibling = (index < parent.children.size() - 1)
        ? parent.children.get(index + 1)
        : null;

    if (leftSibling != null && leftSibling.keys.size() > minKeys()) {
      redistributeFromLeft(node, leftSibling, parent, index - 1);
      return;
    }

    if (rightSibling != null && rightSibling.keys.size() > minKeys()) {
      redistributeFromRight(node, rightSibling, parent, index);
      return;
    }

    if (leftSibling != null) {
      mergeNodes(leftSibling, node, parent, index - 1);
    } else {
      mergeNodes(node, rightSibling, parent, index);
    }
  }


  private void mergeNodes(Node left, Node right,
      InternalNode parent, int sepIndex) {

    if (left.isLeaf()) {
      LeafNode newLeft = (LeafNode) left;
      LeafNode newRight = (LeafNode) right;

      newLeft.keys.addAll(newRight.keys);
      newLeft.next = newRight.next;
    } else {
      InternalNode newLeft = (InternalNode) left;
      InternalNode newRight = (InternalNode) right;

      newLeft.keys.add(parent.keys.get(sepIndex));
      newLeft.keys.addAll(newRight.keys);

      for (Node child : newRight.children) {
        newLeft.children.add(child);
        child.parent = newLeft;
      }
    }

    parent.children.remove(right);
    parent.keys.remove(sepIndex);

    if (parent == root && parent.keys.isEmpty()) {
      root = parent.children.get(0);
      root.parent = null;
      return;
    }

    if (parent != root && parent.keys.size() < minKeys()) {
      fixUnderflow(parent);
    }
  }


  private void redistributeFromRight(@NotNull Node node, @NotNull Node rightSibling,
      @NotNull InternalNode parent, int sepIndex) {

    if (node.isLeaf()) {
      LeafNode leaf = (LeafNode) node;
      LeafNode right = (LeafNode) rightSibling;

      leaf.keys.add(right.keys.remove(0));
      leaf.keys.add(right.keys.remove(0));

      parent.keys.set(sepIndex, right.keys.get(0));
    } else {
      InternalNode in = (InternalNode) node;
      InternalNode right = (InternalNode) rightSibling;

      in.keys.add(parent.keys.get(sepIndex));
      parent.keys.set(sepIndex, right.keys.remove(0));

      Node child = right.children.remove(0);
      in.children.add(child);
      child.parent = in;
    }
  }


  private void redistributeFromLeft(@NotNull Node node, @NotNull Node leftSibling,
      InternalNode parent, int sepIndex) {

    if (node.isLeaf()) {
      LeafNode leaf = (LeafNode) node;
      LeafNode left = (LeafNode) leftSibling;

      leaf.keys.add(0, left.keys.remove(left.keys.size() - 1));

      parent.keys.set(sepIndex, leaf.keys.get(0));
    } else {
      InternalNode in = (InternalNode) node;
      InternalNode left = (InternalNode) leftSibling;

      in.keys.add(0, parent.keys.get(sepIndex));
      parent.keys.set(sepIndex, left.keys.remove(left.keys.size() - 1));

      Node child = left.children.remove(left.children.size() - 1);
      in.children.add(0, child);
      child.parent = in;
    }
  }


  private void updateParentKeyAfterDeletion(@NotNull Node node) {
    if (node.parent == null) {
      return;
    }

    InternalNode parent = (InternalNode) node.parent;
    int index = parent.children.indexOf(node);

    if (index > 0) {
      parent.keys.set(index - 1, node.keys.get(0));
    }
  }


  private void splitLeaf(@NotNull LeafNode leaf) {
    int mid = (leaf.keys.size() + 1) / 2;
    LeafNode newLeaf = new LeafNode();
    newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
    leaf.keys = new ArrayList<>(leaf.keys.subList(0, mid));
    newLeaf.next = leaf.next;
    leaf.next = newLeaf;

    insertIntoParent(leaf, newLeaf.keys.getFirst(), newLeaf);
  }


  private void insertIntoParent(Node left, @NotNull Integer key, Node right) {
    if (left.parent == null) {
      InternalNode newRoot = new InternalNode();
      newRoot.keys.add(key);
      newRoot.children.add(left);
      newRoot.children.add(right);

      left.parent = newRoot;
      right.parent = newRoot;
      root = newRoot;
      return;
    }
    InternalNode parent = (InternalNode) left.parent;
    int index = parent.children.indexOf(left);
    parent.keys.add(index, key);
    parent.children.add(index + 1, right);
    right.parent = parent;

    if (parent.keys.size() > maxKeys()) {
      splitInternal(parent);
    }
  }


  private void splitInternal(@NotNull InternalNode node) {
    int mid = node.keys.size() / 2;
    int upKey = node.keys.get(mid);

    InternalNode newNode = new InternalNode();
    newNode.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));
    node.keys = new ArrayList<>(node.keys.subList(0, mid));

    newNode.children.addAll(node.children.subList(mid + 1, node.children.size()));
    node.children = new ArrayList<>(node.children.subList(0, mid + 1));

    for (Node child : newNode.children) {
      child.parent = newNode;
    }
    insertIntoParent(node, upKey, newNode);
  }


  private void insertSorted(@NotNull LeafNode leaf, @NotNull IDataType[] row, @NotNull int key) {
    List<Integer> keys = leaf.keys;
    int i = 0;
    while (i < keys.size() && keys.get(i) < key) {
      i++;
    }
    leaf.rows.add(i, row);
    keys.add(i, key);
  }


  private LeafNode findLeafToInsert(@NotNull Node node, long key) {
    while (!node.isLeaf()) {
      InternalNode in = (InternalNode) node;
      int i = 0;
      while (i < in.keys.size() && key >= in.keys.get(i)) {
        i++;
      }
      node = in.children.get(i);
    }

    return (LeafNode) node;
  }


  private int maxKeys() {
    return magnitude - 1;
  }


  private int minKeys() {
    return (int) Math.ceil((magnitude - 1) / 2f);
  }


  //TODO needs a real printMethod() or an override toString()
  public void printTree() {
    printNode(root, 0);
  }


  private void printNode(Node node, int level) {
    System.out.println("Level " + level + ": " + node.keys);
    if (!(node instanceof LeafNode)) {
      for (Node child : ((InternalNode) node).children) {
        printNode(child, level + 1);
      }
    } else {
      LeafNode tempNode = (LeafNode) node;
      for (IDataType[] row : tempNode.rows) {
        System.out.print("[");
        for (IDataType rowElem : row) {
          System.out.print(rowElem + " ");
        }
        System.out.print("]");
      }
    }
  }
}
