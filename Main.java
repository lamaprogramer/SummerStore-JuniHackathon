import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import layers.navigation.NavbarLayer;
import layers.BaseLayer;

public class Main extends Application 
{ 
  
  @Override
  public void start(Stage stage) {

    BaseLayer baseLayer = new BaseLayer();

    // Second Layer
    NavbarLayer navbarLayer = new NavbarLayer(baseLayer);
    
    Scene scene = new Scene(baseLayer.grid, 960, 540); 
 
    stage.setTitle("CSS Example in JavaFX"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
