package net.iamaprogrammer.summerstore.api;

import java.util.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.util.HttpUtil;
import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.ebay.EbayBrowseApi.*;

public class ProductInfo {
  private String title;
  private String imageUrl;
  private Price price;
  private Item item;
  private PaginationInfo paginationInfo;

  public ProductInfo(EbayOauth2Api oauth, JsonObject data, PaginationInfo paginationInfo) {
    this.title = data.get("title").getAsString();
    this.imageUrl = data.get("image").getAsJsonObject().get("imageUrl").getAsString();

    String priceValue = data.get("price").getAsJsonObject().get("value").getAsString();
    String priceCurrency = data.get("price").getAsJsonObject().get("currency").getAsString();
    this.price = new Price(priceValue, priceCurrency);

    this.item = new Item(oauth, data.get("itemHref").getAsString());

    this.paginationInfo = paginationInfo;
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

  public Item getItem() {
    return this.item;
  }

  public PaginationInfo getPaginationInfo() {
    return this.paginationInfo;
  }

  public static class Price {
    public String currency;
    public String value;

    public Price(String value, String currency) {
      this.value = value;
      this.currency = currency;
    }
  }

  public static class Item {
    private String subtitle;
    private String description;
    private String imageUrl;
    private List<String> additionalImageUrls = new ArrayList<>();
    
    public Item(EbayOauth2Api oauth, String url) {
      try {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + oauth.getApplicationToken());
        headers.put("X-EBAY-C-MARKETPLACE-ID", "EBAY-US");
  
        HttpURLConnection conn = HttpUtil.createConnection(new URL(url), "GET", headers);
        String response = HttpUtil.readResponse(conn);
        conn.disconnect();
  
        JsonObject jsonResponse = HttpUtil.parseResponse(response);
        //System.out.println(jsonResponse);
        //this.subtitle = jsonResponse.get("subtitle").getAsString();
        this.description = jsonResponse.get("description").getAsString();
        this.imageUrl = jsonResponse.get("image").getAsJsonObject().get("imageUrl").getAsString();

        if (jsonResponse.has("additionalImages")) {
          for (JsonElement image : jsonResponse.get("additionalImages").getAsJsonArray()) {
            additionalImageUrls.add(image.getAsJsonObject().get("imageUrl").getAsString());
          }
        }
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public String getSubtitle() {
      return this.subtitle;
    }

    public String getDescription() {
      return this.description;
    }

    public String getImageUrl() {
      return this.imageUrl;
    }

    public List<String> getAdditionalImageUrls() {
      return this.additionalImageUrls;
    }
  }
}