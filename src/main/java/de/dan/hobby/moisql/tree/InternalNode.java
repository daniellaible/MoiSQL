package de.dan.hobby.moisql.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Laible
 * @since 0.0.1
 *
 * Implementaion of the abstract class Node
 * @see de.dan.hobby.moisql.tree.Node
 *
 * This class respresents an inner node of a B+Tree, therfor it holds no values
 * just a list of it's children
 * **/
public class InternalNode extends Node{

  List<Node> children = new ArrayList<>();

  @Override
  boolean isLeaf() {
    return false;
  }
}
