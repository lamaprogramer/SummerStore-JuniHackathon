package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.Scene; 

import net.iamaprogrammer.summerstore.application.ApplicationNode;
import net.iamaprogrammer.summerstore.application.ApplicationTree;

public class ApplicationBuilder extends ApplicationNode {
  public ApplicationBuilder(Layer root) {
    super(root);
  }

  public ApplicationBuilder addTreeNode(ApplicationTree node) {
    this.children.add(node);
    return this;
  }

  public ApplicationBuilder addNode(Layer node) {
    this.children.add(new ApplicationNode(node));
    return this;
  }

  public Scene init() {
    Queue nodeQueue = new LinkedList<ApplicationNode>();
    nodeQueue.add(this);

    while (!nodeQueue.isEmpty()) {
      ApplicationNode node = (ApplicationNode) nodeQueue.poll();
      if (node.hasChildren()) {
        for (ApplicationNode child : node.children) {
          nodeQueue.add(child.withParent(node));
        }
      }

      node.getNode().init();
      node.getNode().style();
      node.getNode().listeners();
    }
    return new Scene(this.node.grid, 960, 540);
  }
}