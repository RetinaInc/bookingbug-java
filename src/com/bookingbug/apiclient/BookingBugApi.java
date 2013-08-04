package com.bookingbug.apiclient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

public class BookingBugApi {

  public String callApi(URL url) throws IOException {

    String returnString = "";
    System.out.println("==");
    System.out.println(System.getProperty("java.class.path"));
    // SSLSocketFactory sslSocketFactory = disableSslCheck();
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    // urlConnection.setSSLSocketFactory(sslSocketFactory);
    // Map object = new HashMap();
    try {
      // urlConnection.setRequestProperty("Authorization", "basic " +
        // Base64.encodeToString("edeimos@gmail.com:e".getBytes(), true));
      urlConnection.setRequestMethod("GET");
      urlConnection.setRequestProperty("User-Agent", "BookingBug Java Client");
      urlConnection.setRequestProperty("App-Id", "");
      urlConnection.setRequestProperty("App-Key", "");
      System.out.println("Response code: "+urlConnection.getResponseCode());
      // DefaultRepresentationFactory representationFactory = new DefaultRepresentationFactory();
      JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
      System.out.println("representer");
      // System.out.println("email: "+representation.getValue("email"));
      BufferedReader in = new BufferedReader(
        new InputStreamReader(urlConnection.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      returnString = response.toString();
      InputStream ins = new ByteArrayInputStream(returnString.getBytes());
      Reader insr = new InputStreamReader(ins);
      ReadableRepresentation representation = representationFactory.readRepresentation(insr);
      System.out.println("representation");
      System.out.println(representation.getValue("email"));
    } finally {
       urlConnection.disconnect();
    }
    return returnString;

  }

  // private static SSLSocketFactory disableSslCheck() { 
  //   try {
  //     // Create a trust manager that does not validate certificate chains
  //     final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
  //         @Override
  //         public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
  //         }
  //         @Override
  //         public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
  //         }
  //         @Override
  //         public X509Certificate[] getAcceptedIssuers() {
  //             return null;
  //         }
  //     } };
      
  //     // Install the all-trusting trust manager
  //     final SSLContext sslContext = SSLContext.getInstance( "SSL" );
  //     sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
  //     // Create an ssl socket factory with our all-trusting manager
  //     // sslSocketFactory = sslContext.getSocketFactory();
  //     return sslContext.getSocketFactory();
  //     // HttpsURLConnection.setDefaultSSLSocketFactory( sslContext.getSocketFactory() );
  //   } catch (final Exception e) {
  //     System.out.print("== err!!! ==");
  //     e.printStackTrace();
  //     return null;
  //   }
  // }

}

