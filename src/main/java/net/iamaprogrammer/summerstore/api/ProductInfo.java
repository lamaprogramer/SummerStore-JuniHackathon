package net.iamaprogrammer.summerstore.api;

import com.google.gson.*;

public class ProductInfo {
  private String title;
  private String imageUrl;
  private Price price;

  public ProductInfo(JsonObject data) {
    this.title = data.get("title").getAsString();
    this.imageUrl = data.get("image").getAsJsonObject().get("imageUrl").getAsString();

    String priceValue = data.get("price").getAsJsonObject().get("value").getAsString();
    String priceCurrency = data.get("price").getAsJsonObject().get("currency").getAsString();
    this.price = new Price(priceValue, priceCurrency);
  }

  public String getTitle() {
    return this.title;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public Price getPrice() {
    return this.price;
  }

  public static class Price {
    public String currency;
    public String value;

    public Price(String value, String currency) {
      this.value = value;
      this.currency = currency;
    }
  }
}