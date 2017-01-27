document.addEventListener("DOMContentLoaded", function (event) {

  $ajax.sendGETRequest("/new.json", htmlBoard);

  function htmlBoard(json) {
    insertHTML("main", "<h2>" + boardString(json) + "</h2>");
  }

  var insertHTML = function (selector, html) {
    var target = document.getElementById(selector);
    target.innerHtml += html
  }

  var boardString = function (json) {
    var board = json["board"];

    var symbolToValue(marker) = function () {
      if (marker === "_") { return "-"; }
      else { return marker; }
    }
  }
});

//
//def strBoard(board: List[Symbol]): String = {
//
//    def symbolToValue(symbol: Symbol): String = symbol match {
//      case GameSettings.emptySpot => "-"
//      case GameSettings.firstPlayerMarker => "x"
//      case GameSettings.secondPlayerMarker => "o"
//    }
//
//    def gameBoardToString: String = {
//      board.map(symbolToValue(_)).mkString(",")
//    }
//
//    val htmlStart = "<!doctype html><html><head><title>TTT</title></head><body>"
//    val formOpen = "<form method=\"post\" action=\"/play\" style=\"display: inline\">"
//    val hiddenBoard = "<input type=\"hidden\" name=\"board\" value=\"" + gameBoardToString + "\">"
//    val htmlClose = "</body></html>"
//
//    def formClose(index: Int): String = if (List(2, 5, 8).contains(index)) "</form><br>" else "</form>"
//
//    def createForms: String = {
//      var form = ""
//      for ((e, i) <- board.view.zipWithIndex) {
//        form += formOpen
//        form += hiddenBoard
//        form += "<input type=\"hidden\" value=\"" + i + "\" name=\"spot\">" +
//          "<input id=\"spot-" + i + "\" type=\"submit\" value=\"" + symbolToValue(e) + "\">"
//        form += formClose(i)
//      }
//      form
//    }
//    htmlStart + createForms + htmlClose
//  }