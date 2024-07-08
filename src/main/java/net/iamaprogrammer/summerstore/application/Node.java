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

  public boolean isEnabled() {
    return this.node.isEnabled();
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
    this.switchTo(identifier, false);
  }

  public void switchTo(String identifier, boolean shouldClear) {
    if (this.parent == null) {
      return;
    }
    this.performSwap(identifier, shouldClear);
  }

  public<T> void switchWithData(String identifier, T data) {
    if (this.parent == null) {
      return;
    }
    Node nodeToSwapTo = this.performSwap(identifier, false);
    nodeToSwapTo.getNode().init(null, nodeToSwapTo, data);
    nodeToSwapTo.getNode().style();
    nodeToSwapTo.getNode().listeners();
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

  private Node performSwap(String identifier, boolean shouldClear) {
    Node nodeToSwapTo = this.parent.getChild(identifier);
    if (nodeToSwapTo == null) {
      return null;
    }

    if (shouldClear) {
      this.node.clear();
    }

    this.setEnabled(false);
    nodeToSwapTo.setEnabled(true);

    return nodeToSwapTo;
  }
}