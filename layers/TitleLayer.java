package layers;

import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import layers.Layer;

public class TitleLayer extends Layer {
  private Text text1;       
  private Text text2;       
  private TextField textField1;            
  private PasswordField textField2;  
  private Button button1; 
  private Button button2;  
  
  public TitleLayer() {
    super();

    init(null);
    listeners();
    style();
  }
  
  private void init(Layer parent) {
    text1 = new Text("Email");  
    text2 = new Text("Password");
    textField1 = new TextField();
    textField2 = new PasswordField();
    button1 = new Button("Submit");
    button2 = new Button("Clear");

    grid.setMinSize(400, 200);
    grid.setPadding(new Insets(10, 10, 10, 10)); 
    grid.setVgap(5); 
    grid.setHgap(5);     
    grid.setAlignment(Pos.CENTER); 

    grid.add(text1, 0, 0); 
    grid.add(textField1, 1, 0); 
    grid.add(text2, 0, 1);       
    grid.add(textField2, 1, 1); 
    grid.add(button1, 0, 2); 
    grid.add(button2, 1, 2); 
    System.out.println("TitleLayer init");
  }
  
  private void style() {
    button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
    button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 

    text1.setStyle("-fx-font: normal bold 20px 'serif' "); 
    text2.setStyle("-fx-font: normal bold 20px 'serif' ");  
    grid.setStyle("-fx-background-color: BEIGE;"); 
  }
  
  private void listeners() {
    
  }
}