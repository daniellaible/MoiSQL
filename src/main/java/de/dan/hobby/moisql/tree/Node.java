package de.dan.hobby.moisql.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Laible
 * @since 0.0.1
 *
 * Abstract class of a Node. Implementations are:
 * @see de.dan.hobby.moisql.tree.InternalNode
 * @see de.dan.hobby.moisql.tree.LeafNode
 */
public abstract class Node {

  List<Integer> keys = new ArrayList<>();

  Node parent;

  abstract boolean isLeaf();

}
