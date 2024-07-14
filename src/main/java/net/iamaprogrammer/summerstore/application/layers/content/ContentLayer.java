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

public class ContentLayer extends ScrollableLayer<String, List<ProductInfo>> {
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

  public List<ProductInfo> init(Node node, String data) {
    products.clear();
    this.products = EbayBrowseApi.requestItems(ebayApi, "flower%20pool", limit, offset);
    
    //node.passDataToChild("product_list", this.products);

    this.grid.setAlignment(Pos.CENTER);
    return this.products;
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;");
    scrollPane.setStyle("-fx-background-color: #FFFFF0;"); 
  }

  public void listeners() {

  }
}