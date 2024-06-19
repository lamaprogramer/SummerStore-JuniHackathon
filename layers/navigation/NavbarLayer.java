package layers.navigation;

import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import layers.Layer;

public class NavbarLayer extends Layer {
  public NavbarLayer(Layer parent) {
    super(parent);

    init(parent);
    listeners();
    style();
  }

  private void init(Layer parent) {
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(100);

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(10);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(30);
    ColumnConstraints col3 = new ColumnConstraints();
    col3.setPercentWidth(60);

    grid.getRowConstraints().addAll(row1);
    grid.getColumnConstraints().addAll(col1, col2, col3);
    
    grid.setAlignment(Pos.CENTER); 

    parent.grid.add(grid, 0, 0);
  }

  private void style() {
    grid.setStyle("-fx-background-color: GREY;"); 
  }

  private void listeners() {

  }
}