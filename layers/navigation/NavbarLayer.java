package layers.navigation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import layers.Layer;

public class NavbarLayer extends Layer {
  Button menuButton;
  Label brand;

  
  public NavbarLayer(Layer parent) {
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
    col1.setPercentWidth(10);
    col1.setHalignment(HPos.CENTER);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(30);
    col2.setHalignment(HPos.CENTER);
    ColumnConstraints col3 = new ColumnConstraints();
    col3.setPercentWidth(60);
    col2.setHalignment(HPos.CENTER);

    grid.getRowConstraints().addAll(row1);
    grid.getColumnConstraints().addAll(col1, col2, col3);
    
    grid.setAlignment(Pos.CENTER); 

    menuButton = new Button("Menu");
    brand = new Label("SummerStore");

    
    grid.add(menuButton, 0, 0);
    grid.add(brand, 1, 0);
    parent.grid.add(grid, 0, 0);
  }

  private void style() {
    brand.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: linear-gradient(to bottom right, #7EFF7E, #FFFF88); -fx-effect: dropshadow(three-pass-box, rgba(142,142,255,0.2), 10, 0, 5, 5);");

    menuButton.setStyle("-fx-background-color: #FFFF88; -fx-effect: dropshadow(three-pass-box, rgba(142,142,255,0.2), 10, 0, 5, 5);");
    
    grid.setStyle("-fx-background-color: #FFFFF0;; -fx-effect: dropshadow(three-pass-box, rgba(142,142,255,0.2), 10, 0, 0, 0);"); 
  }

  private void listeners() {

  }
}