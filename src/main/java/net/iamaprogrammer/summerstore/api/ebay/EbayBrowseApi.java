package net.iamaprogrammer.summerstore.api.ebay;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.util.HttpUtil;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.URLBuilder;
import net.iamaprogrammer.summerstore.api.ProductInfo;


public class EbayBrowseApi {
  public static JsonObject requestRawItemData(EbayOauth2Api oauth, String query, int limit, int offset) {
    URL url = new URLBuilder("https://api.ebay.com/buy/browse/v1/item_summary/search")
      .query(query)
      .limit(limit)
      //.offset(offset)
      .build();

    try {
      Map<String, String> headers = new HashMap<>();
      headers.put("Authorization", "Bearer " + oauth.getApplicationToken());
      headers.put("X-EBAY-C-MARKETPLACE-ID", "EBAY-US");
      
      HttpURLConnection conn = HttpUtil.createConnection(url, "GET", headers);
      String response = HttpUtil.readResponse(conn);
      conn.disconnect();
      
      return HttpUtil.parseResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static List<ProductInfo> requestItems(EbayOauth2Api oauth, String query, int limit, int offset) {
    JsonObject data = requestRawItemData(oauth, query, limit, offset);
    if (data == null) {
      return null;
    }

    List<ProductInfo> items = new ArrayList<>();
    for (JsonElement item : data.getAsJsonArray("itemSummaries")) {
      items.add(new ProductInfo(oauth, item.getAsJsonObject()));
    }
    return items;
  }
}