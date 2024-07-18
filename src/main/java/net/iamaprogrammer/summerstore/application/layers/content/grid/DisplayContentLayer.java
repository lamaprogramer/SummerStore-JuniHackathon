package net.iamaprogrammer.summerstore.application.layers.content.grid;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;
import net.iamaprogrammer.summerstore.api.ProductInfo;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;
import net.iamaprogrammer.summerstore.application.datahandlers.LayerDataHandler;
import net.iamaprogrammer.summerstore.application.datahandlers.DataType;
import net.iamaprogrammer.summerstore.util.ButtonUtil;

public class DisplayContentLayer extends ScrollableLayer<LayerDataHandler, Void> {
  private final int COLUMN_COUNT = 3;
  private final int ROW_COUNT = 3;

  public DisplayContentLayer() {
    super();

    for (int i = 0; i < ROW_COUNT; i++) {
      RowConstraints row = new RowConstraints(400);
      grid.getRowConstraints().addAll(row);
    }
    for (int i = 0; i < COLUMN_COUNT; i++) {
      ColumnConstraints col = new ColumnConstraints();
      col.setPercentWidth(100/COLUMN_COUNT);
      grid.getColumnConstraints().addAll(col);
    }

    grid.setHgap(15);
    grid.setVgap(15);
  }

  public Void init(Node node, LayerDataHandler data) {
    List<ProductInfo> products = data.get(List.class);
    
    for (int i = 0; i < products.size(); i++) {
      int row = i / ROW_COUNT;
      int column = i % COLUMN_COUNT;

      GridPane pane = this.setupItemDisplayPane();
      this.populateItemDisplayPane(node, pane, products.get(i));
      this.styleItemDisplayPane(pane);

      this.grid.add(pane, row, column);
    }

    this.grid.setAlignment(Pos.CENTER);

    return null;
  }

  public void style() {
    grid.getStyleClass().addAll("primary-color");
    scrollPane.getStyleClass().addAll("primary-color");
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

    Button viewProduct = ButtonUtil.createIconShapedButton("arrow-right-icon", "accent-color", "with-dropshadow");

    viewProduct.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        Node parent = node.getParent();
        
        parent.switchTo(parent.getParent(), "product_info");
        parent.passDataToPeer("product_info", product);
      }
    });

    pane.add(viewProduct, 0, 3);
  }

  private void styleItemDisplayPane(GridPane pane) {
    pane.getStyleClass().addAll("with-dropshadow", "secondary-color");
  }
}