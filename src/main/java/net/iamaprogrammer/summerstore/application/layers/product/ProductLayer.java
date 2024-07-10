package net.iamaprogrammer.summerstore.application.layers.product;

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

  public ProductLayer() {
    super();
    
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(40);
    col.setHalignment(HPos.CENTER);

    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(60);

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);

    grid.getColumnConstraints().addAll(col, col2);
    grid.getRowConstraints().addAll(row);
  }

  public void init(Node node, ProductInfo data) {

    Button leaveProductInfo = new Button("Leave");
    leaveProductInfo.getStyleClass().addAll("colored-button", "with-dropshadow");

    leaveProductInfo.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        node.switchTo("products", true);
      }
    });

    grid.add(leaveProductInfo, 1, 0);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: purple;");
    scrollPane.setStyle("-fx-background-color: red;"); 
  }

  public void listeners() {

  }
}