package net.iamaprogrammer.summerstore.application;

import java.util.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class ScrollableLayer<T> extends Layer<T> {
  public ScrollPane scrollPane;

  public ScrollableLayer() {
    super();
    this.scrollPane = new ScrollPane();
    this.scrollPane.setFitToWidth(true);
    this.scrollPane.setFitToHeight(true);
    
    this.scrollPane.setContent(this.grid);
  }

  @Override
  public void addToParent(int row, int column) {
    this.parent.grid.add(this.scrollPane, row, column);
  }

  @Override
  public javafx.scene.Node getLayerRoot() {
    return this.scrollPane;
  }
}