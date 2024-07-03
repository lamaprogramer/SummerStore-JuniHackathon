package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.ApplicationNode;

public class ApplicationTree extends ApplicationNode {
  public ApplicationTree(Layer root) {
    super(root);
  }
  
  public ApplicationTree(Layer root, boolean enabled) {
    super(root, enabled);
  }
  
  public ApplicationTree addTreeNode(ApplicationTree node) {
    this.children.add(node);
    return this;
  }

  public ApplicationTree addNode(Layer node) {
    this.children.add(new ApplicationNode(node));
    return this;
  }

  public ApplicationTree addNode(Layer node, boolean enabled) {
    this.children.add(new ApplicationNode(node, enabled));
    return this;
  }
}