package layers;

import javafx.scene.layout.GridPane;

public class Layer {
  public GridPane grid;

  public Layer() {
    this(null, new GridPane());
  }

  public Layer(GridPane grid) {
    this(null, grid);
  }

  public Layer(Layer parent, GridPane grid) {
    this.grid = grid;
  }

  private void init(Layer parent) {}
  private void style() {}
  private void listeners() {}
}