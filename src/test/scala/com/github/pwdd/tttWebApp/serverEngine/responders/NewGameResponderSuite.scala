package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import com.github.pwdd.tttWebApp.tttEngine.GameSettings
import org.scalatest.FunSuite

import scala.io.Source.fromInputStream

class NewGameResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("canRespond: is true for root") {
    assert(NewGameResponder.canRespond(root))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameResponder.canRespond("/play"))
  }

  test("header: has properly formatted header if canRespond is true") {
    val responder = NewGameResponder
    val header = fromInputStream(responder.header(root, "date")).mkString
    assert(header contains "HTTP/1.1 200 OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "\r\n\r\n")
  }

  test("header: has proper content type") {
    val responder = NewGameResponder
    val header = fromInputStream(responder.header(root, "date")).mkString
    assert(header contains "text/html")
  }

  test("body: has 9 form tags") {
    val responder = NewGameResponder
    val body = fromInputStream(responder.body(root)).mkString
    assert("<form".r.findAllMatchIn(body).length == GameSettings.boardLength)
  }

  test("body: is valid html doc") {
    val responder = NewGameResponder
    val body = fromInputStream(responder.body(root)).mkString
    assert(body contains "<!doctype html>")
  }
}
