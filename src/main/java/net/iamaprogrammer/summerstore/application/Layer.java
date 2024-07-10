package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.layout.GridPane;

import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;

public class Layer<T> {
  public GridPane grid;
  public Layer parent;

  protected Layer() {
    this.grid = new GridPane();
  }

  public void setParent(Layer parent) {
    this.parent = parent;
  }

  public void addToParent(int row, int column) {
    this.parent.grid.add(this.grid, row, column);
  }

  public boolean isEnabled() {
    return this.grid.isVisible() && this.grid.isManaged();
  }

  public void setEnabled(boolean enabled) {
    this.grid.setVisible(enabled);
    this.grid.setManaged(enabled);
  }

  public void clear(Node node) {
    List<javafx.scene.Node> blacklist = new ArrayList<>();
    for (Node childNode : node.children) {
      blacklist.add(childNode.node.grid);
    }
    
    this.grid.getChildren().removeIf(child -> !blacklist.contains(child));
  }

  public void init(Node node, T data) {}
  public void style() {}
  public void listeners() {}
}