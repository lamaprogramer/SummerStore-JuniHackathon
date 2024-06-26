package net.iamaprogrammer.summerstore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

import net.iamaprogrammer.summerstore.layers.content.ContentLayer;
import net.iamaprogrammer.summerstore.layers.controls.ControlsLayer;
import net.iamaprogrammer.summerstore.layers.navigation.NavbarLayer;
import net.iamaprogrammer.summerstore.layers.BaseLayer;

import com.google.gson.*;

public class Main extends Application { 
  private static final String EBAY_API_KEY = System.getenv("EBAY_API_KEY");
  
  @Override
  public void start(Stage stage) {

    // try {
    //   URL url = new URL("https://api.nal.usda.gov/fdc/v1/food/1750339?api_key=DEMO_KEY");
    //   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //   conn.setRequestMethod("GET");
    //   conn.setRequestProperty("Accept", "application/json");
    //   if (conn.getResponseCode() != 200) {
    //     throw new RuntimeException("Failed : HTTP Error code : "
    //             + conn.getResponseCode());
    //   }
    //   InputStreamReader in = new InputStreamReader(conn.getInputStream());
    //   BufferedReader br = new BufferedReader(in);
    //   String output;
    //   while ((output = br.readLine()) != null) {
    //     System.out.println(output);
    //   }
    //   conn.disconnect();

    // } catch (Exception e) {
    //     System.out.println("Exception in NetClientGet:- " + e);
    // }

    BaseLayer baseLayer = new BaseLayer();

    // Second Layer
    NavbarLayer navbarLayer = new NavbarLayer(baseLayer);

    // Third Layer
    ControlsLayer controlsLayer = new ControlsLayer(baseLayer);

    // Fourth Layer
    ContentLayer contentLayer = new ContentLayer(controlsLayer);
    
    Scene scene = new Scene(baseLayer.grid, 960, 540); 

    scene.getStylesheets().add("stylesheets/main.css");
    
    stage.setTitle("CSS Example in JavaFX"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
