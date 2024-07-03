package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.Layer;

public class ApplicationNode {
  protected List<ApplicationNode> children = new ArrayList<>();
  protected ApplicationNode parent;
  protected Layer node;

  protected ApplicationNode(Layer node) {
    this.node = node;
  }

  protected ApplicationNode(Layer node, boolean enabled) {
    this.node = node;
    this.node.setEnabled(enabled);
  }

  public void setEnabled(boolean enabled) {
    this.node.setEnabled(enabled);
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