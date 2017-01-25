package com.github.pwdd.tttWebApp.serverEngine.responders

import org.scalatest.FunSuite

class NewGameResponderSuite extends FunSuite {
  test("canRespond: is true for root") {
    assert(NewGameResponder.canRespond("/"))
  }

  test("canRespond: is false for anything else") {
    assert(!NewGameResponder.canRespond("/play"))
  }
}
