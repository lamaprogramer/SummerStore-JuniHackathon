package net.iamaprogrammer.summerstore.application.layers.content;

import java.util.*;

import javafx.geometry.Pos;
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

public class ContentLayer extends ScrollableLayer<LayerDataHandler, LayerDataHandler> {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  private List<ProductInfo> products = new ArrayList<>();
  private final int COLUMN_COUNT = 3;
  private final int ROW_COUNT = 3;
  private final int limit = ROW_COUNT * COLUMN_COUNT;
  
  private int offset = 0;

  public ContentLayer() {
    super();

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(10);

    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(90);
    grid.getRowConstraints().addAll(row, row2);
  }

  public LayerDataHandler init(Node node, LayerDataHandler data) {
    this.clearAndRequestItems();
    
    //node.passDataToChild("product_list", this.products);

    this.grid.setAlignment(Pos.CENTER);
    return new LayerDataHandler(
      new DataType<>(this.products)
    );
  }

  @Override
  protected void onDataPassed(Node node, LayerDataHandler data) {
    System.out.println("Data passed: " + data.get(Integer.class));
    
    this.offset += limit*data.get(Integer.class);
    this.clearAndRequestItems();
    
    Node productList = node.getChild("product_list");
    productList.clearNode();
    productList.initApplicationNodes(
      new LayerDataHandler(
        new DataType<>(this.products)
    ), true);

    Node productPagination = node.getChild("product_pagination");
    productPagination.clearNode();
    productPagination.initApplicationNodes(
      new LayerDataHandler(
        new DataType<>(this.products)
    ), true);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;");
    scrollPane.setStyle("-fx-background-color: #FFFFF0;"); 
  }

  public void listeners() {

  }

  private void clearAndRequestItems() {
    products.clear();
    this.products = EbayBrowseApi.requestItems(ebayApi, "flower", limit, offset);
  }
}