package de.dan.hobby.moisql.tree;

import de.dan.hobby.moisql.datatype.IDataType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Laible
 * @since 0.0.1
 *
 * Implementaion of the abstract class Node
 * @see de.dan.hobby.moisql.tree.Node
 *
 * This class represents the leaf a B+Tree and therefor will hold the data
 */
public class LeafNode extends Node {

  LeafNode next;

  List<IDataType[]> rows = new ArrayList<>();

  /**
   * returns true since this is a leaf
   * @return
   */
  @Override
  boolean isLeaf() {
    return true;
  }


  /**
   * This is a pointer to the next leaf in a B+Tree
   *
   * @return the next leaf node in a B+Tree
   */
  public LeafNode getNext() {
    return next;
  }


  /**
   * Returns the type definition of a table
   *
   * @return the type the definition
   */
  public List<IDataType[]> getRows() {
    return rows;
  }
}
