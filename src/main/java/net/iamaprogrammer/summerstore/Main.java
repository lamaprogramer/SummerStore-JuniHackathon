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
import net.iamaprogrammer.summerstore.application.layers.product.ProductLayer;
import net.iamaprogrammer.summerstore.application.layers.product.grid.ProductTitleLayer;
import net.iamaprogrammer.summerstore.application.layers.product.grid.ProductDescriptionLayer;

import net.iamaprogrammer.summerstore.application.layers.controls.ControlsLayer;
import net.iamaprogrammer.summerstore.application.layers.navigation.NavbarLayer;
import net.iamaprogrammer.summerstore.application.layers.BaseLayer;
import net.iamaprogrammer.summerstore.application.ApplicationTree;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;

import com.google.gson.*;
import com.ebay.api.client.auth.oauth2.CredentialUtil;

public class Main extends Application {
  
  @Override
  public void start(Stage stage) {
    TreeBasedApplication application = TreeBasedApplication.builder(new BaseLayer())
      .addTreeNode(new ApplicationTree("products", new ContentLayer())

      , 0, 1)
      .addTreeNode(new ApplicationTree("product_info", new ProductLayer(), false)
        .addNode("product_description", new ProductDescriptionLayer(), 1, 0)
        .addNode("product_title", new ProductTitleLayer(), 0, 0)
      , 0, 1)
      .addNode("navbar", new NavbarLayer(), 0, 0)
      .init();

    Scene scene = application.getScene(960, 540);

    scene.getStylesheets().add("stylesheets/main.css");
    
    stage.setTitle("Summer Store"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
