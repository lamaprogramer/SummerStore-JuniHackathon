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

public class ContentLayer extends ScrollableLayer<String> {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  private List<ProductInfo> products = new ArrayList<>();
  private final int COLUMN_COUNT = 3;
  private final int ROW_COUNT = 3;
  private final int limit = ROW_COUNT * COLUMN_COUNT;
  
  private int offset = 0;

  public ContentLayer() {
    super();

    for (int i = 0; i < ROW_COUNT; i++) {
      RowConstraints row = new RowConstraints(400);
      grid.getRowConstraints().addAll(row);
    }
    for (int i = 0; i < COLUMN_COUNT; i++) {
      ColumnConstraints col = new ColumnConstraints(250);
      grid.getColumnConstraints().addAll(col);
    }

    grid.setHgap(15);
    grid.setVgap(15);
  }

  public void init(Node node, String data) {
    products.clear();
    this.products = EbayBrowseApi.requestItems(ebayApi, "flower", limit, offset);
    
    for (int i = 0; i < products.size(); i++) {
      int row = i / ROW_COUNT;
      int column = i % COLUMN_COUNT;

      GridPane pane = this.setupItemDisplayPane();
      this.populateItemDisplayPane(node, pane, products.get(i));
      this.styleItemDisplayPane(pane);

      this.grid.add(pane, row, column);
    }

    this.grid.setAlignment(Pos.CENTER);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;");
    scrollPane.setStyle("-fx-background-color: #FFFFF0;"); 
  }

  public void listeners() {

  }

  private GridPane setupItemDisplayPane() {
    GridPane pane = new GridPane();

    RowConstraints imageDisplay = new RowConstraints();
    imageDisplay.setPercentHeight(60);

    RowConstraints titleDisplay = new RowConstraints();
    titleDisplay.setPercentHeight(20);
    titleDisplay.setValignment(VPos.TOP);

    RowConstraints priceDisplay = new RowConstraints();
    priceDisplay.setPercentHeight(10);
    priceDisplay.setValignment(VPos.CENTER);

    RowConstraints controlsDisplay = new RowConstraints();
    controlsDisplay.setPercentHeight(10);
    controlsDisplay.setValignment(VPos.CENTER);

    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    pane.getColumnConstraints().add(col);
    pane.getRowConstraints().addAll(imageDisplay, titleDisplay, priceDisplay, controlsDisplay);

    return pane;
  }

  private void populateItemDisplayPane(Node node, GridPane pane, ProductInfo product) {
    Image image = new Image(product.getImageUrl());
    ImageView view = new ImageView(image);
    pane.add(view, 0, 0);

    Label title = new Label(product.getTitle());
    title.setWrapText(true);
    title.getStyleClass().add("item-title");
    pane.add(title, 0, 1);

    Label price = new Label(product.getPrice().value + " " + product.getPrice().currency);
    price.setWrapText(true);
    price.getStyleClass().add("item-price");
    pane.add(price, 0, 2);

    Button viewProduct = new Button("View Product");
    viewProduct.getStyleClass().addAll("colored-button", "with-dropshadow");

    viewProduct.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        node.switchWithData("product_info", product);
      }
    });

    pane.add(viewProduct, 0, 3);
  }

  private void styleItemDisplayPane(GridPane pane) {
    pane.getStyleClass().add("with-dropshadow");
    pane.setStyle("-fx-background-color: #7EFF7E;");
  }
}