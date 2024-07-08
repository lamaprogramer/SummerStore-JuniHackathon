package net.iamaprogrammer.summerstore.application;

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
  public void setEnabled(boolean enabled) {
    this.scrollPane.setVisible(enabled);
    this.scrollPane.setManaged(enabled);
  }

  @Override
  public boolean isEnabled() {
    return this.scrollPane.isVisible() && this.scrollPane.isManaged();
  }

  @Override
  public void addToParent(int row, int column) {
    this.parent.grid.add(this.scrollPane, row, column);
  }
}