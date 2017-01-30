package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.nio.file.Paths
import java.util

import scala.io.Source.fromInputStream
import org.scalatest.FunSuite

class POSTProtocolSuite extends FunSuite {
  val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("errorMessage: has properly formatted header if POST cannot handle post request") {
    val invalidRequest = new util.HashMap[String, String] {
      put("URI", "/foo")
      put("Method", "POST")
      put("Protocol", "HTTP/1.1")
      put("Body", "spot=0&board=-,-,-,-,-,-,-,-,-")
    }

    val post = POSTProtocol(root, invalidRequest, Array())

    val errorMessage = fromInputStream(post.errorMessage("date")).mkString

    assert(errorMessage contains "HTTP/1.1 400 Bad Request\r\n")
    assert(errorMessage contains "date\r\n")
    assert(errorMessage contains "\r\n\r\n")
  }
}
