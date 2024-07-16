/******************************************************

Project Name:   Summer Store
Author:         Iamaprogrammer (aka William)
Github Repo:    https://github.com/lamaprogramer/SummerStore-JuniHackathon


Description:
Hi! This is my project for the 2024 Juni-Hackathon. Here, I'll give a
brief overview of my project.

Summer Store is an application I created using JavaFX, GSON, and the eBay API.
The main focus of this application is to serve as a bare-bones platform for browsing flowers.
It uses JavaFX to create a GUI, the eBay Browse API to get data for different flower products,
and GSON to parse that data into useable JSON.

To somewhat organize my code, I've created a tree-like structure, in which the application 
is managed. This structure has nodes of which are made up of two parts: the "Node" class, 
and the "Layer" class.


*************Node Components*************

The "Layer" class is responsible for defining the GUI components. Every "Layer" contains a GridPane, 
which is used to control the layout of the application. 
"Layer" also contain three methods of note:

init()          : is where GUI components are defined.
style()         : optional method to style the GUI components.
onDataPassed()  : is called whenever data is passed to the "Layer".


The "Node" class is responsible for comunication between different nodes in the tree. 
Certain methods allow you to manipulate, and send data to, different nodes. 
Some notable methods are:

initApplicationNodes() : Initializes node and its children.
passDataToParent()     : Passes data to parent node.
passDataToPeer()       : Passes data to peer Nodes. (Nodes that share the same parent)
passDataToChild()      : Passes data to a child Node.


*************How the tree manages nodes.*************

Now for the tree itself. Each node is given a String identifier, that way it can be found 
when using "getChild()" or "passDataToChild()" methods. Then specify which of the parent's
grid cells the node should occupy. Finally, you can optionally specify if the node should be
enabled or not.

For a node to be enabled, it has to be both visible, and managed by JavaFX's layout manager.
when you have different menus, you likely want to switch which node is enabled, that way both
menus aren't rendered and taking up space at the same time.


*************Tree root, and node initialization.*************

The tree root, defined as a class called "TreeBasedApplication", is the 
root of the application. The root itself is a node, but it is also responsible for 
initializing the application. When the application is initialized, 
it recursively goes through the tree, and initializes any node that is "enabled".


The proccess of initializing each node goes as follows: 
First, call the "init()" and "style()" methods.

Second, store the return value of the "init" method, this will later be passed to the node's 
children.

Third, iterate through the node's children and pass the return value of "init()" to the child
as long as the child is "enabled".

Fourth, repeat steps 1-3 until all "enabled" nodes have been initialized.

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

    scene.getStylesheets().add("stylesheets/main.css");
    
    stage.setTitle("Summer Store"); 
    stage.setScene(scene);
    stage.show(); 
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
