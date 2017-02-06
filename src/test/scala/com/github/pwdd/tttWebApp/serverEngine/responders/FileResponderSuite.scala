package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.FileInputStream
import java.nio.file.Paths

import org.scalatest.FunSuite

import scala.io.Source.fromInputStream

class FileResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("canRespond: is true for root + /js") {
    assert(FileResponder.canRespond(root + "/js"))
  }

  test("canRespond: is true for root + /js ending in trailing slash") {
    assert(FileResponder.canRespond(root + "/js/"))
  }

  test("canRespond: is true for any file that exists inside /public") {
    assert(FileResponder.canRespond(root + "/js/src/ajax.js"))
  }

  test("canRespond: is false for non existing files") {
    assert(!FileResponder.canRespond(root + "/js/foo.html"))
  }

  test("canRespond: is false for root") {
    assert(!FileResponder.canRespond(root))
  }

  test("header: has properly formatted header if canRespond is true") {
    val header = fromInputStream(FileResponder.header("/public/js", "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("header: has the right content type") {
    val header = fromInputStream(FileResponder.header(root + "/js/ajax.js", "date")).mkString
    assert(header contains "text/plain")
  }

  test("body: responds with the content of index.html if request is to /js") {
    val body = fromInputStream(FileResponder.body(root + "/js")).mkString
    assert(body contains "TTT-JS")
  }

  test("body: responds with the content of requested file") {
    val body = fromInputStream(FileResponder.body(root + "/js/src/ajax.js")).mkString
    val file = new FileInputStream(root + "/js/src/ajax.js")
    val fileContent = fromInputStream(file).mkString
    assert(fileContent === body)
  }
}
