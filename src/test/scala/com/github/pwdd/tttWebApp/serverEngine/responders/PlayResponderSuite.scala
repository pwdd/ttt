package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import scala.io.Source.fromInputStream

import org.scalatest.FunSuite

class PlayResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase

  test("canRespond: is true for /play and valid request") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(responder.canRespond(root + "/play"))
  }

  test("canRespond: is false for paths other than /play") {
    val requestBody = "spot=0&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.canRespond(root))
  }

  test("canRespond: is false for /play and bad request") {
    val requestBody = "spot=a&board=-,-,-,-,-,-,-,-,-"
    val responder = PlayResponder(requestBody)
    assert(!responder.canRespond(root + "/play"))
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
    val header = fromInputStream(responder.header(root + "/play", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "Content-Type: text/html\r\n")
    assert(header contains "\r\n\r\n")
  }
}
