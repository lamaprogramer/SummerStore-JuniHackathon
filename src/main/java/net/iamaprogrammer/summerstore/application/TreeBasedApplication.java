package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.Scene; 

import net.iamaprogrammer.summerstore.application.ApplicationNode;
import net.iamaprogrammer.summerstore.application.ApplicationTree;

public class TreeBasedApplication extends ApplicationNode {
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

  public ApplicationNode getNodeAtPath(String path) {
    String[] pathArray = path.split("/");
    ApplicationNode node = this;

    for (int i = 0; i < pathArray.length; i++) {
      node = node.getChild(pathArray[i]);
    }

    return node;
  }

  private void initTree() {
    Queue nodeQueue = new LinkedList<ApplicationNode>();
    nodeQueue.add(this);

    while (!nodeQueue.isEmpty()) {
      ApplicationNode node = (ApplicationNode) nodeQueue.poll();
      if (node.hasChildren()) {
        for (ApplicationNode child : node.children) {
          nodeQueue.add(child.withParent(node));
        }
      }

      node.getNode().init(this);
      node.getNode().style();
      node.getNode().listeners();
    }
  }

  public static class ApplicationBuilder extends ApplicationNode {
    public ApplicationBuilder(Layer root) {
      super("", root);
    }
  
    public ApplicationBuilder addTreeNode(ApplicationTree node) {
      this.children.add(node);
      return this;
    }
  
    public ApplicationBuilder addNode(String identifier, Layer node) {
      this.children.add(new ApplicationNode(identifier, node));
      return this;
    }

    public ApplicationBuilder addNode(String identifier, Layer node, boolean enabled) {
      this.children.add(new ApplicationNode(identifier, node, enabled));
      return this;
    }
  
    public TreeBasedApplication init() {
      return new TreeBasedApplication(this);
    }
  }
}