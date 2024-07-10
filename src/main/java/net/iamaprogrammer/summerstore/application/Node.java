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

  public<T> void initApplicationNodes(T data) {
    this.node.init(this, data);
    this.node.style();
    this.node.listeners();

    if (!this.hasChildren()) {
      return;
    }
    
    for (Node child : this.children) {
      if (!child.isEnabled()) {
        continue;
      }
      
      child.initApplicationNodes(data);
    }
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
    nodeToSwapTo.initApplicationNodes(data);
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

  public Node getChild(String identifier) {
    for (Node child : this.children) {
      if (child.getIdentifer().equals(identifier)) {
        return child;
      }
    }
    return null;
  }

  public void setEnabled(boolean enabled) {
    this.node.setEnabled(enabled);
  }

  public Node withParent(Node parent, int row, int column) {
    this.parent = parent;
    this.node.setParent(parent.getNode());
    this.node.addToParent(row, column);
    return this;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public boolean hasParent() {
    return parent != null;
  }

  public boolean isEnabled() {
    return this.node.isEnabled();
  }

  public void clearNode() {
    this.node.clear(this);
  }

  private void performClear() {
    this.clearNode();

    if (!this.hasChildren()) {
      return;
    }

    for (Node child : this.children) {
      child.clearNode();
    }
  }

  private Node performSwap(String identifier, boolean shouldClear) {
    Node nodeToSwapTo = this.parent.getChild(identifier);
    if (nodeToSwapTo == null) {
      return null;
    }

    if (shouldClear) {
      this.performClear();
    }

    this.setEnabled(false);
    nodeToSwapTo.setEnabled(true);
    return nodeToSwapTo;
  }
}