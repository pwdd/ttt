document.addEventListener("DOMContentLoaded", function (event) {

  $ajax.sendGETRequest("/new.json", htmlBoard);

  window.addEventListener("submit", function (event) {
    event.preventDefault();
    var board = JSON.stringify(event.target.board.value.split(","));
    var spot = event.target.spot.value;
    var data = '{"board":' + board + ',"spot":' + spot + "}";
    $ajax.sendPOSTRequest("/move.json", htmlBoard, data);
  });

  function htmlBoard(json) {
    insertHTML("main", json["message"] + boardString(json));
  }

  function insertHTML(selector, html) {
    var target = document.getElementById(selector);
    target.innerHTML = html
  }

  function boardString(jsonData) {
    var board = jsonData["board"];
    var formOpen = "<form method=\"post\" action=\"\" style=\"display: inline\">";
    var hiddenBoard = "<input type=\"hidden\" name=\"board\" value=\"" + board.toString() + "\">";
    var indexBreaks = [2, 5, 8]
    var finalForm = "";

    var formClose = function(index) {
      if (indexBreaks.indexOf(index) !== -1) {
        return "</form><br>";
      } else {
        return "</form>";
      }
    }

    for (var i = 0; i < board.length; i++) {
      finalForm += formOpen;
      finalForm += hiddenBoard;
      finalForm += "<input type=\"hidden\" value=\"" + i + "\" name=\"spot\">";
      finalForm += "<input id=\"spot-" + i + "\" type=\"submit\" value=\"" + board[i] + "\">";
      finalForm += formClose(i);
    }
    return finalForm;
  }
});
