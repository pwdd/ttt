package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.{FunSuite, Matchers}
import scala.io.Source.fromInputStream
import play.api.libs.json.Json

class NewGameJSONResponderSuite extends FunSuite with Matchers {
  private val path = "/new.json"
  private val headerIS = NewGameJSONResponder.header(path, "date")
  private val header = scala.io.Source.fromInputStream(headerIS).mkString

  test("canRespond: is true for /new.json/") {
    assert(NewGameJSONResponder.canRespond("/new.json/"))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameJSONResponder.canRespond("/play/new.json"))
  }

  test("header: is properly formatted") {
    assert(header contains "OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("header: contains right content type") {
    assert(header contains "application/json\r\n")
  }

  test("body: should contain a valid string representing a valid JSON board") {
    val body = NewGameJSONResponder.body("/new.json")
    val bodyString = fromInputStream(body).mkString
    noException should be thrownBy Json.parse(bodyString)
  }

  test("body: should contain a representation of an empty board") {
    val body = NewGameJSONResponder.body("/new.json")
    val bodyString = fromInputStream(body).mkString
    assert(bodyString contains """["_","_","_","_","_","_","_","_","_"]""")
  }
}
