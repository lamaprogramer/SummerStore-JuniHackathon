package net.iamaprogrammer.summerstore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import net.iamaprogrammer.summerstore.application.layers.content.ContentLayer;
import net.iamaprogrammer.summerstore.application.layers.controls.ControlsLayer;
import net.iamaprogrammer.summerstore.application.layers.navigation.NavbarLayer;
import net.iamaprogrammer.summerstore.application.layers.BaseLayer;
import net.iamaprogrammer.summerstore.application.ApplicationTree;
import net.iamaprogrammer.summerstore.application.ApplicationBuilder;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;

import com.google.gson.*;
import com.ebay.api.client.auth.oauth2.CredentialUtil;

public class Main extends Application { 
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  
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
    System.out.println(ebayApi.credentialsLoaded());
    Scene scene = new ApplicationBuilder(new BaseLayer())
      .addNode(new NavbarLayer())
      .addTreeNode(new ApplicationTree(new ControlsLayer())
        .addNode(new ContentLayer())
      ).init();

    scene.getStylesheets().add("stylesheets/main.css");
    
    stage.setTitle("Summer Store"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
