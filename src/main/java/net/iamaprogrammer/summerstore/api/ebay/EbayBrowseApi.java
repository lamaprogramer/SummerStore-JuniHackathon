package net.iamaprogrammer.summerstore.api.ebay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;

import net.iamaprogrammer.summerstore.api.ebay.EbayOauth2Api;
import net.iamaprogrammer.summerstore.api.URLBuilder;


public class EbayBrowseApi {
  public static String requestItems(EbayOauth2Api oauth, String query, int limit, int offset) {
    URL url = new URLBuilder("https://api.sandbox.ebay.com/buy/browse/v1/item_summary/search")
      .query(query)
      .limit(limit)
      //.offset(offset)
      .build();

    System.out.println(url);

    try {
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization", "Bearer " + oauth.getApplicationToken());
      conn.setRequestProperty("X-EBAY-C-MARKETPLACE-ID", "EBAY-US");
  
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      conn.disconnect();
      
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}