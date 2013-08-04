package com.bookingbug.apiclient;

import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
// import org.codehaus.jackson.map.ObjectMapper;
// import org.json.JSONObject;

// import org.apache.http.impl.client.DefaultHttpClient;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.HttpResponse;

/**
* Simple interactive console application.
* Uses the java.io.Console class of Java 6.
*/
public final class BookingBugConsole {

  public static final void main(String... aArgs){
    Console console = System.console();
    //read user name, using java.util.Formatter syntax :
    String username = console.readLine("User Name? ");

    //read the password, without echoing the output 
    char[] password = console.readPassword("Password? ");

    //verify user name and password using some mechanism (elided)

    //the javadoc for the Console class recommends "zeroing-out" the password 
    //when finished verifying it :
    Arrays.fill(password, ' ');

    console.printf("Welcome, %1$s.", username);
    console.printf(fNEW_LINE);

    callUrl("http://mayfly.booking-bug.com/api/v1/login", console);

  // String className = console.readLine("Please enter a package-qualified class name : ");
  // Class theClass = null;
  // try {
  //   theClass = Class.forName(className);
  //   console.printf("The inheritance tree: %1$s", getInheritanceTree(theClass));
  // }
  // catch(ClassNotFoundException ex){
  //    console.printf("Cannot find that class.");
  // }

    //this version just exits, without asking the user for more input
    console.printf("Bye.");
  }

  private static void callUrl(String urlString, Console console) {
    try {
      URL url = new URL(urlString);
      BookingBugApi api = new BookingBugApi();
      // Map<String, Object> response = api.callApi(url);
      String response = api.callApi(url);
      console.printf("got response");
      console.printf(fNEW_LINE);
      // console.printf(response.toString());
      console.printf(response);
      console.printf(fNEW_LINE);
      // Map<String, Object> user = response.get("user");
      // Object links = user.get("links");
      // console.printf(links.toString());
      // console.printf(response.keySet().toString());
      // console.printf(fNEW_LINE);
    } catch (MalformedURLException e) {
      console.printf(e.toString());
    } catch (IOException e) {
      console.printf(e.toString());
    }
  }
  
  // PRIVATE //
  // private static final String fHEADER = "The inheritance tree:";
  private static final String fNEW_LINE = System.getProperty("line.separator");

  // private static String getInheritanceTree(Class aClass){
  //   StringBuilder superclasses = new StringBuilder();
  //   superclasses.append( fNEW_LINE );
  //   Class theClass = aClass;
  //   while ( theClass != null ) {
  //     superclasses.append( theClass );
  //     superclasses.append( fNEW_LINE );
  //     theClass = theClass.getSuperclass();
  //   }
  //   superclasses.append( fNEW_LINE );
  //   return superclasses.toString();
  // }

}
