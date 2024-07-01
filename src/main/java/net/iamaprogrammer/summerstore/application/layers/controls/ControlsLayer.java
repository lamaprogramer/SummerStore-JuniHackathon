package net.iamaprogrammer.summerstore.application.layers.controls;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import net.iamaprogrammer.summerstore.application.Layer;

public class ControlsLayer extends Layer {

  public ControlsLayer() {
    super();
  }

  public void init() {
    // Init Grid
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(10);
    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(80);
    RowConstraints row3 = new RowConstraints();
    row3.setPercentHeight(10);

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(100);
    col1.setHalignment(HPos.CENTER);

    grid.getRowConstraints().addAll(row1, row2, row3);
    grid.getColumnConstraints().addAll(col1);

    grid.setAlignment(Pos.CENTER); 
    this.addToParent(0, 1);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: blue;"); 
  }

  public void listeners() {

  }
}