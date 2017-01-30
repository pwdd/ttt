package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.Json

import scala.io.Source.fromInputStream

class NewGameJSONResponderSuite extends FunSuite with Matchers {
  test("canRespond: is true for /new.json") {
    assert(NewGameJSONResponder.canRespond("/new.json"))
  }

  test("canRespond: is false for paths other than /new.json") {
    assert(!NewGameJSONResponder.canRespond("/foo/new.json"))
  }

  test("header: has properly formatted header if canRespond is true") {
    val header = fromInputStream(NewGameJSONResponder.header("/new.json", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("header: has the proper content type") {
    val header = fromInputStream(NewGameJSONResponder.header("/move.json", "date")).mkString
    assert(header contains "Content-Type: application/json")
  }

  test("body: can be parsed into a JSON object if request is valid") {
    val body = fromInputStream(NewGameJSONResponder.body("/new.json")).mkString
    noException should be thrownBy(Json.parse(body))
  }
}
