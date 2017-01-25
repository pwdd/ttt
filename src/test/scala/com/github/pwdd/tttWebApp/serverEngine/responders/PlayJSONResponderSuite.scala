package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.FunSuite
import scala.io.Source.fromInputStream

class PlayJSONResponderSuite extends FunSuite {
  test("canRespond: is true for /move.json and valid request") {
    val requestBody = """{"spot":0,"board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    assert(responder.canRespond("/move.json"))
  }

  test("canRespond: is false for paths other than /move.json") {
    val requestBody = """{"spot":0,"board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.canRespond("foo/move.json"))
  }

  test("canRespond: is false for /move and bad request") {
    val requestBody = """{"spot":"a","board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.canRespond("/move.json"))
  }

  test("hasValidRequest: is true for valid spot and board") {
    val requestBody = """{"spot":0,"board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    assert(responder.hasValidRequest)
  }

  test("hasValidRequest: is false if board or spot is not present") {
    val requestBody = """{"input":0,"board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    assert(!responder.hasValidRequest)
  }

  test("header: has properly formatted header if canRespond is true") {
    val requestBody = """{"spot":0,"board":["_","_","_","_","_","_","_","_","_"]}""""
    val responder = PlayJSONResponder(requestBody)
    val header = fromInputStream(responder.header("/move.json", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "Content-Type: application/json\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("body: should contain a representation of the next state of the board") {
    val requestBody = """{"spot":0,"board":["_","o","x","o","o","x","x","x","o"]}""""
    val responder = PlayJSONResponder(requestBody)
    val bodyString = fromInputStream(responder.body("/move.json")).mkString
    assert(bodyString contains """["x","o","x","o","o","x","x","x","o"]""")
  }
}

