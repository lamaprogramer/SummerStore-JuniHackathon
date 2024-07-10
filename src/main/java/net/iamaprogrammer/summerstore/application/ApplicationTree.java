package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.Node;

public class ApplicationTree extends Node {
  public ApplicationTree(String identifier, Layer root) {
    super(identifier, root);
  }
  
  public ApplicationTree(String identifier, Layer root, boolean enabled) {
    super(identifier, root, enabled);
  }
  
  public ApplicationTree addTreeNode(ApplicationTree node, int row, int column) {
    this.children.add(node.withParent(this, row, column));
    return this;
  }

  public ApplicationTree addNode(String identifier, Layer node, int row, int column) {
    this.children.add(new Node(identifier, node).withParent(this, row, column));
    return this;
  }

  public ApplicationTree addNode(String identifier, Layer node, boolean enabled, int row, int column) {
    this.children.add(new Node(identifier, node, enabled).withParent(this, row, column));
    return this;
  }
}