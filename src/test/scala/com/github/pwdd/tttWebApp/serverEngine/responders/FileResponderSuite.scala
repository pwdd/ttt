package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import org.scalatest.FunSuite

class FileResponderSuite extends FunSuite {
  private val root = Paths.get(System.getProperty("user.dir"), "/public").toString

  test("canRespond: is true for root + /js") {
    assert(FileResponder.canRespond(root + "/js"))
  }

  test("canRespond: is true for root + /js ending in trailing slash") {
    assert(FileResponder.canRespond(root + "/js/"))
  }

  test("canRespond: is true for any file that exists inside /public") {
    assert(FileResponder.canRespond(root + "/js/ajax.js"))
  }

  test("canRespond: is false for non existing files") {
    assert(!FileResponder.canRespond(root + "/js/foo.html"))
  }

  test("canRespond: is false for root") {
    assert(!FileResponder.canRespond(root))
  }
}
