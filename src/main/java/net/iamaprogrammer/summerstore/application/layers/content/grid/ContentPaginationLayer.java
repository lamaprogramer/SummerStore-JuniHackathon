package net.iamaprogrammer.summerstore.application.layers.content.grid;

import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.HPos;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi.*;
import net.iamaprogrammer.summerstore.api.ProductInfo;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.ScrollableLayer;
import net.iamaprogrammer.summerstore.application.datahandlers.LayerDataHandler;
import net.iamaprogrammer.summerstore.application.datahandlers.DataType;

public class ContentPaginationLayer extends Layer<LayerDataHandler, Void> {
  Label pagesLabel;
  
  public ContentPaginationLayer() {
    super();

    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);

    ColumnConstraints deadSpace1 = new ColumnConstraints();
    deadSpace1.setPercentWidth(20);

    ColumnConstraints backwardButton = new ColumnConstraints();
    backwardButton.setPercentWidth(10);

    ColumnConstraints pages = new ColumnConstraints();
    pages.setPercentWidth(40);
    pages.setHalignment(HPos.CENTER);

    ColumnConstraints forwardButton = new ColumnConstraints();
    forwardButton.setPercentWidth(10);
    
    ColumnConstraints deadSpace2 = new ColumnConstraints();
    deadSpace2.setPercentWidth(20);

    grid.getRowConstraints().addAll(row);
    grid.getColumnConstraints().addAll(deadSpace1, backwardButton, pages, forwardButton, deadSpace2);
  }

  public Void init(Node node, LayerDataHandler data) {
    Button forwardButton = new Button("Forward");
    forwardButton.getStyleClass().addAll("secondary-color", "with-dropshadow");
    Button backwardButton = new Button("Backward");
    backwardButton.getStyleClass().addAll("secondary-color", "with-dropshadow");

    forwardButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        node.passDataToParent( 
          new LayerDataHandler(new DataType<>(1))
        );
      }
    });

    backwardButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        node.passDataToParent( 
          new LayerDataHandler(new DataType<>(-1))
        );
      }
    });
    List<ProductInfo> products = data.get(List.class);
    PaginationInfo paginationInfo = products.get(0).getPaginationInfo();
    
    pagesLabel = new Label(paginationInfo.getOffset()/paginationInfo.getLimit() + " of " + paginationInfo.getItemCount()/paginationInfo.getLimit());

    grid.add(backwardButton, 1, 0);
    grid.add(pagesLabel, 2, 0);
    grid.add(forwardButton, 3, 0);
    
    return null;
  }

  @Override
  protected void onDataPassed(Node node, LayerDataHandler data) {
    List<ProductInfo> products = data.get(List.class);
    PaginationInfo paginationInfo = products.get(0).getPaginationInfo();

    pagesLabel = new Label("Page " + (paginationInfo.getOffset()/paginationInfo.getLimit())+1 + " of " + paginationInfo.getItemCount()/paginationInfo.getLimit());
  }

  public void style() {
    grid.getStyleClass().addAll("primary-color");
  }
}