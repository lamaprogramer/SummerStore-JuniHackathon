package net.iamaprogrammer.summerstore.application;

import java.util.*;

import net.iamaprogrammer.summerstore.application.Layer;

public class Node {
  protected List<Node> children = new ArrayList<>();
  protected String identifier;
  protected Node parent;
  protected Layer node;

  protected Node(String identifier, Layer node) {
    this(identifier, node, true);
  }

  protected Node(String identifier, Layer node, boolean enabled) {
    this.node = node;
    this.identifier = identifier;
    this.node.setEnabled(enabled);
  }

  public void setEnabled(boolean enabled) {
    this.node.setEnabled(enabled);
  }

  public Node getChild(String identifier) {
    for (Node child : this.children) {
      if (child.getIdentifer().equals(identifier)) {
        return child;
      }
    }

    return null;
  }

  public void switchTo(String identifier) {
    if (this.parent != null) {
      Node nodeToSwapTo = this.parent.getChild(identifier);
      if (nodeToSwapTo != null) {
        this.setEnabled(false);
        nodeToSwapTo.setEnabled(true);
      }
    }
  }

  public void clearAndSwitchTo(String identifier) {
    this.node.clear();
    this.switchTo(identifier);
  }

  public String getIdentifer() {
    return this.identifier;
  }

  public Layer getNode() {
    return node;
  }

  public Node getParent() {
    return parent;
  }

  public boolean hasParent() {
    return parent != null;
  }

  public Node withParent(Node parent) {
    this.parent = parent;
    this.node.setParent(parent.getNode());
    return this;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }
}