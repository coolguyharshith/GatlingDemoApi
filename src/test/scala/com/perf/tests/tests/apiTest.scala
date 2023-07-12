package com.perf.tests.tests


import io.gatling.core.Predef._
import io.gatling.http.Predef._


class apiTest extends Simulation {

  //protocol
  val httpProtocol = http.baseUrl(url= "https://reqres.in/api/users")

  //Scenario
  val scn = scenario( scenarioName = "Get Api Request Demo" )
    .exec(
      http( requestName = "Get Single User"  )
        .get( "/2" )
        .check(
          status.is( expected = 200),
          jsonPath( path = "$x.data.first_name").is(expected = "Janet" ))
        )
    .pause( duration = 1)
  //setup
  setUp(
    scn.inject(rampUsers(users = 10).during(5))
      .protocols(httpProtocol)
  )

}
