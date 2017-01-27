package com.github.pwdd.tttWebApp.tttEngine

import org.scalatest.FunSuite

class WebMessengerSuite extends FunSuite {
  private val board =  List(
    'x, '_, 'x,
    'o, '_, 'o,
    'x, 'o, 'x)

  private val forms = WebMessenger.strBoard(board)

  test("strBoard: prints a series an html with a form tag for each board spot") {
    assert("<form".r.findAllMatchIn(forms).length === board.length)
  }

  test("strBoard: adds a break every 3 form tags") {
    assert("<br>".r.findAllMatchIn(forms).length === 3)
  }

  test("strBoard: has a field that holds board representation") {
    assert(forms contains "x,_,x,o,_,o,x,o,x")
  }
}