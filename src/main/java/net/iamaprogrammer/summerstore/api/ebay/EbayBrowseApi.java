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
      
      HttpURLConnection conn = createConnection(url, "GET", headers);
      String response = readResponse(conn);
      conn.disconnect();
      
      return parseResponse(response);
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
      items.add(new ProductInfo(item.getAsJsonObject()));
    }
    return items;
  }

  private static HttpURLConnection createConnection(URL url, String method, Map<String, String> headers) throws IOException, ProtocolException {
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod(method);
    for (Map.Entry<String, String> header : headers.entrySet()) {
      conn.setRequestProperty(header.getKey(), header.getValue());
    }

    return conn;
  }

  private static String readResponse(HttpURLConnection conn) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }

  private static JsonObject parseResponse(String response) {
    return JsonParser.parseString(response).getAsJsonObject();
  }
}