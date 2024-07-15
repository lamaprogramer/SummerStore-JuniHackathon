package net.iamaprogrammer.summerstore.application.layers.content.grid;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;
import net.iamaprogrammer.summerstore.api.ProductInfo;

import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;

public class ContentPaginationLayer extends Layer<List<ProductInfo>, Void> {
  public ContentPaginationLayer() {
    super();

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);

    ColumnConstraints deadSpace1 = new ColumnConstraints();
    deadSpace1.setPercentWidth(20);

    ColumnConstraints backwardButton = new ColumnConstraints();
    backwardButton.setPercentWidth(10);

    ColumnConstraints pages = new ColumnConstraints();
    pages.setPercentWidth(40);

    ColumnConstraints forwardButton = new ColumnConstraints();
    forwardButton.setPercentWidth(10);
    
    ColumnConstraints deadSpace2 = new ColumnConstraints();
    deadSpace2.setPercentWidth(20);

    grid.getRowConstraints().addAll(row);
    grid.getColumnConstraints().addAll(deadSpace1, backwardButton, pages, forwardButton, deadSpace2);
  }

  public Void init(Node node, List<ProductInfo> data) {
    Button forwardButton = new Button("Forward");
    Button backwardButton = new Button("Backward");

    grid.add(backwardButton, 1, 0);
    grid.add(forwardButton, 3, 0);
    
    return null;
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red");
    //scrollPane.setStyle("-fx-background-color: #FFFFF0;"); 
  }

  public void listeners() {

  }
}