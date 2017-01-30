package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.nio.file.Paths
import java.util

import com.github.pwdd.tttWebApp.serverEngine.responders._
import org.scalatest.{FunSuite, Matchers}

class ProtocolFactorySuite extends FunSuite with Matchers {
  val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("createProtocol: creates a GETProtocol with right list of responders") {
    val request = new util.HashMap[String, String] {
      put("URI", "/foo")
      put("Method", "GET")
      put("Protocol", "HTTP/1.1")
      put("Body", "spot=0&board=-,-,-,-,-,-,-,-,-")
    }

    val protocol = ProtocolFactory.createProtocol(root, request)

    assert(protocol.respondersList contains FileResponder)
    assert(protocol.respondersList contains NewGameResponder)
    assert(protocol.respondersList contains NewGameJSONResponder)
  }

  test("createProtocol: creates a POSTProtocol with right list of responders") {
    val request = new util.HashMap[String, String] {
      put("URI", "/foo")
      put("Method", "POST")
      put("Protocol", "HTTP/1.1")
      put("Body", "spot=0&board=-,-,-,-,-,-,-,-,-")
    }

    val body = request.get("Body")

    val protocol = ProtocolFactory.createProtocol(root, request)

    assert(protocol.respondersList contains PlayResponder(body))
    assert(protocol.respondersList contains PlayJSONResponder(body))
  }
}
