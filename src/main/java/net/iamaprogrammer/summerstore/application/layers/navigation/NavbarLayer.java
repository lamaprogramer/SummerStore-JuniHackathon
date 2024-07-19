package net.iamaprogrammer.summerstore.application.layers.navigation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.util.ButtonUtil;
import net.iamaprogrammer.summerstore.application.datahandlers.LayerDataHandler;
import net.iamaprogrammer.summerstore.application.datahandlers.DataType;

public class NavbarLayer extends Layer<Void, Void> {
  Button menuButton;
  Button githubButton;
  TextField searchBar;
  Label brand;

  public NavbarLayer() {
    super();

    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(100);

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(10);
    col1.setHalignment(HPos.CENTER);
    
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(30);
    col2.setHalignment(HPos.CENTER);
    
    ColumnConstraints col3 = new ColumnConstraints();
    col3.setPercentWidth(30);
    col3.setHalignment(HPos.CENTER);
    
    ColumnConstraints col4 = new ColumnConstraints();
    col4.setPercentWidth(20);
    col4.setHalignment(HPos.CENTER);
    
    ColumnConstraints col5 = new ColumnConstraints();
    col5.setPercentWidth(10);
    col5.setHalignment(HPos.CENTER);

    grid.getRowConstraints().addAll(row1);
    grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

    grid.setAlignment(Pos.CENTER); 
  }

  public Void init(Node node, Void data) {
    menuButton = ButtonUtil.createIconShapedButton("menu-icon", "with-dropshadow", "secondary-color");
    brand = new Label("Summer Store");
    
    searchBar = new TextField();
    searchBar.setPromptText("Search: ");
    searchBar.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        if (node.getParent().getChild("products").isEnabled()) {
          node.passDataToPeer("products", 
            new LayerDataHandler(1,
              new DataType<>(searchBar.getText())
            )
          );
        }
      }
    });
    
    githubButton = ButtonUtil.createIconShapedButton("github-icon", "with-dropshadow", "secondary-color");

    grid.add(menuButton, 0, 0);
    grid.add(brand, 1, 0);
    grid.add(searchBar, 3, 0);
    grid.add(githubButton, 4, 0);

    return null;
  }

  public void style() {
    brand.getStyleClass().addAll("with-dropshadow", "brand-title");
    searchBar.getStyleClass().addAll("with-dropshadow", "light-primary-color");
    grid.getStyleClass().addAll("with-dropshadow", "primary-color");
  }
}