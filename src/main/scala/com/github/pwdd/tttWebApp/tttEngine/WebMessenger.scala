package com.github.pwdd.tttWebApp.tttEngine

import com.github.pwdd.tttcore.Board

object WebMessenger {
  def strBoard(board: List[Symbol]): String = {

    def symbolToValue(symbol: Symbol): String = symbol match {
      case Board.emptySpot => "-"
      case Board.firstPlayer => "x"
      case Board.secondPlayer => "o"
    }

    def gameBoardToString: String = {
      board.map(symbolToValue(_)).mkString(",")
    }

    val htmlStart = "<!doctype html><html><head><title>TTT</title></head><body>"
    val formOpen = "<form method=\"post\" action=\"/play\" style=\"display: inline\">"
    val hiddenBoard = "<input type=\"hidden\" name=\"board\" value=\"" + gameBoardToString + "\">"
    val htmlClose = "</body></html>"

    def formClose(index: Int): String = if (List(2, 5, 8).contains(index)) "</form><br>" else "</form>"

    def createForms: String = {
      var form = ""
      for ((e, i) <- board.view.zipWithIndex) {
        form += formOpen
        form += hiddenBoard
        form += "<input type=\"hidden\" value=\"" + i + "\" name=\"input\">" +
          "<input id=\"spot-" + i + "\" type=\"submit\" value=\"" + symbolToValue(e) + "\">"
        form += formClose(i)
      }
      form
    }
    htmlStart + createForms + htmlClose
  }
}
