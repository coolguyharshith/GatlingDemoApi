package com.perf.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://demostore.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")

	val headers_0 = Map(
		"sec-ch-ua" -> """Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "document",
		"sec-fetch-mode" -> "navigate",
		"sec-fetch-site" -> "same-origin",
		"sec-fetch-user" -> "?1",
		"upgrade-insecure-requests" -> "1")

	val headers_2 = Map(
		"accept" -> "*/*",
		"sec-ch-ua" -> """Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "cors",
		"sec-fetch-site" -> "same-origin",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Cache-Control" -> "max-age=0",
		"Origin" -> "http://demostore.gatling.io",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "demostore.gatling.io"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/category/all")
			.headers(headers_0))
		.pause(3)
		.exec(http("request_1")
			.get("/product/black-and-red-glasses")
			.headers(headers_0))
		.pause(2)
		.exec(http("request_2")
			.get("/cart/add/19")
			.headers(headers_2))
		.pause(5)
		.exec(http("request_3")
			.get("/cart/view")
			.headers(headers_0))
		.pause(17)
		.exec(http("request_4")
			.post("http://" + uri1 + "/login")
			.headers(headers_4)
			.formParam("_csrf", "d70f6239-57ef-41b5-a8dc-01b0a85e5c5f")
			.formParam("username", "user1")
			.formParam("password", "pass"))
		.pause(4)
		.exec(http("request_5")
			.get("http://" + uri1 + "/cart/view")
			.headers(headers_5))
		.pause(4)
		.exec(http("request_6")
			.get("http://" + uri1 + "/cart/checkout")
			.headers(headers_5))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}