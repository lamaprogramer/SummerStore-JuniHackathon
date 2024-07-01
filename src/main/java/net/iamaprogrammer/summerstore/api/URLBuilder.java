package net.iamaprogrammer.summerstore.api;

import java.net.URL;
import java.util.*;

public class URLBuilder {
  private String baseURL;
  private List<String> params = new ArrayList<>();
  
  public URLBuilder(String baseURL) {
    this.baseURL = baseURL;
  }

  public URLBuilder query(String q) {
    this.params.add("q=" + q);
    return this;
  }

  public URLBuilder offset(int offset) {
    this.params.add("offset=" + offset);
    return this;
  }

  public URLBuilder limit(int limit) {
    this.params.add("limit=" + limit);
    return this;
  }

  public URL build() {
    try {
      //System.out.println(stringifyParams());
      return new URL(this.baseURL + stringifyParams());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String stringifyParams() {
    StringBuilder sb = new StringBuilder();
    sb.append("?");

    for (String param : this.params) {
      sb.append(param);
      if (this.params.indexOf(param) != this.params.size() - 1) {
        sb.append("&");
      }
    }
    return sb.toString();
  }
}