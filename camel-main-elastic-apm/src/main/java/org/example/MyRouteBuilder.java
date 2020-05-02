package org.example;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("timer:java-router?period=3000").routeId("java-router")
      .log("Hello world from MyRouter.java");
    
  }
}
