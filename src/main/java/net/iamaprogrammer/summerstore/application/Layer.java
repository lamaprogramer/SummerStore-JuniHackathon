package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.layout.GridPane;

import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;

public class Layer<I, O> {
  public GridPane grid;
  public Layer parent;

  protected Layer() {
    this.grid = new GridPane();
  }

  public javafx.scene.Node getLayerRoot() {
    return this.grid;
  }

  public void setParent(Layer parent) {
    this.parent = parent;
  }

  public void addToParent(int row, int column) {
    this.parent.grid.add(this.grid, row, column);
  }

  public boolean isEnabled() {
    return this.getLayerRoot().isVisible() && this.getLayerRoot().isManaged();
  }

  public void setEnabled(boolean enabled) {
    this.getLayerRoot().setVisible(enabled);
    this.getLayerRoot().setManaged(enabled);
  }

  public void clear(Node node) {
    List<javafx.scene.Node> blacklist = new ArrayList<>();
    for (Node childNode : node.children) {
      blacklist.add(childNode.node.getLayerRoot());
    }
    
    this.grid.getChildren().removeIf(child -> !blacklist.contains(child));
  }

  protected void onDataPassed(Node node, I data) {}

  public O init(Node node, I data) {
    return null;
  }
  public void style() {}
  public void listeners() {}
}