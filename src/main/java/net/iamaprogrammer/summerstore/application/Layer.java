package net.iamaprogrammer.summerstore.application;

import javafx.scene.layout.GridPane;

public class Layer {
  public GridPane grid;
  public Layer parent;

  public Layer() {
    this.grid = new GridPane();
  }

  public void setParent(Layer parent) {
    this.parent = parent;
  }

  public void addToParent(int row, int column) {
    this.parent.grid.add(this.grid, row, column);
  }

  public void setEnabled(boolean enabled) {
    this.grid.setVisible(enabled);
    this.grid.setManaged(enabled);
  }

  public void init() {}
  public void style() {}
  public void listeners() {}
}