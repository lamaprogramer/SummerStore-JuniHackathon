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
//import javafx.scene.web.WebView;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;
import net.iamaprogrammer.summerstore.api.ProductInfo;

import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;

public class ProductDescriptionLayer extends ScrollableLayer<ProductInfo> {
  public ProductDescriptionLayer() {
    super();

    RowConstraints descriptionRow = new RowConstraints(100);
    //titleDisplay.setPercentHeight(30);
    //titleDisplay.setValignment(VPos.TOP);


    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    grid.getColumnConstraints().add(col);
    grid.getRowConstraints().addAll(descriptionRow);

    // might be clearing this class from parent, but then never adding it back
  }

  public void init(Node node, ProductInfo data) {
    //WebView webView = new WebView();
    //webView.getEngine().loadContent(data.getItem().getDescription());

    Label description = new Label("This is a placeholder description because Replit does not like JavaFX's web package, which would be needed because the item descriptions provided by eBay's API are raw HTML.");
    description.setWrapText(true);
    //description.getStyleClass().add("item-title");
    grid.add(description, 0, 0);

  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: blue;");
  }

  public void listeners() {

  }
}