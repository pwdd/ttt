package com.github.pwdd.tttWebApp.tttEngine

object WebMessenger {
  val invalidMove = "\nYour choice is not valid. \n"

  val draw = "The game tied!\n"

  def win(winner: Option[Symbol], position: List[Int]): String = winner match {
    case Some(marker) => "Player '" + marker.name + "' won\n"
    case _ => "<h2></h2>"
  }

  def strBoard(board: List[Symbol]): String = {

    def symbolToValue(symbol: Symbol): String = symbol match {
      case GameSettings.emptySpot => "_"
      case GameSettings.firstPlayerMarker => "x"
      case GameSettings.secondPlayerMarker => "o"
    }

    def gameBoardToString: String = {
      board.map(symbolToValue).mkString(",")
    }

    val formOpen = "<form method=\"post\" action=\"/play\" style=\"display: inline\">"
    val hiddenBoard = "<input type=\"hidden\" name=\"board\" value=\"" + gameBoardToString + "\">"
    val playItAgain = "<br><a href=\"./\"><button>Restart</button></a>"

    def formClose(index: Int): String = if (List(2, 5, 8).contains(index)) "</form><br>" else "</form>"

    def createForms: String = {
      var form: String = ""
      for ((e, i) <- board.view.zipWithIndex) {
        form += formOpen
        form += hiddenBoard
        form += "<input type=\"hidden\" value=\"" + i + "\" name=\"spot\">" +
          "<input id=\"spot-" + i + "\" type=\"submit\" value=\"" + symbolToValue(e) + "\">"
        form += formClose(i)
      }
      form
    }
    createForms + playItAgain
  }
}
