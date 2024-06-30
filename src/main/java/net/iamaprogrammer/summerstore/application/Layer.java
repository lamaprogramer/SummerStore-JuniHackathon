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

  public void init() {}
  public void style() {}
  public void listeners() {}
}