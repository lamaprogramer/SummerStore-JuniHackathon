package net.iamaprogrammer.summerstore.application.layers.content;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;

public class ContentLayer extends ScrollableLayer {
  private static final EbayOauth2Api ebayApi = new EbayOauth2Api();

  public ContentLayer() {
    super();
  }

  public void init() {
    // Init Grid
    int columnCount = 3;

    for (int i = 0; i < columnCount; i++) {
      RowConstraints row = new RowConstraints(400);
      //row.setPercentHeight(200);

      ColumnConstraints col = new ColumnConstraints(250);
      //col.setPercentWidth(100 / columnCount);

      grid.getRowConstraints().addAll(row);
      grid.getColumnConstraints().addAll(col);
    }

    grid.setHgap(10);
    grid.setVgap(10);

    // Get json data.
    JsonObject response = EbayBrowseApi.requestItems(ebayApi, "pool", 9, 0);

    //String linkToNext = response.get("next").getAsString();
    JsonArray items = response.getAsJsonArray("itemSummaries");
    System.out.println(items.get(0));

    for (int i = 0; i < columnCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        GridPane pane = new GridPane();

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(100);
        col.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(col);
        
        if (i * columnCount + j < items.size()) {
          JsonElement imageUrl = items.get(i * columnCount + j).getAsJsonObject().get("image").getAsJsonObject().get("imageUrl");
          if (imageUrl != null) {
            Image image = new Image(imageUrl.getAsString());
            ImageView view = new ImageView(image);
            pane.add(view, 0, 0);
          }
        }
        
        pane.setStyle("-fx-background-color: green;");

        this.grid.add(pane, i, j);
      }
    }
    
    //col1.setHalignment(HPos.CENTER);

    grid.setAlignment(Pos.CENTER); 
    this.addToParent(0, 1);
  }

  public void style() {
    grid.getStyleClass().addAll("with-dropshadow");
    grid.setStyle("-fx-background-color: red;");
    scrollPane.setStyle("-fx-background-color: red;"); 
  }

  public void listeners() {

  }
}