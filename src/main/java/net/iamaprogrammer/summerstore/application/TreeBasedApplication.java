package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.Scene; 

import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.ApplicationTree;

public class TreeBasedApplication extends Node {
  protected TreeBasedApplication(ApplicationBuilder builder) {
    super(builder.getIdentifier(), builder.getNode());
    this.parent = builder.getParent();
    this.children = builder.getChildren();

    this.initApplicationNodes(null);
  }

  public static ApplicationBuilder builder(Layer root) {
    return new ApplicationBuilder(root);
  }

  public Scene getScene(int width, int height) {
    return new Scene(this.getNode().grid, width, height);
  }

  public Node getNodeAtPath(String path) {
    String[] pathArray = path.split("/");
    Node node = this;

    for (int i = 0; i < pathArray.length; i++) {
      node = node.getChild(pathArray[i]);
    }

    return node;
  }

  public static class ApplicationBuilder extends Node {
    public ApplicationBuilder(Layer root) {
      super("", root);
    }
  
    public ApplicationBuilder addTreeNode(ApplicationTree node, int row, int column) {
      this.getChildren().add(node.withParent(this, row, column));
      return this;
    }
  
    public ApplicationBuilder addNode(String identifier, Layer node, int row, int column) {
      this.getChildren().add(new Node(identifier, node).withParent(this, row, column));
      return this;
    }

    public ApplicationBuilder addNode(String identifier, Layer node, boolean enabled, int row, int column) {
      this.getChildren().add(new Node(identifier, node, enabled).withParent(this, row, column));
      return this;
    }
  
    public TreeBasedApplication init() {
      return new TreeBasedApplication(this);
    }
  }
}