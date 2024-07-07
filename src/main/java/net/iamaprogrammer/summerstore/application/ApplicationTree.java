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
  
  public ApplicationTree addTreeNode(ApplicationTree node) {
    this.children.add(node);
    return this;
  }

  public ApplicationTree addNode(String identifier, Layer node) {
    this.children.add(new Node(identifier, node));
    return this;
  }

  public ApplicationTree addNode(String identifier, Layer node, boolean enabled) {
    this.children.add(new Node(identifier, node, enabled));
    return this;
  }
}