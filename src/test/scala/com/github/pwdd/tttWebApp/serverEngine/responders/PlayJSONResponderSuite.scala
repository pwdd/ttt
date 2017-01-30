package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.Json

import scala.io.Source.fromInputStream

class PlayJSONResponderSuite extends FunSuite with Matchers {
  test("canRespond: is true for /move.json and valid request") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    assert(responder.canRespond("/move.json"))
  }

  test("canRespond: is false for paths other than /play") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.canRespond("/foo/move.json"))
  }

  test("canRespond: is false for /move.json and bad request") {
    val requestBody = "{\"spot\":0,\"board\":[]}"
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.canRespond("/move.json"))
  }

  test("hasValidRequest: is true for valid spot and board") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    assert(responder.hasValidRequest)
  }

  test("hasValidRequest: is false if board or spot is not present") {
    val requestBody = "{\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.hasValidRequest)
  }

  test("header: has properly formatted header if canRespond is true") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    val header = fromInputStream(responder.header("/move.json", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("header: has the proper content type") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    val header = fromInputStream(responder.header("/move.json", "date")).mkString
    assert(header contains "Content-Type: application/json")
  }

  test("body: can be parsed into a JSON object if request is valid") {
    val requestBody = "{\"spot\":0,\"board\":[\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\",\"_\"]}"
    val responder = PlayJSONResponder(requestBody)
    val body = fromInputStream(responder.body("/move.json")).mkString
    noException should be thrownBy(Json.parse(body))
  }
}
