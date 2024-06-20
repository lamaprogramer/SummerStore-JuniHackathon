package layers;

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

public class BaseLayer extends Layer {
  
  public BaseLayer() {
    super();

    init(null);
    listeners();
    style();
  }
  
  private void init(Layer parent) {
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(10);
    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(90);

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(100);

    grid.getRowConstraints().addAll(row1, row2);
    grid.getColumnConstraints().addAll(col1);
    
    grid.setAlignment(Pos.CENTER);
  }
  
  private void style() {
    grid.setStyle("-fx-background-color: #FFFFFB;"); 
  }
  
  private void listeners() {
    
  }
}