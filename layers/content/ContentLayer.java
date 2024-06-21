package layers.content;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import layers.Layer;

public class ContentLayer extends Layer {

  public ContentLayer(Layer parent) {
    super(parent);

    init(parent);
    listeners();
    style();
  }

  private void init(Layer parent) {
    // Init Grid
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(100);

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(100);
    
    col1.setHalignment(HPos.CENTER);

    grid.getRowConstraints().addAll(row1);
    grid.getColumnConstraints().addAll(col1);

    grid.setAlignment(Pos.CENTER); 
    parent.grid.add(grid, 0, 1);
  }

  private void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red;"); 
  }

  private void listeners() {

  }
}