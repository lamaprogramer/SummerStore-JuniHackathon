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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;
import net.iamaprogrammer.summerstore.api.ProductInfo;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;

public class ContentLayer extends ScrollableLayer {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  private List<ProductInfo> products = new ArrayList<>();
  private static final int COLUMN_COUNT = 3;
  private static final int ROW_COUNT = 3;

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

    this.products = EbayBrowseApi.requestItems(ebayApi, "pool", ROW_COUNT * COLUMN_COUNT, 0);
  }

  public void init() {
    for (int i = 0; i < products.size(); i++) {
      int row = i / ROW_COUNT;
      int column = i % COLUMN_COUNT;

      GridPane pane = this.setupItemDisplayPane();
      this.populateItemDisplayPane(pane, products.get(i));
      this.styleItemDisplayPane(pane);

      this.grid.add(pane, row, column);
    }

    this.grid.setAlignment(Pos.CENTER);
    this.addToParent(0, 1);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red;");
    scrollPane.setStyle("-fx-background-color: red;"); 
  }

  public void listeners() {

  }

  private GridPane setupItemDisplayPane() {
    GridPane pane = new GridPane();

    RowConstraints imageDisplay = new RowConstraints();
    imageDisplay.setPercentHeight(60);

    RowConstraints titleDisplay = new RowConstraints();
    titleDisplay.setPercentHeight(40);
    titleDisplay.setValignment(VPos.TOP);

    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    pane.getColumnConstraints().add(col);
    pane.getRowConstraints().addAll(imageDisplay, titleDisplay);

    return pane;
  }

  private void populateItemDisplayPane(GridPane pane, ProductInfo product) {
    Image image = new Image(product.getImageUrl());
    ImageView view = new ImageView(image);
    pane.add(view, 0, 0);

    Label title = new Label(product.getTitle());
    title.setWrapText(true);
    title.getStyleClass().add("item-title");
    pane.add(title, 0, 1);
  }

  private void styleItemDisplayPane(GridPane pane) {
    pane.getStyleClass().add("with-dropshadow");
    pane.setStyle("-fx-background-color: green;");
  }
}