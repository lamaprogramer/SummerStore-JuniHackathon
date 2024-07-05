package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.Layer;

public class ApplicationNode {
  protected List<ApplicationNode> children = new ArrayList<>();
  protected String identifier;
  protected ApplicationNode parent;
  protected Layer node;

  protected ApplicationNode(String identifier, Layer node) {
    this(identifier, node, true);
  }

  protected ApplicationNode(String identifier, Layer node, boolean enabled) {
    this.node = node;
    this.identifier = identifier;
    this.node.setEnabled(enabled);
  }

  public void setEnabled(boolean enabled) {
    this.node.setEnabled(enabled);
  }

  public String getIdentifer() {
    return this.identifier;
  }

  public Layer getNode() {
    return node;
  }

  public ApplicationNode getParent() {
    return parent;
  }

  public ApplicationNode getChild(String identifier) {
    for (ApplicationNode child : this.children) {
      if (child.getIdentifer().equals(identifier)) {
        return child;
      }
    }

    return null;
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