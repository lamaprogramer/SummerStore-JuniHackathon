package net.iamaprogrammer.summerstore.application.layers.product.grid;

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

public class ProductTitleLayer extends Layer<ProductInfo> {
  public ProductTitleLayer() {
    super();

    RowConstraints imageDisplay = new RowConstraints();
    imageDisplay.setPercentHeight(60);

    RowConstraints titleDisplay = new RowConstraints();
    titleDisplay.setPercentHeight(30);
    titleDisplay.setValignment(VPos.TOP);

    RowConstraints priceDisplay = new RowConstraints();
    priceDisplay.setPercentHeight(10);
    priceDisplay.setValignment(VPos.CENTER);

    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    grid.getColumnConstraints().add(col);
    grid.getRowConstraints().addAll(imageDisplay, titleDisplay, priceDisplay);

    // might be clearing this class from parent, but then never adding it back
  }

  public void init(Node node, ProductInfo data) {
    Image image = new Image(data.getImageUrl());
    ImageView view = new ImageView(image);
    grid.add(view, 0, 0);

    Label title = new Label(data.getTitle());
    title.setWrapText(true);
    title.getStyleClass().add("item-title");
    grid.add(title, 0, 1);

  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red;");
  }

  public void listeners() {

  }
}