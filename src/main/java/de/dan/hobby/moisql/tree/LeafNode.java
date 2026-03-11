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

  @Override
  boolean isLeaf() {
    return true;
  }
}
