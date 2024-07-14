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

  public<I> void initApplicationNodes(I data) {
    this.initApplicationNodes(data, false);
  }
  
  public<I> void initApplicationNodes(I data, boolean useSameData) {
    Object dataToPass = this.node.init(this, data);
    this.node.style();
    this.node.listeners();

    if (!this.hasChildren()) {
      return;
    }
    
    for (Node child : this.children) {
      if (!child.isEnabled()) {
        continue;
      }
      child.initApplicationNodes(useSameData ? data : dataToPass, useSameData);
    }
  }

  public void switchTo(Node origin, String path) {
    this.switchTo(origin, path, false);
  }

  public void switchTo(Node origin, String path, boolean shouldClear) {
    if (origin == null) {
      System.out.println("Origin is null");
      return;
    }
    this.performSwap(origin, path, shouldClear);
  }

  public<I> void passDataToParent(I data) {
    Node parent = this.getParent();
    parent.getNode().onDataPassed(parent, data);
  }

  public<I> void passDataToPeer(String identifier, I data) {
    Node peer = this.getParent().getChild(identifier);
    peer.getNode().onDataPassed(peer, data);
  }

  public<I> void passDataToChild(String identifier, I data) {
    Node child = this.getChild(identifier);
    child.getNode().onDataPassed(child, data);
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

  private Node performSwap(Node origin, String path, boolean shouldClear) {
    String[] pathArray = path.split("/");

    Node nodeToSwapTo = origin;
    for (int i = 0; i < pathArray.length; i++) {
      System.out.println(pathArray[i]);
      nodeToSwapTo = nodeToSwapTo.getChild(pathArray[i]);
    }
    System.out.println("Swapping to: " + nodeToSwapTo.getIdentifer());
    //Node nodeToSwapTo = this.parent.getChild(identifier);
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