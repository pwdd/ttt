package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import com.github.pwdd.tttWebApp.serverEngine.responders.NewGameResponder
import org.scalatest.FunSuite

class ResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase
  private val headerIS = NewGameResponder.header(root, "date")
  private val header = scala.io.Source.fromInputStream(headerIS).mkString

  test("header: is properly formatted") {
    assert(header contains "OK\r\n")
    assert(header contains "date\r\n")
    assert(header contains "text/html\r\n")
    assert(header contains "\r\n\r\n")
  }
}