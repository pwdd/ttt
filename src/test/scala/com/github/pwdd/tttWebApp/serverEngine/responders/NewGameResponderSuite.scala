package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.FunSuite

import scala.io.Source.fromInputStream

class NewGameResponderSuite extends FunSuite {

  test("canRespond: is true for root") {
    assert(NewGameResponder.canRespond("/"))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameResponder.canRespond("/play"))
  }

  test("header: is properly formatted") {
    val root = "/"
    val headerIS = NewGameResponder.header(root, "date")
    val header = scala.io.Source.fromInputStream(headerIS).mkString

    assert(header contains "OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "text/html\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("body: should contain a representation of an empty board") {
    val body = NewGameResponder.body("/")
    val bodyString = fromInputStream(body).mkString
    assert(bodyString contains "-,-,-,-,-,-,-,-,-")
  }
}
