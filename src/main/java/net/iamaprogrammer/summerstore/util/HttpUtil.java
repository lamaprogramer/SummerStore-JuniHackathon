package net.iamaprogrammer.summerstore.util;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.*;

public class HttpUtil {
  public static HttpURLConnection createConnection(URL url, String method, Map<String, String> headers) throws IOException, ProtocolException {
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod(method);
    for (Map.Entry<String, String> header : headers.entrySet()) {
      conn.setRequestProperty(header.getKey(), header.getValue());
    }

    return conn;
  }

  public static String readResponse(HttpURLConnection conn) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }

  public static JsonObject parseResponse(String response) {
    return JsonParser.parseString(response).getAsJsonObject();
  }
}