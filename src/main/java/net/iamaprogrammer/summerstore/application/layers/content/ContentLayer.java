package net.iamaprogrammer.summerstore.application.layers.content;

import java.util.*;

import javafx.geometry.Pos;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.geometry.HPos;
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

public class ContentLayer extends Layer<LayerDataHandler, LayerDataHandler> {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();
  private List<ProductInfo> products = new ArrayList<>();
  private final int COLUMN_COUNT = 3;
  private final int ROW_COUNT = 3;
  private final int limit = ROW_COUNT * COLUMN_COUNT;
  private int offset = 0;
  private String query = "";

  public ContentLayer() {
    super();

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(10);

    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(90);
    grid.getRowConstraints().addAll(row, row2);

    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(85);
    col.setHalignment(HPos.CENTER);
    grid.getColumnConstraints().addAll(col);
  }

  public LayerDataHandler init(Node node, LayerDataHandler data) {
    this.clearAndRequestItems();

    this.grid.setAlignment(Pos.CENTER);
    return new LayerDataHandler(
      new DataType<>(this.products)
    );
  }

  @Override
  protected void onDataPassed(Node node, LayerDataHandler data) {
    if (data.getDataId() == 0) {
      int moveBy = data.get(Integer.class);
      
      this.offset += withinBounds(moveBy) ? this.limit*moveBy : 0;
      this.clearAndRequestItems();

      this.initChildren(node, 
        new LayerDataHandler(new DataType<>(this.products))
      );
      
    } else if (data.getDataId() == 1) {
      this.query = data.get(String.class);
      this.offset = 0;

      this.clearAndRequestItems();

      this.initChildren(node, 
        new LayerDataHandler(new DataType<>(this.products))
      );
    }
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow", "primary-color");
  }

  private void clearAndRequestItems() {
    products.clear();
    this.products = EbayBrowseApi.requestItems(ebayApi, this.query, "19617", this.limit, this.offset);
  }

  private void initChildren(Node node, LayerDataHandler data) {
    for (Node child : node.getChildren()) {
      child.clearNode();
      child.initApplicationNodes(data, true);
    }
  }
  
  private boolean withinBounds(int moveBy) {
    return !(this.offset == 0 && moveBy < 0);
  }
}