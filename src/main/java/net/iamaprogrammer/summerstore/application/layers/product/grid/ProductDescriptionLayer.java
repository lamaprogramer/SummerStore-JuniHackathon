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
import javafx.geometry.Insets;
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

    RowConstraints imageRow = new RowConstraints(200);
    RowConstraints additionalImagesRow = new RowConstraints(100);
    RowConstraints descriptionRow = new RowConstraints(100);
    //titleDisplay.setPercentHeight(30);
    //titleDisplay.setValignment(VPos.TOP);


    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    col.setHalignment(HPos.CENTER);

    grid.getColumnConstraints().add(col);
    grid.getRowConstraints().addAll(imageRow, descriptionRow);
    grid.setHgap(15);
    grid.setVgap(15);
  }

  public void init(Node node, ProductInfo data) {
    //WebView webView = new WebView();
    //webView.getEngine().loadContent(data.getItem().getDescription());
    Image image = new Image(data.getItem().getImageUrl());
    ImageView view = new ImageView(image);
    view.setPreserveRatio(true);
    view.setFitWidth(200);
    view.setFitHeight(200);
    grid.add(view, 0, 0);

    ScrollPane imageScrollPane = this.createAdditionalImagesDisplay(
      data.getItem().getAdditionalImageUrls()
    );
    grid.add(imageScrollPane, 0, 1);
    
    Label description = new Label("This is a placeholder description because Replit does not like JavaFX's web package, which would be needed because the item descriptions provided by eBay's API are raw HTML.");
    description.setWrapText(true);
    GridPane.setMargin(description, new Insets(0,50,0,50));
    grid.add(description, 0, 2);

  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: #FFFFF0;");
    scrollPane.setStyle("-fx-background-color: #FFFFF0;");
  }

  public void listeners() {

  }

  private ScrollPane createAdditionalImagesDisplay(List<String> additionalImages) {
    ScrollPane imageScrollPane = new ScrollPane();
    GridPane imageHolder = new GridPane();

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);
    row.setValignment(VPos.CENTER);
    imageHolder.getRowConstraints().add(row);

    for (int i = 0; i < additionalImages.size(); i++) {
      ColumnConstraints col = new ColumnConstraints(100);
      col.setHalignment(HPos.CENTER);
      imageHolder.getColumnConstraints().add(col);

      String imageUrl = additionalImages.get(i);

      Image additionalImage = new Image(imageUrl);
      ImageView additionalImageView = new ImageView(additionalImage);
      additionalImageView.setPreserveRatio(true);
      additionalImageView.setFitWidth(80);
      additionalImageView.setFitHeight(80);

      imageHolder.add(additionalImageView, i, 0);
    }
    this.scrollPane.setFitToWidth(true);
    this.scrollPane.setFitToHeight(true);
    imageScrollPane.setContent(imageHolder);
    GridPane.setMargin(imageScrollPane, new Insets(0,50,0,50));
    return imageScrollPane;
  }
}