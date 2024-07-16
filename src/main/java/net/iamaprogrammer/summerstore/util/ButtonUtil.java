package net.iamaprogrammer.summerstore.util;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class ButtonUtil {
  public static Button createTextButton(String text, String... styleClass) {
    Button button = new Button(text);
    button.getStyleClass().addAll(styleClass);
    return button;
  }

  public static Button createIconShapedButton(String icon, String... styleClass) {
    Button button = new Button("");
    button.getStyleClass().addAll("icon-button", icon);
    for (String style : styleClass) {
      button.getStyleClass().add(style);
    }
    return button;
  }

  public static Button createIconGraphicButton(String icon, String... styleClass) {
    Button button = new Button("");
    button.getStyleClass().addAll("icon-button", "icon-graphic-button");
    for (String style : styleClass) {
      button.getStyleClass().add(style);
    }
    button.setPickOnBounds(true);

    Region graphic = new Region();
    graphic.getStyleClass().addAll("icon", icon);
    button.setGraphic(graphic);
    
    return button;
  }
}