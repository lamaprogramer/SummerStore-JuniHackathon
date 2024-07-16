package net.iamaprogrammer.summerstore.application.layers.navigation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;

public class NavbarLayer extends Layer<String, Void> {
  Button menuButton;
  Label brand;

  
  public NavbarLayer() {
    super();

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
  }

  public Void init(Node node, String data) {
    // Init Grid
    menuButton = new Button("Menu");
    brand = new Label("Summer Store");

    
    grid.add(menuButton, 0, 0);
    grid.add(brand, 1, 0);

    return null;
  }

  public void style() {
    menuButton.getStyleClass().addAll("colored-button", "with-dropshadow");

    brand.getStyleClass().addAll("with-dropshadow");
    brand.setStyle("-fx-font-size: 2em; -fx-font-weight: bold; -fx-text-fill: linear-gradient(to bottom right, #7EFF7E, #FFFF88);");

    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;"); 
  }

  public void listeners() {

  }
}