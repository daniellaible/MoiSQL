package de.dan.hobby.moisql.tree;

/**
 * @author Daniel Laible
 * @since 0.0.1
 *
 * Implementaion of the abstract class Node
 * @see de.dan.hobby.moisql.tree.Node
 *
 * This class represents the leaf a B+Tree and therefor will hold the data
 */
//TODO Here will the actual data be stored
public class LeafNode extends Node {

  LeafNode next;

  @Override
  boolean isLeaf() {
    return true;
  }
}
