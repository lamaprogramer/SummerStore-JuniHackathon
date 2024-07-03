package net.iamaprogrammer.summerstore.api;

import com.google.gson.*;

public class ProductInfo {
  private String title;
  private String imageUrl;

  public ProductInfo(JsonObject data) {
    this.title = data.get("title").getAsString();
    this.imageUrl = data.get("image").getAsJsonObject().get("imageUrl").getAsString();
  }

  public String getTitle() {
    return this.title;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }
}