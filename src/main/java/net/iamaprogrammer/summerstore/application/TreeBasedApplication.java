package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.Scene; 

import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.ApplicationTree;

public class TreeBasedApplication extends Node {
  protected TreeBasedApplication(ApplicationBuilder builder) {
    super(builder.identifier, builder.node);
    this.parent = builder.parent;
    this.children = builder.children;

    this.initTree();
  }

  public static ApplicationBuilder builder(Layer root) {
    return new ApplicationBuilder(root);
  }

  public Scene getScene(int width, int height) {
    return new Scene(this.node.grid, width, height);
  }

  public Node getNodeAtPath(String path) {
    String[] pathArray = path.split("/");
    Node node = this;

    for (int i = 0; i < pathArray.length; i++) {
      node = node.getChild(pathArray[i]);
    }

    return node;
  }

  private void initTree() {
    Queue nodeQueue = new LinkedList<Node>();
    nodeQueue.add(this);

    while (!nodeQueue.isEmpty()) {
      Node node = (Node) nodeQueue.poll();
      if (node.hasChildren()) {
        for (Node child : node.children) {
          nodeQueue.add(child.withParent(node));
        }
      }

      node.getNode().init(this, node);
      node.getNode().style();
      node.getNode().listeners();
    }
  }

  public static class ApplicationBuilder extends Node {
    public ApplicationBuilder(Layer root) {
      super("", root);
    }
  
    public ApplicationBuilder addTreeNode(ApplicationTree node) {
      this.children.add(node);
      return this;
    }
  
    public ApplicationBuilder addNode(String identifier, Layer node) {
      this.children.add(new Node(identifier, node));
      return this;
    }

    public ApplicationBuilder addNode(String identifier, Layer node, boolean enabled) {
      this.children.add(new Node(identifier, node, enabled));
      return this;
    }
  
    public TreeBasedApplication init() {
      return new TreeBasedApplication(this);
    }
  }
}