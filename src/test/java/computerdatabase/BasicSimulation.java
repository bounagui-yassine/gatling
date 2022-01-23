package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol = http
    .baseUrl("http://localhost:8081") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");

  ScenarioBuilder scn = scenario("Scenario1") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
      .get("/contrats/2"))
    .pause(5) // Note that Gatling has recorded real time pauses

    .exec(http("request_2") // Here's an example of a POST request
      .post("/contrats/statutEbo")
      .formParam("id", "2")); // Note the triple double quotes: used in Scala for protecting a whole chain of characters (no need for backslash)

    
  {
    setUp(scn.injectOpen(atOnceUsers(800)).protocols(httpProtocol));
  }
}