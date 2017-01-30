package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.nio.file.Paths
import java.util

import org.scalatest.FunSuite

import scala.io.Source.fromInputStream

class GETProtocolSuite extends FunSuite {
  val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("errorMessage: has properly formatted header if POST cannot handle post request") {
    val invalidRequest = new util.HashMap[String, String] {
      put("URI", "/foo")
      put("Method", "GET")
      put("Protocol", "HTTP/1.1")
      put("Body", "spot=0&board=-,-,-,-,-,-,-,-,-")
    }

    val get = GETProtocol(root, invalidRequest, Array())

    val errorMessage = fromInputStream(get.errorMessage("date")).mkString

    assert(errorMessage contains "HTTP/1.1 404 Not Found\r\n")
    assert(errorMessage contains "date\r\n")
    assert(errorMessage contains "\r\n\r\n")
  }
}
