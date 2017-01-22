package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import org.scalatest.FunSuite

import scala.io.Source.{fromFile, fromInputStream}

object PlayResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase
  private val requestBody = "input=0&board=\"-,-,-,-,-,-,-,-,-\""
  private val responder = PlayResponder(requestBody)

  test("canRespond: is true for /play") {
    assert(responder.canRespond(root + "/play"))
  }

  test("canRespond: is false for anything else") {
    assert(!responder.canRespond(root))
  }

  test("body: returns the content of public/play.html") {
    val source = fromFile("public/play.html")
    val lines = try source.mkString.replaceAll("\\n", "") finally source.close()
    val body = fromInputStream(responder.body(root)).mkString

    assert(lines == body)
  }
}
