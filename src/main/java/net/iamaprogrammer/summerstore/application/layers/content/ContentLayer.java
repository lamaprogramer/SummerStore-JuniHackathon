package net.iamaprogrammer.summerstore.application.layers.content;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;

public class ContentLayer extends ScrollableLayer {

  public ContentLayer() {
    super();
  }

  public void init() {
    // Init Grid

    int columnCount = 3;

    for (int i = 0; i < columnCount; i++) {
      RowConstraints row = new RowConstraints(400);
      //row.setPercentHeight(200);

      ColumnConstraints col = new ColumnConstraints(250);
      //col.setPercentWidth(100 / columnCount);

      grid.getRowConstraints().addAll(row);
      grid.getColumnConstraints().addAll(col);
    }

    grid.setHgap(10);
    grid.setVgap(10);

    for (int i = 0; i < columnCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: green;");

        this.grid.add(pane, i, j);
      }
    }
    
    //col1.setHalignment(HPos.CENTER);

    grid.setAlignment(Pos.CENTER); 
    this.addToParent(0, 1);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red;");
    scrollPane.setStyle("-fx-background-color: red;"); 
  }

  public void listeners() {

  }
}