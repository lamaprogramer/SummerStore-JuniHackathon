package net.iamaprogrammer.summerstore.application.layers.navigation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import net.iamaprogrammer.summerstore.application.Layer;
import net.iamaprogrammer.summerstore.application.TreeBasedApplication;
import net.iamaprogrammer.summerstore.application.Node;
import net.iamaprogrammer.summerstore.util.ButtonUtil;

public class NavbarLayer extends Layer<Void, Void> {
  Button menuButton;
  Button githubButton;
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
    col3.setPercentWidth(50);
    col3.setHalignment(HPos.CENTER);
    ColumnConstraints col4 = new ColumnConstraints();
    col4.setPercentWidth(10);
    col4.setHalignment(HPos.CENTER);

    grid.getRowConstraints().addAll(row1);
    grid.getColumnConstraints().addAll(col1, col2, col3, col4);

    grid.setAlignment(Pos.CENTER); 
  }

  public Void init(Node node, Void data) {
    menuButton = ButtonUtil.createIconShapedButton("menu-icon", "with-dropshadow", "secondary-color");
    githubButton = ButtonUtil.createIconShapedButton("github-icon", "with-dropshadow", "secondary-color");
    brand = new Label("Summer Store");

    grid.add(menuButton, 0, 0);
    grid.add(brand, 1, 0);
    grid.add(githubButton, 3, 0);

    return null;
  }

  public void style() {
    brand.getStyleClass().addAll("with-dropshadow", "brand-title");
    grid.getStyleClass().addAll("with-dropshadow", "primary-color");
  }
}