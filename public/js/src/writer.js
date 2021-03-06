var insertHTML = function(selector, html) {
  var target = document.getElementById(selector);
  target.innerHTML = html
};

var boardString = function(jsonData) {
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
};

var htmlGame = function(json) {
  insertHTML("main", boardString(json));
  insertHTML("message", json["message"]);
};

module.exports = {
  htmlGame: htmlGame,
  insertHTML: insertHTML,
  boardString: boardString
}
