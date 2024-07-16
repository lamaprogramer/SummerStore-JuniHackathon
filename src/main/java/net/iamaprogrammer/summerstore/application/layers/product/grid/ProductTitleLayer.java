package net.iamaprogrammer.summerstore.application.layers.product.grid;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
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

public class ProductTitleLayer extends Layer<ProductInfo, Void> {
  public ProductTitleLayer() {
    super();

    RowConstraints imageDisplay = new RowConstraints();
    imageDisplay.setPercentHeight(60);

    RowConstraints titleDisplay = new RowConstraints();
    titleDisplay.setPercentHeight(20);
    titleDisplay.setValignment(VPos.TOP);

    RowConstraints priceDisplay = new RowConstraints();
    priceDisplay.setPercentHeight(20);
    priceDisplay.setValignment(VPos.TOP);

    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    grid.getColumnConstraints().add(col);
    grid.getRowConstraints().addAll(imageDisplay, titleDisplay, priceDisplay);
  }

  public Void init(Node node, ProductInfo data) {
    Image image = new Image(data.getImageUrl());
    ImageView view = new ImageView(image);
    grid.add(view, 0, 0);

    Label title = new Label(data.getTitle());
    title.setWrapText(true);
    title.getStyleClass().add("item-title");
    GridPane.setMargin(title, new Insets(0,50,0,50));
    grid.add(title, 0, 1);

    Label price = new Label(data.getPrice().value + " " + data.getPrice().currency);
    price.setWrapText(true);
    price.getStyleClass().add("item-price");
    grid.add(price, 0, 2);

    return null;
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;");
  }

  public void listeners() {

  }
}