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
    assert(responder.hasValidRequest)
  }

  test("hasValidRequest: is false if board or spot is not present") {
    val requestBody = "move=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.hasValidRequest)
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
}
