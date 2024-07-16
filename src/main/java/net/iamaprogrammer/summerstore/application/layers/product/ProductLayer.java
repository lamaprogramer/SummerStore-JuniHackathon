package net.iamaprogrammer.summerstore.application.layers.product;

import java.util.*;
 
import javafx.scene.control.Button;
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

public class ProductLayer extends Layer<ProductInfo, Void> {
  public ProductLayer() {
    super();
    
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(40);
    col.setHalignment(HPos.CENTER);

    ColumnConstraints col2 = new ColumnConstraints();
    col2.setHalignment(HPos.CENTER);
    col2.setPercentWidth(60);

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);

    grid.getColumnConstraints().addAll(col, col2);
    grid.getRowConstraints().addAll(row);
  }

  public Void init(Node node, ProductInfo data) {
    return null;
  }

  @Override
  protected void onDataPassed(Node node, ProductInfo data) {
    System.out.println("Data passed: " + data.getTitle());
    node.initApplicationNodes(data, true);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow", "primary-color");
  }

  public void listeners() {

  }
}