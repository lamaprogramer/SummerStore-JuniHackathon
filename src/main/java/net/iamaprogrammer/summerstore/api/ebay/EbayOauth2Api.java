package net.iamaprogrammer.summerstore.api.ebay;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.model.AccessToken;
import com.ebay.api.client.auth.oauth2.model.Environment;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;

public class EbayOauth2Api {
  private static final List<String> SCOPE_LIST_SANDBOX = Arrays.asList(new String[]{"https://api.ebay.com/oauth/api_scope", "https://api.ebay.com/oauth/api_scope/buy.item.feed"});

  // YAML stored in string for security purpouses.
  private static final String EBAY_CONFIG_YAML = String.format("name: ebay-config\n\napi.sandbox.ebay.com:\n    appid: %s\n    certid: %s\n    devid: %s\n    redirecturi: %s", System.getenv("EBAY_APP_ID"), System.getenv("EBAY_CERT_ID"), System.getenv("EBAY_DEV_ID"), System.getenv("EBAY_REDIRECT_URI"));
  
  private static String APPLICATION_TOKEN;
  private static boolean CREDENTIALS_LOADED = false;
  private static OAuth2Api oauth2Api;

  public EbayOauth2Api() {
    try {
      CredentialUtil.load(EBAY_CONFIG_YAML);
      CREDENTIALS_LOADED = true;
      oauth2Api = new OAuth2Api();
  
      OAuthResponse oauth2Response = oauth2Api.getApplicationToken(Environment.SANDBOX, SCOPE_LIST_SANDBOX);
      Optional<AccessToken> applicationToken = oauth2Response.getAccessToken();
      
      if (!applicationToken.isPresent()) {
        System.out.println("Error: Could not get application token");
      }
      
      APPLICATION_TOKEN = applicationToken.get().getToken();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public boolean credentialsLoaded() {
    return CREDENTIALS_LOADED;
  }

  public boolean hasApplicationToken() {
    return APPLICATION_TOKEN != null;
  }

  public String getApplicationToken() {
    return APPLICATION_TOKEN;
  }
}