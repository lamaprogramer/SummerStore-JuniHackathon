package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.Layer;

public class ApplicationNode {
  protected final List<ApplicationNode> children = new ArrayList<>();
  protected ApplicationNode parent;
  protected Layer node;

  protected ApplicationNode(Layer node) {
    this.node = node;
  }

  public Layer getNode() {
    return node;
  }

  public ApplicationNode getParent() {
    return parent;
  }

  public boolean hasParent() {
    return parent != null;
  }

  public ApplicationNode withParent(ApplicationNode parent) {
    this.parent = parent;
    this.node.setParent(parent.getNode());
    return this;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }
}