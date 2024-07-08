package net.iamaprogrammer.summerstore.application;

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

  public void clear() {
    this.grid.getChildren().clear();
  }

  public void init(TreeBasedApplication tree, Node node, T data) {}
  public void style() {}
  public void listeners() {}
}