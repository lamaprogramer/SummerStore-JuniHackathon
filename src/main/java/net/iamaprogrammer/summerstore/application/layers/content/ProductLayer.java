package net.iamaprogrammer.summerstore.application.layers.content;

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

public class ProductLayer extends ScrollableLayer<ProductInfo> {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  private List<ProductInfo> products = new ArrayList<>();
  private static final int COLUMN_COUNT = 3;
  private static final int ROW_COUNT = 3;

  public ProductLayer() {
    super();
    
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(25);

    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(75);

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(90);

    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(10);

    grid.getColumnConstraints().addAll(col, col2);
    grid.getRowConstraints().addAll(row, row2);
  }

  public void init(TreeBasedApplication tree, Node node, ProductInfo data) {
    System.out.println(data.getTitle());
    
    Image image = new Image(data.getImageUrl());
    ImageView view = new ImageView(image);
    grid.add(view, 0, 0);

    Button leaveProductInfo = new Button("Leave");
    leaveProductInfo.getStyleClass().addAll("colored-button", "with-dropshadow");

    leaveProductInfo.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        node.switchTo("products", true);
      }
    });

    grid.add(leaveProductInfo, 0, 1);
    
    this.addToParent(0, 1);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: purple;");
    scrollPane.setStyle("-fx-background-color: red;"); 
  }

  public void listeners() {

  }
}