package com.github.pwdd.tttWebApp.serverEngine.responders

import scala.io.Source.fromInputStream

import org.scalatest.FunSuite

class PlayResponderSuite extends FunSuite {
  test("canRespond: is true for /play and valid request") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(responder.canRespond("/play"))
  }

  test("canRespond: is false for paths other than /play") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.canRespond("foo/play"))
  }

  test("canRespond: is false for /play and bad request") {
    val requestBody = "spot=a&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.canRespond("/play"))
  }

  test("hasValidRequest: is true for valid spot and board") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(responder.hasValidaRequest)
  }

  test("hasValidRequest: is false if board or spot is not present") {
    val requestBody = "move=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.hasValidaRequest)
  }

  test("header: has properly formatted header if canRespond is true") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    val header = fromInputStream(responder.header("/play", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "Content-Type: text/html\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("body: should contain a representation of the next state of the board") {
    val requestBody = "spot=0&board=" +
      "-,o,x," +
      "o,o,x," +
      "x,x,o"
    val responder = PlayResponder(requestBody)
    val bodyString = fromInputStream(responder.body("/play")).mkString
    assert(bodyString contains "x,o,x,o,o,x,x,x,o")
  }
}
