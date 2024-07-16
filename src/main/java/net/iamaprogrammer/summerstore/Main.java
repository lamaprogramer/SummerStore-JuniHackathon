/******************************************************

Project Name:   Summer Store
Author:         Iamaprogrammer (aka William)
Github Repo:    https://github.com/lamaprogramer/SummerStore-JuniHackathon

Hi! This is my project for the 2024 Juni-Hackathon. If you want an in-depth telling of how my 
project works,you can check it the gGithub repository above!

It can sometimes take a sec to show up, Replit isnt the fastest for this kind of stuff.

******************************************************/



package net.iamaprogrammer.summerstore;

import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.scene.Scene; 
import javafx.stage.Stage;

import net.iamaprogrammer.summerstore.application.layers.content.ContentLayer;
import net.iamaprogrammer.summerstore.application.layers.content.grid.DisplayContentLayer;
import net.iamaprogrammer.summerstore.application.layers.content.grid.ContentPaginationLayer;
import net.iamaprogrammer.summerstore.application.layers.product.ProductLayer;
import net.iamaprogrammer.summerstore.application.layers.product.grid.ProductTitleLayer;
import net.iamaprogrammer.summerstore.application.layers.product.grid.ProductDescriptionLayer;
import net.iamaprogrammer.summerstore.application.layers.navigation.NavbarLayer;
import net.iamaprogrammer.summerstore.application.layers.BaseLayer;
import net.iamaprogrammer.summerstore.application.ApplicationTree;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;

public class Main extends Application {
  
  @Override
  public void start(Stage stage) {
    TreeBasedApplication application = TreeBasedApplication.builder(new BaseLayer())
      .addTreeNode(new ApplicationTree("products", new ContentLayer())
        .addNode("product_list", new DisplayContentLayer(), 0, 1)
        .addNode("product_pagination", new ContentPaginationLayer(), 0, 0)
      , 0, 1)
      .addTreeNode(new ApplicationTree("product_info", new ProductLayer(), false)
        .addNode("product_description", new ProductDescriptionLayer(), 1, 0)
        .addNode("product_title", new ProductTitleLayer(), 0, 0)
      , 0, 1)
      .addNode("navbar", new NavbarLayer(), 0, 0)
      .init();

    Scene scene = application.getScene(960, 540);

    scene.getStylesheets().addAll("stylesheets/main.css", "stylesheets/icons.css");
    
    stage.setTitle("Summer Store"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
