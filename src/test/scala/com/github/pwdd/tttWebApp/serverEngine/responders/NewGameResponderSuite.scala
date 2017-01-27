package com.github.pwdd.tttWebApp.serverEngine.responders

import java.nio.file.Paths

import org.scalatest.FunSuite

class NewGameResponderSuite extends FunSuite {
  test("canRespond: is true for root") {
    val root = Paths.get(System.getProperty("user.dir"), "/public").toString
    assert(NewGameResponder.canRespond(root))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameResponder.canRespond("/play"))
  }
}
